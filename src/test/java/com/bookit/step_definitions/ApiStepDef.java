package com.bookit.step_definitions;

import com.bookit.pages.MatchingUiAndAPI;
import com.bookit.utilities.BookItApiUtil;
import com.bookit.utilities.DBUtils;
import com.bookit.utilities.Environment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.ui.ISelect;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;


public class ApiStepDef {

    String accessToken;
    Response response;
    MatchingUiAndAPI uiAndAPI = new MatchingUiAndAPI();
    Map<String,String> storedMap;
    List<String> matchNames;

    @Given("User logged in to BookIt api as a teacher role")
    public void user_logged_in_to_BookIt_api_as_a_teacher_role() {
        accessToken=BookItApiUtil.getAccessToken(Environment.TEACHER_EMAIL, Environment.TEACHER_PASSWORD);
    }

    @Given("User send get request to {string}")
    public void user_send_get_request_to(String path) {
       response= RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .when().get(Environment.BASE_URL+path);
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int expStatusCode) {
       MatcherAssert.assertThat(response.statusCode(), equalTo(expStatusCode));
    }

    @And("content type {string}")
    public void content_type(String expContentType) {
        MatcherAssert.assertThat(response.contentType(),equalTo(expContentType));

    }

    @Then("role is {string}")
    public void roleIs(String role) {
        MatcherAssert.assertThat(response.jsonPath().getString("role"), equalTo(role));
    }

//     "id": 11516,
//             "firstName": "Barbabas",
//             "lastName": "Lyst",
//             "role": "teacher"

    @Then("User should see same info on UI and API")
    public void userShouldSeeSameInfoOnUIAndAPI() {
        Map apiUserMap = response.body().as(Map.class);
       MatcherAssert.assertThat(uiAndAPI.fullName.getText(),
               is(apiUserMap.get("firstName")+" "+apiUserMap.get("lastName")));
        response.prettyPrint();
    }

    @When("User sends post request to {string} with following info:")
    public void userSendsPostRequestToWithFollowingInfo(String endPoint, Map<String,String> newStudentInfo) {
        storedMap = newStudentInfo;
              response =  RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .and().params(newStudentInfo)
                .when().post(Environment.BASE_URL+endPoint);
        System.out.println(response.prettyPrint());
    }

    @And("User deletes previous created student {string}")
    public void userDeletesPreviousCreatedStudent(String endPoint) {
        int idNumber = response.path("entryiId");

        RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .when().delete(Environment.BASE_URL+endPoint+idNumber)
                .then().assertThat().statusCode(204);
    }

    @And("User send get request to {string} with {string}")
    public void userSendGetRequestToWith(String endPoint, String team_id) {
        response =RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .and().pathParam("id", team_id).log().all()
                .when().get(Environment.BASE_URL+endPoint);
        response.prettyPrint();
    }

    @Then("status code should be  {int}")
    public void statusCodeShouldBe(int codeOfStatus) {
            MatcherAssert.assertThat(response.statusCode(),is(codeOfStatus));
    }
    @And("Team name should match {string} in response")
    public void teamNameShouldMatchInResponse(String exTeamName) {
        MatcherAssert.assertThat(response.path("name"), is(exTeamName));
    }

    @And("Database query should have same {string} and {string}")
    public void databaseQueryShouldHaveSameAnd(String team_id, String team_name) {
        String sql = "select id,name from team where id ="+team_id+"";
        Map dbTeamInfo = DBUtils.getRowMap(sql);
        MatcherAssert.assertThat(dbTeamInfo.get("id"), is(Long.parseLong(team_id)));
        MatcherAssert.assertThat(dbTeamInfo.get("name"), is(team_name));

    }

    @And("Database should have same team information")
    public void databaseShouldHaveSameTeamInformation() {
        int newTeamID = response.path("entryiId");
        String sql = "SELECT * FROM team WHERE id = "+newTeamID;
        Map <String, Object>teamMapSearch =DBUtils.getRowMap(sql);

        MatcherAssert.assertThat(teamMapSearch.get("id"), equalTo((long)(newTeamID)));
        MatcherAssert.assertThat(teamMapSearch.get("name"), equalTo(storedMap.get("team-name")));
        MatcherAssert.assertThat(teamMapSearch.get("batch_number").toString(), equalTo(storedMap.get("batch-number")));
    }

        @And("User logged in to BookIt api  as team lead role")
        public void userLoggedInToBookItApiAsTeamLeadRole() {
        accessToken = BookItApiUtil.getAccessToken(Environment.LEADER_EMAIL, Environment.LEADER_PASSWORD);
    }

    @And("User sends GET request to {string} with:")
    public void userSendsGETRequestToWith(String endPoint, Map<String,String> queryParams){
       response= RestAssured.given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .and().queryParams(queryParams)
                .when().get(Environment.BASE_URL+endPoint);
    }

    @And("available rooms in response should match UI results")
    public void availableRoomsInResponseShouldMatchUIResults() {
         matchNames= response.jsonPath().getList("description");
        MatcherAssert.assertThat(UIRoomBookItStepDef.availableRooms, equalTo(matchNames));
    }

    @And("available rooms in DB should match UI and API results")
    public void availableRoomsInDBShouldMatchUIAndAPIResults() {
        String query =
                "select room.description from room inner join cluster on room.cluster_id = cluster.id where cluster.name='light-side'";
        List<Object> DBResults = DBUtils.getColumnData(query, "description");
        MatcherAssert.assertThat(DBResults, allOf(equalTo(UIRoomBookItStepDef.availableRooms), equalTo(matchNames)));
    }

    @And("User deletes previous created student team")
    public void userDeletesPreviousCreatedStudentTeam() {
            int teamID = response.path("entryiId");

            RestAssured.given().accept(ContentType.JSON)
                    .and().header("Authorization", accessToken)
                    .when().delete(Environment.BASE_URL+"/api/teams/"+teamID)
                    .then().assertThat().statusCode(200);
    }
}

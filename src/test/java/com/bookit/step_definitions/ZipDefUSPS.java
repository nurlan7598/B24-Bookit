package com.bookit.step_definitions;

import com.bookit.pages.ZipCodeUIPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.*;

public class ZipDefUSPS {
   Response response;
    UIZipCodeStepDef login = new UIZipCodeStepDef();
    ZipCodeUIPage zipCodeUIPage = new ZipCodeUIPage();
    @Given("User sends GET request to {string} with {string} zipcode")
    public void userSendsGETRequestToWithZipcode(String endpoint, String zipcode) {
       response= RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("postal-code", zipcode)
                .when().get(endpoint);
        System.out.println(response);
    }

    @Then("city name should be {string} in response")
    public void cityNameShouldBeInResponse(String cityName) {
        MatcherAssert.assertThat(response.statusCode(), is(200));
        response.prettyPrint();

       String city = response.jsonPath().getString("places[0].'place name'");
       MatcherAssert.assertThat(city, equalToIgnoringCase(cityName));
    }

    @And("User searches for {string} on USPS web")
    public void userSearchesForOnUSPSWeb(String str1) {
        login.getLink();
        zipCodeUIPage.searchBoxZipcode.sendKeys(str1);
        zipCodeUIPage.searchButtonZipcode.click();
    }

    @Then("city name should be {string} in result")
    public void cityNameShouldBeInResult(String str1) {
        MatcherAssert.assertThat(str1, is(zipCodeUIPage.textOFCity.getText()));
    }
}

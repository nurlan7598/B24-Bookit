package com.bookit.step_definitions;

import com.bookit.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;

import static org.junit.Assert.assertEquals;

public class HelloWorldApiStepDefs {
    Response response;

    @Given("User sends get request to hello world api")
    public void user_sends_get_request_to_hello_world_api() {
        response= RestAssured.given().accept(ContentType.JSON)
                .and().get(ConfigurationReader.getProperty("hello.world.api"));
    }

    @Then("status code is {int}")
    public void status_code_is(int exStatusCode) {
       assertEquals(exStatusCode, response.statusCode());
        }

    @Then("response body contains {string}")
    public void response_body_contains(String exValue) {
       String mm = response.jsonPath().getString("message");
        assertEquals(exValue,mm);
    }
}

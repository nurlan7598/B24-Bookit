package com.bookit.step_definitions;

import com.bookit.pages.HomePage;
import com.bookit.pages.HuntPage;
import com.bookit.pages.LogIn;
import com.bookit.pages.SpotsPage;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Map;

public class UIRoomBookItStepDef {

    LogIn log = new LogIn();
    HomePage home = new HomePage();
    HuntPage huntPage = new HuntPage();
    SpotsPage spotsPage = new SpotsPage();
    static  List<String>availableRooms;

    @Given("User logged in to BookIt app  as team lead role")
    public void user_logged_in_to_BookIt_app_as_team_lead_role() {
        Driver.getDriver().get(Environment.URL);
        log.logIn(Environment.LEADER_EMAIL,Environment.LEADER_PASSWORD);
    }

    @When("User goes to room hunt page")
    public void user_goes_to_room_hunt_page() {
        home.huntButton.click();
    }

    @When("User sets date for using room as:")
    public void user_sets_date_for_using_room_as(Map<String,String> dateInfo) {
        huntPage.dateField.sendKeys(dateInfo.get("date"));
       huntPage.selectStartTime(dateInfo.get("from"));
       huntPage.selectEndTime(dateInfo.get("to"));
        huntPage.searchButton.click();
    }

    @Then("User should see available rooms:")
    public void user_should_see_available_rooms() {
        BrowserUtils.waitFor(2);
        availableRooms = BrowserUtils.getElementsText(spotsPage.roomNames);
        System.out.println(availableRooms);
    }
}

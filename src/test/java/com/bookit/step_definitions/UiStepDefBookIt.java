package com.bookit.step_definitions;

import com.bookit.pages.HomePage;
import com.bookit.pages.LogIn;
import com.bookit.pages.MatchingUiAndAPI;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class UiStepDefBookIt {
            LogIn logIn = new LogIn();
            HomePage homePage = new HomePage();
            MatchingUiAndAPI uiAndAPI = new MatchingUiAndAPI();

    @And("User logged in BookIt api as a teacher role")
    public void UserLoggedInBookItApiAsATeacherRole() {
        Driver.getDriver().get(Environment.URL);
        logIn.logIn(Environment.TEACHER_EMAIL,Environment.TEACHER_PASSWORD);
    }


    @And("User is on self page")
    public void userIsOnSelfPage() {
        homePage.clickButtonMy();
    }
}

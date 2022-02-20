package com.bookit.pages;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.Environment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LogIn  extends BasePage{

    @FindBy (name = "email")
    public WebElement eMail;

    @FindBy(name = "password")
    public WebElement paSword;

    @FindBy(xpath = "//button[.='sign in']")
    public WebElement signInButtons;

    public void logIn(String email, String password){
        eMail.sendKeys(email);
        paSword.sendKeys(password);
        BrowserUtils.waitFor(2);
        signInButtons.click();
    }
}

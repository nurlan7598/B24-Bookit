package com.bookit.pages;

import com.bookit.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "(//*[.='my'])[2]")
    public WebElement myClick;

    @FindBy(xpath = "(//*[.='self'])")
    public WebElement selfClick;

    @FindBy (xpath = "//a[.='hunt']")
    public WebElement huntButton;

    public void clickButtonMy() {
        myClick.click();
        BrowserUtils.waitFor(2);
        selfClick.click();
    }
}

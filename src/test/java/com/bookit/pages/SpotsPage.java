package com.bookit.pages;

import com.bookit.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpotsPage extends BasePage{


    @FindBy(xpath = "//p[@class='subtitle']")
    public List<WebElement>roomNames;

}

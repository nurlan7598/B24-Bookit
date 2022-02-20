package com.bookit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ZipCodeUIPage extends BasePage{
    @FindBy(id = "tZip")
    public WebElement searchBoxZipcode;

    @FindBy(id = "cities-by-zip-code")
    public WebElement searchButtonZipcode;

    @FindBy(xpath = "(//p[@class='row-detail-wrapper'])[1]")
    public WebElement textOFCity;
}
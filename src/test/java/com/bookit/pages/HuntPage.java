package com.bookit.pages;

import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HuntPage extends BasePage{

    @FindBy (id = "mat-input-0")
    public WebElement dateField;

    @FindBy(xpath = "(//div[@class='mat-select-value'])[1]")
    public WebElement from;

    @FindBy(xpath = "(//div[@class='mat-select-value'])[2]")
    public WebElement too;

    @FindBy(xpath = "//*[@class='mat-icon material-icons']")
    public WebElement searchButton;

    public void selectStartTime(String str1){
        BrowserUtils.waitFor(2);
        from.click();
        BrowserUtils.waitFor(2);
        Driver.getDriver().findElement(By.xpath("//span[contains(text(),'"+str1+"')]")).click();
    }

    public void selectEndTime(String str2){
        BrowserUtils.waitFor(2);
        too.click();
        BrowserUtils.waitFor(2);
        Driver.getDriver().findElement(By.xpath("//span[contains(text(),'"+str2+"')]")).click();
    }
}

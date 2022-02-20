package com.bookit.step_definitions;

import com.bookit.pages.BasePage;
import com.bookit.utilities.Driver;
import org.junit.Test;

public class UIZipCodeStepDef extends BasePage {

    @Test
    public void getLink(){
        Driver.getDriver().get("https://tools.usps.com/zip-code-lookup.htm?citybyzipcode");
    }

}

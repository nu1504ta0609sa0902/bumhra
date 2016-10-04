package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects.ActionsPage;
import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class UpdateBusinessRules extends _Page {

    @FindBy(partialLinkText = "Other")
    WebElement others;

    @FindBy(xpath = ".//*[.='E-Cigarette Nicotine Strength']//following::input[1]")
    WebElement nicotineStrength;

    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submitBtn;

    @Autowired
    public UpdateBusinessRules(WebDriver driver) {
        super(driver);
    }

    public UpdateBusinessRules viewSpecifiedProductType(String productType) {
        WaitUtils.waitForElementToBeClickable(driver, others, TIMEOUT_DEFAULT, false);
        if(productType.equals("Other")){
            PageUtils.doubleClick(driver, others);
        }
        return new UpdateBusinessRules(driver);
    }


    public ActionsPage updateFieldValue(String filedToUpdate, String updateValue) {
        if(filedToUpdate.equals("Nicotine Strength")) {
            WaitUtils.waitForElementToBeClickable(driver, nicotineStrength, TIMEOUT_DEFAULT, false);
            nicotineStrength.clear();
            nicotineStrength.sendKeys(updateValue);
        }

        PageUtils.doubleClick(driver, submitBtn);
        //return new UpdateBusinessRules(driver);
        return new ActionsPage(driver);
    }
}

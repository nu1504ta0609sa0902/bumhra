package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class UpdateQAPercentage extends _Page {

    @FindBy(xpath = ".//label[.='Percentage']//following::input[1]")
    WebElement percentage;
    @FindBy(xpath = ".//label[.='Percentage']//following::p[@class='component_error']")
    WebElement errorMessage;
    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submitBtn;
    @FindBy(xpath = ".//button[.='Cancel']")
    WebElement cancelBtn;
    @FindBy(xpath = ".//button[.='Yes']")
    WebElement alertYes;
    @FindBy(xpath = ".//button[.='No']")
    WebElement alertNo;

    @Autowired
    public UpdateQAPercentage(WebDriver driver) {
        super(driver);
    }

    public UpdateQAPercentage setQAPercentage(String qaPercentage) {
        WaitUtils.waitForElementToBeClickable(driver, percentage, 5, false);
        percentage.clear();
        percentage.sendKeys(qaPercentage);
        PageUtils.singleClick(driver, submitBtn);
        return new UpdateQAPercentage(driver);
    }

    public void acceptDialog(boolean accept) {
        WaitUtils.waitForElementToBeClickable(driver, alertYes, 5, false);
        if(accept)
            alertYes.click();
        else
            alertNo.click();
//        WaitUtils.waitForAlert(driver, 5, false);
//        Alert alert = driver.switchTo().alert();
//        if(accept){
//            alert.accept();
//        }else{
//            alert.dismiss();
//        }
    }

    public boolean isErrorMessageCorrect(String expectedMessage) {
        WaitUtils.waitForElementToBeClickable(driver, errorMessage, 5, false);
        boolean matched = errorMessage.getText().contains(expectedMessage);
        return matched;
    }

    public boolean isQAPercentageCorrect(String expectedQAPercentage) {
        WaitUtils.waitForElementToBeClickable(driver, percentage, 5, false);
        String currentQAPercentage = percentage.getText();
        boolean matched = currentQAPercentage.equals(expectedQAPercentage);
        return matched;
    }
}

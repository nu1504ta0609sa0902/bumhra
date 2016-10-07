package com.mhra.mcm.appian.pageobjects;

import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class TasksPage extends _Page {

    @FindBy(xpath = ".//h2")
    WebElement ecidHeading;

    @FindBy(xpath = ".//span[.='EC ID']//following::p[1]")
    WebElement ecid;

    @FindBy(xpath = ".//button[.='Accept']")
    WebElement accept;

    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submit;

    @FindBy(xpath = ".//label[.='TCA Number']//following::input[1]")
    WebElement tcaNumber;

    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement tcaName;

    @FindBy(partialLinkText = "Update TCA for")
    List<WebElement> listOfTCALinks;

    @FindBy(xpath = ".//label[.='Approve']")
    WebElement rbApprove;

    @FindBy(xpath = ".//label[.='Reject']")
    WebElement rbReject;

    @Autowired
    public TasksPage(WebDriver driver) {
        super(driver);
    }

    public TasksPage clickTaskWithSubmitterName(String submitterName) {
        boolean found = false;
        int attempt = 0;
        do {
            attempt++;

            try {
                WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(submitterName), 5, false);
                WebElement task = driver.findElement(By.partialLinkText(submitterName));
                task.click();
                found = true;
            }catch(Exception e){
                found = false;
            }

            //refresh page
            if(!found) {
                driver.navigate().refresh();
                PageFactory.initElements(driver, this);
            }

        }while(!found && attempt < 10);

        return new TasksPage(driver);
    }

    public boolean isCorrectECID(String ecIDNumber) {
        try {
            WaitUtils.waitForElementToBeClickable(driver, ecid, 10, false);
            String text = ecid.getText();
            boolean contains = text.equals(ecIDNumber);
            return contains;
        }catch (Exception e){
            return false;
        }
    }



    public boolean isNotificationForECID(String ecIDNumber) {
        try {
            WaitUtils.waitForElementToBeClickable(driver, ecidHeading, 10, false);
            String text = ecidHeading.getText();
            boolean contains = text.equals(ecIDNumber);
            return contains;
        }catch (Exception e){
            return false;
        }
    }

    public TasksPage acceptTask() {
        try
        {
            WaitUtils.waitForElementToBeVisible(driver, accept, 10, false);
            if(accept.isDisplayed()){
                accept.click();
            }
        }catch(Exception e){
            log.info("Task Already Accepted ");
        }
        return new TasksPage(driver);
    }

    public void enterTCANumber(String newTCANumber) {
        WaitUtils.isElementPartOfDomAdvanced2(driver, By.xpath(".//label[.='TCA Number']//following::input[1]"), 20, false);
        WaitUtils.waitForElementToBeClickable(driver, tcaNumber, 10, false);
        tcaNumber.sendKeys(newTCANumber);
    }

    public void enterTCAName(String submitterName) {
        WaitUtils.waitForElementToBeClickable(driver, tcaName, 10, false);
        tcaName.sendKeys(submitterName);
    }

    public TasksPage updateSummary() {
        PageUtils.doubleClick(driver,submit);
        //submit.submit();
        return new TasksPage(driver);
    }

    public TasksPage clickOnLinkAndVerifyItsCorrectPage(int count) {
        WaitUtils.isElementPartOfDomAdvanced2(driver, By.partialLinkText("Update TCA for"), 20, false);
        WebElement tcaLink = listOfTCALinks.get(count);
        tcaLink.click();
        return new TasksPage(driver);
    }

    public boolean isOptionToAcceptRejectDisplayed() {
        WaitUtils.waitForElementToBeVisible(driver, rbApprove, 5, false);
        boolean approveDisplayed = rbApprove.isDisplayed();
        boolean rejectDisplayed = rbReject.isDisplayed();
        return approveDisplayed && rejectDisplayed;
    }
}

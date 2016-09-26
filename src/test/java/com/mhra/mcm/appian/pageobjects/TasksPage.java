package com.mhra.mcm.appian.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class TasksPage extends _Page {

    @FindBy(xpath = ".//span[.='EC ID']//following::p[1]")
    WebElement ecid;

    @FindBy(xpath = ".//button[.='Accept']")
    WebElement accept;

    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submit;

    @FindBy(xpath = ".//label[.='TCA Number']//following::input[1]")
    WebElement tcaNumber;

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

    public TasksPage updateSummary() {
        PageUtils.doubleClick(driver,submit);
        //submit.submit();
        return new TasksPage(driver);
    }
}

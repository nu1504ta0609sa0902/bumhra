package com.mhra.mcm.appian.po;

import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(submitterName), 5);
                WebElement task = driver.findElement(By.partialLinkText(submitterName));
                task.click();
                found = true;
            }catch(Exception e){
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
        WaitUtils.waitForElementToBeClickable(driver, ecid, 5);
        String text = ecid.getText();
        boolean contains = text.equals(ecIDNumber);
        return contains;
    }

    public TasksPage acceptTask() {
        try
        {
            if(accept.isDisplayed()){
                accept.click();
            }
        }catch(Exception e){
            log.info("Task Already Accepted ");
        }
        return new TasksPage(driver);
    }

    public void enterTCANumber(String newTCANumber) {
        WaitUtils.waitForElementToBeClickable(driver, tcaNumber, 5);
        tcaNumber.sendKeys(newTCANumber);
    }

    public TasksPage updateSummary() {
        PageUtils.doubleClick(driver,submit);
        //submit.submit();
        return new TasksPage(driver);
    }
}

package com.mhra.mcm.appian.pageobjects;

import com.mhra.mcm.appian.pageobjects.sections.contents.ManageSubstances;
import com.mhra.mcm.appian.pageobjects.sections.contents.UpdateQAPercentage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.pageobjects.sections.contents.CreateNotification;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ActionsPage extends _Page {

    @FindBy(partialLinkText = "Upload Sample Notification")
    WebElement uploadSampleNotification;

    @FindBy(partialLinkText = "Create Standard Invoices")
    WebElement createStandardNotification;

    @FindBy(partialLinkText = "Update QA Percentage")
    WebElement updateQAPercentage;

    @FindBy(partialLinkText = "Create Annual Invoices")
    WebElement createAnnualInvoices;

    @FindBy(partialLinkText = "Manage Substances")
    WebElement manageSubstances;

    @Autowired
    public ActionsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNotification clickUploadSampleNotification() {

        try {
            WaitUtils.waitForElementToBeClickable(driver, uploadSampleNotification, 10, false);
            uploadSampleNotification.click();
            return new CreateNotification(driver);
        } catch (Exception e) {
            return null;
        }
    }

    public ActionsPage generateStandardInvoices() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, createStandardNotification, 10, false);
            PageUtils.singleClick(driver, createStandardNotification);
            return new ActionsPage(driver);
        } catch (Exception e) {
            return null;
        }
    }

    public ActionsPage generateAnnualInvoices() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, createAnnualInvoices, 10, false);
            PageUtils.singleClick(driver, createAnnualInvoices);
            return new ActionsPage(driver);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isNotificationGeneratedSuccessfully() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, uploadSampleNotification, 10, false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UpdateQAPercentage clickUpdateQAPercentage() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, updateQAPercentage, 10, false);
            PageUtils.doubleClick(driver, updateQAPercentage);
            return new UpdateQAPercentage(driver);
        } catch (Exception e) {
            return null;
        }
    }

    public ManageSubstances clickManageSubstances() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, manageSubstances, 10, false);
            PageUtils.doubleClick(driver, manageSubstances);
            return new ManageSubstances(driver);
        } catch (Exception e) {
            return null;
        }
    }
}

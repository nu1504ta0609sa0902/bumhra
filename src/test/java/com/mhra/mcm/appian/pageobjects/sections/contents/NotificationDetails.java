package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 26/07/2016.
 */
@Component
public class NotificationDetails extends _Page {

    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement submitterName;

    @FindBy(linkText = "Manage Documents")
    WebElement manageDocuments;
    @FindBy(xpath = ".//*[.='Audit History']")
    WebElement auditHistory;
    @FindBy(xpath = ".//*[.='Comments']")
    WebElement comments;
    @FindBy(xpath = ".//*[.='Documents']")
    WebElement documents;
    @FindBy(xpath = ".//*[.='Assessment Report']")
    WebElement assessmentReport;

    @FindBy(css = ".GJEWJWHDFR")
    WebElement header;
    @FindBy(css = ".GJEWJWHDCP .GJEWJWHDIP")
    WebElement currentStatus;

    @FindBy(linkText = "Manage Notification")
    WebElement manageNotificationBtn;

    @Autowired
    public NotificationDetails(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean headerContainsID(String expectedNotificationID) {
        WaitUtils.waitForElementToBeClickable(driver, header, 5);
        return header.getText().contains(expectedNotificationID);
    }

    public String getCurrentStatus() {
        WaitUtils.waitForElementToBeClickable(driver, currentStatus, 20, false);
        return currentStatus.getText();
    }

    public boolean hasPageStatusChangedTo(String currentStatusText) {

        boolean statusChanged = false;
        WaitUtils.waitForElementToBeVisible(driver, currentStatus, 10, false);
        String updatedStatus = getCurrentStatus();
        if(!updatedStatus.equals(currentStatusText)){
            statusChanged = true;
        }else{
            //WaitUtils.nativeWait(5);
            driver.navigate().refresh();
            PageFactory.initElements(driver, this);
        }

        return statusChanged;
    }

    public boolean expectedStatusToBe(String expectedStatus) {
        boolean found = false;
        WaitUtils.waitForElementToBeVisible(driver, currentStatus, 5);
        String updatedStatus = getCurrentStatus();
        if(updatedStatus.equals(expectedStatus) || updatedStatus.equals("Quality Assurance")){
            found = true;
        }else{
            //WaitUtils.nativeWait(5);
            driver.navigate().refresh();
            PageFactory.initElements(driver, this);
        }

        return found;
    }

    public EditNotification clickManageDocuments() {
        WaitUtils.waitForElementToBeClickable(driver, manageDocuments, 5);
        PageUtils.doubleClick(driver, manageDocuments);
        //manageDocuments.click();
        return new EditNotification(driver);
    }

    public EditNotification clickManageNotification() {
        WaitUtils.waitForElementToBeClickable(driver, manageNotificationBtn, 5);
        manageNotificationBtn.click();
        return new EditNotification(driver);
    }

    public boolean isCorrectPage() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, manageDocuments, 5, false);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public AuditHistory clickAuditHistory() {
        WaitUtils.waitForElementToBeClickable(driver, auditHistory, 10, false);
        PageUtils.doubleClick(driver, auditHistory);
        //auditHistory.click();
        return new AuditHistory(driver);
    }

    public Comments clickCommentsLink() {
        WaitUtils.waitForElementToBeClickable(driver, comments, 10, false);
        PageUtils.singleClick(driver, comments);
        return new Comments(driver);
    }

    public Documents clickDocuments() {
        WaitUtils.waitForElementToBeClickable(driver, documents, 10, false);
        PageUtils.singleClick(driver, documents);
        return new Documents(driver);
    }

    public AssessmentReport clickAssessmentReport() {
        WaitUtils.waitForElementToBeClickable(driver, assessmentReport, 10, false);
        PageUtils.doubleClick(driver, assessmentReport);
        return new AssessmentReport(driver);
    }
}

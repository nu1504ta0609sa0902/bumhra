package com.mhra.mcm.appian.pageobjects.sections.contents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.others.FileUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

/**
 * Created by TPD_Auto on 20/07/2016.
 */
@Component
public class EditNotification extends _Page {

    @FindBy(xpath = ".//label[.='EC ID']//following::input[1]")
    WebElement ecId;
    @FindBy(xpath = ".//label[.='Previous EC ID']//following::input[1]")
    WebElement previousEcId;
    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement submitterName;
    @FindBy(xpath = ".//span[.='Status']//following::select[1]")
    WebElement status;
    @FindBy(xpath = ".//span[.='Submission Type']//following::select[1]")
    WebElement submissionType;
    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submitBtn;

    @Autowired
    public EditNotification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String appendTextToSubmitterName(String append) {
        WaitUtils.waitForElementToBeVisible(driver, submitterName, 5);
        String existingName = PageUtils.getText(submitterName);
        submitterName.clear();
        submitterName.sendKeys(existingName + append);
        return existingName;
    }

    public NotificationDetails submitChanges() {
        submitterName.submit();
        return new NotificationDetails(driver);
    }

    public NotificationDetails addGenericToxicologyReportFromTempFolder(String fileName, Notification random) {
        String fullPath = FileUtils.getFileFullPath("tmp", fileName);
        String name = random.getIngredient().ingredientName;
        NotificationUtils.addDocumentNumber(1, driver, "5", fullPath, "Some Description", false, false, name);
        return new NotificationDetails(driver);
    }

    public NotificationDetails updateStatusTo(String updatedStatus) {
        WaitUtils.waitForElementToBeClickable(driver, status, 15, false);
        PageUtils.selectByText(status, updatedStatus);
        PageUtils.doubleClick(driver, submitBtn);
        return new NotificationDetails(driver);
    }

    public boolean isCorrectPage() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, submitBtn, 10, false);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Use to update submission type and set previous and new ecid
     * @param type
     * @param prevECID
     * @param newECID
     * @return
     */
    public NotificationDetails updateNotificationECID(String type, String prevECID, String newECID) {
        WaitUtils.waitForElementToBeClickable(driver, submissionType, 15, false);

        if(type!=null && !type.equals(""))
        PageUtils.selectByIndex(submissionType, type);
        
        ecId.clear();
        ecId.sendKeys(newECID);
        previousEcId.clear();
        previousEcId.sendKeys(prevECID);

        //Submit for notification update
        PageUtils.doubleClick(driver, submitBtn);
        return new NotificationDetails(driver);
    }


    public NotificationDetails updateNotificationStatus(String statusTxt, String prevECID, String newECID) {
        WaitUtils.waitForElementToBeClickable(driver, submissionType, 15, false);

        if(statusTxt!=null && !statusTxt.equals(""))
            PageUtils.selectByText(status, statusTxt);

        ecId.clear();
        ecId.sendKeys(newECID);
        previousEcId.clear();
        previousEcId.sendKeys(prevECID);

        //Submit for notification update
        PageUtils.doubleClick(driver, submitBtn);
        return new NotificationDetails(driver);
    }
}

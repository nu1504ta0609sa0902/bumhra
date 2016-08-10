package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.helpers.FileUtils;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;
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

    @FindBy(css = ".GJEWJWHDFR")
    WebElement header;
    @FindBy(css = ".GJEWJWHDCP .GJEWJWHDIP")
    WebElement currentStatus;

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
        WaitUtils.waitForElementToBeClickable(driver, currentStatus, 5);
        return currentStatus.getText();
    }

    public boolean hasPageStatusChangedTo(String currentStatusText) {

        boolean statusChanged = false;
        WaitUtils.waitForElementToBeVisible(driver, currentStatus, 5);
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
        if(updatedStatus.equals(expectedStatus)){
            found = true;
        }else{
            //WaitUtils.nativeWait(5);
            driver.navigate().refresh();
            PageFactory.initElements(driver, this);
        }

        return found;
    }

    public NotificationDetails addGenericToxicologyReportFromTempFolder(String fileName, Notification random) {
        String fullPath = FileUtils.getFileFullPath("tmp", fileName);
        String name = random.getIngredient().ingredientName;
        NotificationUtils.addDocumentNumber(1, driver, "5", fullPath, "Some Description", false, false, name);
        return new NotificationDetails(driver);
    }

    public NotificationDetails clickManageDocuments() {
        WaitUtils.waitForElementToBeClickable(driver, manageDocuments, 5);
        manageDocuments.click();
        return new NotificationDetails(driver);
    }
}

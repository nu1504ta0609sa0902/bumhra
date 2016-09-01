package com.mhra.mcm.appian.pageobjects.sections.contents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.FileUtils;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

/**
 * Created by TPD_Auto on 20/07/2016.
 */
@Component
public class EditNotification extends _Page {

    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement submitterName;
    @FindBy(xpath = ".//span[.='Status']//following::select[1]")
    WebElement status;
    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submitBtn;

    @Autowired
    public EditNotification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String appendTextToSubmitterName(String append) {
        WaitUtils.waitForElementToBeVisible(driver, submitterName, 5);
        String existingName = WaitUtils.getText(submitterName);
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
        WaitUtils.waitForElementToBeClickable(driver, status, 10, false);
        PageUtils.selectByText(status, updatedStatus);
        PageUtils.doubleClick(driver, submitBtn);
        return new NotificationDetails(driver);
    }
}

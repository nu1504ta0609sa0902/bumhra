package com.mhra.mcm.appian.po;

import com.mhra.mcm.appian.po.sections.contents.CreateNotification;
import com.mhra.mcm.appian.utils.PageUtils;
import com.mhra.mcm.appian.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ActionsPage extends _Page {

    @FindBy(linkText = "Upload Sample Notification")
    WebElement uploadSampleNotification;

    @FindBy(linkText = "Create Standard Invoices")
    WebElement createStandardNotification;

    @Autowired
    public ActionsPage(WebDriver driver) {
        super(driver);
    }

    public CreateNotification clickUploadSampleNotification() {
        WaitUtils.waitForElementToBeClickable(driver, uploadSampleNotification, 5);
        uploadSampleNotification.click();
        return new CreateNotification(driver);
    }

    public ActionsPage generateStandardInvoices() {
        WaitUtils.waitForElementToBeClickable(driver, createStandardNotification, 5);
        PageUtils.doubleClick(driver, createStandardNotification);
        //createStandardNotification.click();
        return new ActionsPage(driver);
    }
}

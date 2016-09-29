package com.mhra.mcm.appian.pageobjects;

import com.mhra.mcm.appian.pageobjects.sections.contents.Exceptions;
import com.mhra.mcm.appian.pageobjects.sections.contents.NotificationsReport;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ReportsPage extends _Page {
    @FindBy(linkText = "Exceptions")
    WebElement exceptionsLink;
    @FindBy(linkText = "Notifications")
    WebElement notificationsLink;


    @Autowired
    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    public Exceptions gotoExceptionsPage() {
        WaitUtils.waitForElementToBeClickable(driver, exceptionsLink, 5, false);
        exceptionsLink.click();
        return new Exceptions(driver);
    }

    public NotificationsReport gotoNotificationsPage() {
        WaitUtils.waitForElementToBeClickable(driver, notificationsLink, 5, false);
        notificationsLink.click();
        return new NotificationsReport(driver);
    }
}

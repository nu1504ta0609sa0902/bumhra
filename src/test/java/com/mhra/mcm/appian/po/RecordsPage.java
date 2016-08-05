package com.mhra.mcm.appian.po;

import com.mhra.mcm.appian.po.sections.contents.EditNotification;
import com.mhra.mcm.appian.po.sections.contents.NotificationDetails;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
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
public class RecordsPage extends _Page {

    @FindBy(css=".appianGridLayout.hasFooter>tbody>tr")
    List<WebElement> listOfNotifications;

    @FindBy(linkText = "Manage Notification")
    WebElement manageNotificationBtn;

    @FindBy(linkText = "Notifications")
    WebElement notificationsLink;

    @FindBy(linkText = "Users")
    WebElement usersLink;

    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement submitterName;

    @Autowired
    public RecordsPage(WebDriver driver) {
        super(driver);
    }


    public boolean hasNotifications() {
        WaitUtils.waitForElementToBeClickable(driver, By.cssSelector(".appianGridLayout.hasFooter>tbody>tr"), 5);
        return listOfNotifications.size()> 0;
    }

    public RecordsPage clickNotificationNumber(int number) {
        WebElement notification = listOfNotifications.get(number - 1);
        WebElement link = notification.findElement(By.cssSelector("div[is-selectable='selectable'] a"));
        link.click();
        return new RecordsPage(driver);
    }

    public boolean isNotificationEditable() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, manageNotificationBtn, 5);
            boolean isDisplayed = manageNotificationBtn.isDisplayed();
            if (isDisplayed)
                manageNotificationBtn.click();
            return isDisplayed;
        }catch (Exception e){
            return false;
        }
    }

    public RecordsPage clickNotificationsLink() {
        WaitUtils.waitForElementToBeClickable(driver, notificationsLink, 5);
        notificationsLink.click();
        return new RecordsPage(driver);
    }

    public RecordsPage clickUsersLink() {
        WaitUtils.waitForElementToBeClickable(driver, usersLink, 5);
        usersLink.click();
        return new RecordsPage(driver);
    }

    public EditNotification clickManageNotification() {
        WaitUtils.waitForElementToBeClickable(driver, manageNotificationBtn, 5);
        manageNotificationBtn.click();
        return new EditNotification(driver);
    }

    public boolean notificationsPageContainsText(String expectedName) {
        WaitUtils.waitForElementToBeClickable(driver, submitterName, 5);
        String text = submitterName.getText();
        boolean containsNewName = text.contains(expectedName);
        return containsNewName;
    }

    public NotificationDetails clickNotificationNumber(String expectedNotificationID) {
        boolean found = false;
        int attempt = 0;
        do {
            attempt++;

            try {
                WaitUtils.waitForElementToBeClickable(driver, By.linkText(expectedNotificationID), 5);
                WebElement notification = driver.findElement(By.linkText(expectedNotificationID));
                notification.click();
                found = true;
            }catch(Exception e){
            }

            //refresh page
            if(!found) {
                driver.navigate().refresh();
                PageFactory.initElements(driver, this);
            }

        }while(!found && attempt < 5);
        return new NotificationDetails(driver);
    }
}

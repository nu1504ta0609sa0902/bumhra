package com.mhra.mcm.appian.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.pageobjects.sections.contents.EditNotification;
import com.mhra.mcm.appian.pageobjects.sections.contents.NotificationDetails;
import com.mhra.mcm.appian.pageobjects.sections.filters.RecordsFilter;
import com.mhra.mcm.appian.utils.helpers.GenericUtils;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

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

    @FindBy(xpath = ".//h2[.='Uploaded On']//following::a")
    List<WebElement> listOfECIDLinks;

    @FindBy(xpath=".//img//following::input[1]")
    WebElement searchField;

    @Autowired
    public RecordsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public RecordsFilter getFilterSection() {
        return new RecordsFilter(driver);
    }

    public boolean hasNotifications() {
        WaitUtils.waitForElementToBeClickable(driver, By.cssSelector(".appianGridLayout.hasFooter>tbody>tr"), 5);
        return listOfNotifications.size()> 0;
    }

    public RecordsPage clickNotificationNumber(int notificationNumber) {
        WebElement notification = listOfNotifications.get(notificationNumber - 1);
        WebElement link = notification.findElement(By.cssSelector("div[is-selectable='selectable'] a"));
        link.click();
        return new RecordsPage(driver);
    }

    public boolean isNotificationEditable() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, manageNotificationBtn, 7, false);
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
        PageUtils.doubleClick(driver, notificationsLink);
        //notificationsLink.click();
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

    public boolean notificationsPageContainsText(String expectedName, boolean refresh) {
        if(refresh){
            //Bug with the page, it requires a page refresh to show the updates
            WaitUtils.waitForElementToBeClickable(driver, submitterName, 5);
            driver.navigate().refresh();
            PageFactory.initElements(driver, this);
        }

        //
        WaitUtils.waitForElementToBeClickable(driver, submitterName, 5);
        String text = submitterName.getText();
        boolean containsNewName = text.contains(expectedName);
        return containsNewName;
    }

    /**
     *
     * @param expectedNotificationID
     * @param maxNumberOfTimesToIterate
     * @return
     */
    public NotificationDetails clickNotificationNumber(String expectedNotificationID, int maxNumberOfTimesToIterate) {
        boolean found = false;
        int attempt = 0;
        do {
            attempt++;

            try {
                WaitUtils.waitForElementToBeClickable(driver, By.linkText(expectedNotificationID), 7, false);
                WebElement notification = driver.findElement(By.linkText(expectedNotificationID));
                notification.click();
                found = true;
                break;
            }catch(Exception e){
            }

            //refresh page
            if(!found) {
                driver.navigate().refresh();
                PageFactory.initElements(driver, this);
            }

        }while(!found && attempt < maxNumberOfTimesToIterate);
        return new NotificationDetails(driver);
    }

    /**
     *
     * @param from
     * @param maxNumberOfTimesToIterate
     * @return
     */
    public String getARandomNotificationECIDFromPosition(int from, int maxNumberOfTimesToIterate) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//h2[.='Uploaded On']//following::a[2]"), 5);
        if(listOfECIDLinks.size() > 0){
            String ecID = null;
            int count = from;
            do{
                count++;
                ecID = listOfECIDLinks.get(count).getText();
                if(ecID.contains("Next") || ecID.contains("Previous") || ecID.trim().equals("")){
                    ecID = null;
                }

                if(count>maxNumberOfTimesToIterate){
                    break;
                }
            }while(ecID == null);
            return ecID;
        }else{
            return null;
        }
    }

    /**
     *
     * @param status
     * @param maxNumberOfTimesToIterate
     * @return
     */
    public String getARandomNotificationWithStatusNotEqualTo(String status, int maxNumberOfTimesToIterate) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//h2[.='Uploaded On']//following::a[2]"), 5);
        if(listOfECIDLinks.size() > 0){
            String ecID = null;
            int count = 0;
            do{
                count++;
                WebElement element = listOfECIDLinks.get(listOfECIDLinks.size() - count);
                ecID = element.getText();
                if(ecID.contains("Next") || ecID.contains("Previous") || ecID.trim().equals("")){
                    ecID = null;
                }else{
                    element = driver.findElement(By.xpath(".//*[.='" + ecID + "']//following::p[4]"));
                    String currentStatus = element.getText();
                    System.out.println("Status " + currentStatus);

                    //Bug: Failed notifications can't be edited
                    if(currentStatus.equals(status) || currentStatus.equals("Failed")){
                       ecID = null;
                    }
                }

                if(count>maxNumberOfTimesToIterate){
                    break;
                }
            }while(ecID == null);
            return ecID;
        }else{
            return null;
        }
    }

    public int getNotificationCount(String ecid) {
        WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(ecid), 5);
        List<WebElement> listOfMatches = driver.findElements(By.partialLinkText(ecid));
        int count = GenericUtils.getUniqueECIDCount(listOfMatches);
        return count;
    }


    public RecordsPage searchForECIDSubmitterOrOthers(String ecid) {
        WaitUtils.waitForElementToBeClickable(driver, searchField, 5);
        searchField.clear();
        searchField.sendKeys(ecid);
        searchField.sendKeys(Keys.ENTER);
        return new RecordsPage(driver);
    }


    public String getSubmitterNameForEcid(String ecid) {
        WebElement submitter = driver.findElement(By.xpath(".//a[.='"+ecid+"']//following::p[2]"));
        String name = submitter.getText();
        return name;
    }

    public boolean isAllNotificationStatusOfType(String filterBy) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//h2[.='Uploaded On']"), 5, false);
        //WebElement submitter = driver.findElement(By.xpath(".//h2[.='Uploaded On']//following::p[.='" + filterBy + "']"));
        List<WebElement> listOfMatchingNotificationsWithStatus = driver.findElements(By.xpath(".//h2[.='Uploaded On']//following::p[.='" + filterBy + "']"));
        boolean allStatusSame = GenericUtils.isAllStatusMatching(listOfMatchingNotificationsWithStatus, filterBy);
        return allStatusSame;
    }
    
}

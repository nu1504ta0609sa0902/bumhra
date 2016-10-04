package com.mhra.mcm.appian.pageobjects;

import com.mhra.mcm.appian.pageobjects.sections.contents.EditNotification;
import com.mhra.mcm.appian.pageobjects.sections.contents.NotificationDetails;
import com.mhra.mcm.appian.pageobjects.sections.filters.RecordsFilter;
import com.mhra.mcm.appian.utils.helpers.others.GenericUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class RecordsPage extends _Page {

    @FindBy(css = ".appianGridLayout.hasFooter>tbody>tr")
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

    @FindBy(xpath = ".//img//following::input[1]")
    WebElement searchField;

    @FindBy(xpath = ".//*[.='Previous']//following::span[2]")
    WebElement totalCount;

    @FindBy(xpath = ".//*[.='Notifications']//following::span/b")
    WebElement searchResultsFor;

    @Autowired
    public RecordsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public RecordsFilter getFilterSection() {
        WaitUtils.waitForElementToBeClickable(driver, searchField, 15, false);
        return new RecordsFilter(driver);
    }

    public boolean hasNotifications() {
        WaitUtils.waitForElementToBeClickable(driver, By.cssSelector(".appianGridLayout.hasFooter>tbody>tr"), 5);
        return listOfNotifications.size() > 0;
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
        } catch (Exception e) {
            return false;
        }
    }

    public RecordsPage clickNotificationsLink() {
        WaitUtils.isElementPartOfDomAdvanced2(driver, By.partialLinkText("Notifications"), 20, false);
        WaitUtils.waitForElementToBeClickable(driver, notificationsLink, 20);
        PageUtils.singleClick(driver, notificationsLink);
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
        if (refresh) {
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
                PageUtils.doubleClick(driver, notification);
                //notification.click();
                found = true;
                break;
            } catch (Exception e) {
                found = false;
            }

            //refresh page
            if (!found) {
                driver.navigate().refresh();
                PageFactory.initElements(driver, this);
            }

        } while (!found && attempt < maxNumberOfTimesToIterate);
        return new NotificationDetails(driver);
    }

    /**
     * @param from
     * @param maxNumberOfTimesToIterate
     * @return
     */
    public String getARandomNotificationECIDFromPosition(int from, int maxNumberOfTimesToIterate) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//h2[.='Uploaded On']//following::a[2]"), 5);
        if (listOfECIDLinks.size() > 0) {
            String ecID = null;
            int count = from;
            do {
                count++;
                ecID = listOfECIDLinks.get(count).getText();
                if (ecID.contains("Next") || ecID.contains("Previous") || ecID.trim().equals("")) {
                    ecID = null;
                }

                if (count > maxNumberOfTimesToIterate) {
                    break;
                }
            } while (ecID == null);
            return ecID;
        } else {
            return null;
        }
    }

    /**
     * @param status
     * @param maxNumberOfTimesToIterate
     * @return
     */
    public String getARandomNotificationWithStatusNotEqualTo(String status, int maxNumberOfTimesToIterate) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//h2[.='Uploaded On']//following::a[2]"), 5);
        if (listOfECIDLinks.size() > 0) {
            String ecID = null;
            int count = 0;
            do {
                count++;
                WebElement element = listOfECIDLinks.get(listOfECIDLinks.size() - count);
                ecID = element.getText();
                if (ecID.contains("Next") || ecID.contains("Previous") || ecID.trim().equals("")) {
                    ecID = null;
                } else {
                    element = driver.findElement(By.xpath(".//*[.='" + ecID + "']//following::p[4]"));
                    String currentStatus = element.getText();
                    //System.out.println("Status " + currentStatus);

                    //Bug: Failed notifications can't be edited
                    if (currentStatus.equals(status) || currentStatus.equals("Failed") || currentStatus.equals("Withdrawn")) {
                        ecID = null;
                    }
                }

                if (count > maxNumberOfTimesToIterate) {
                    break;
                }
            } while (ecID == null);
            return ecID;
        } else {
            return null;
        }
    }

    public String getARandomNotificationWithStatusEqualTo(String status, int maxNumberOfTimesToIterate) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//h2[.='Uploaded On']//following::a[2]"), 5);
        if (listOfECIDLinks.size() > 0) {
            maxNumberOfTimesToIterate = listOfECIDLinks.size();
            String ecID = null;
            int count = 0;
            do {
                count++;
                WebElement element = listOfECIDLinks.get(maxNumberOfTimesToIterate - count);
                ecID = element.getText();
                if (ecID.contains("Next") || ecID.contains("Previous") || ecID.trim().equals("")) {
                    ecID = null;
                } else {
                    WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//*[.='" + ecID + "']//following::p[4]"), 15, false);
                    element = driver.findElement(By.xpath(".//*[.='" + ecID + "']//following::p[4]"));
                    String currentStatus = element.getText();

                    //Bug: Failed notifications can't be edited
                    if (!currentStatus.equals(status) || currentStatus.equals("Failed") || currentStatus.equals("Withdrawn")) {
                        ecID = null;
                    } else if (currentStatus.equals(status)) {
                        System.out.println(currentStatus + ", " + status);
                        WebElement elementSub = listOfECIDLinks.get(maxNumberOfTimesToIterate - count);
                        ecID = elementSub.getText();
                        break;
                    }
                }

                if (count > maxNumberOfTimesToIterate) {
                    break;
                }
            } while (ecID == null);
            return ecID;
        } else {
            return null;
        }
    }


    public String getARandomNotification(int maxNumberOfTimesToIterate) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//h2[.='Uploaded On']//following::a[2]"), 5);
        if (listOfECIDLinks.size() > 0) {
            maxNumberOfTimesToIterate = listOfECIDLinks.size();
            String ecID = null;
            int count = 0;
            do {
                count++;
                WebElement element = PageUtils.getRandomNotification(listOfECIDLinks);
                ecID = element.getText();
                if (ecID.contains("Next") || ecID.contains("Previous") || ecID.trim().equals("")) {
                    ecID = null;
                } else {
                    break;
                }

                if (count > maxNumberOfTimesToIterate) {
                    break;
                }
            } while (ecID == null);
            return ecID;
        } else {
            return null;
        }
    }

    public int getNotificationCount(String ecid) {
//        WaitUtils.isElementPartOfDomAdvanced2(driver, By.partialLinkText(ecid), 10, false);
//        WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(ecid), 5);
        new WebDriverWait(driver, TIMEOUT_DEFAULT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(ecid)));
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
        WebElement submitter = driver.findElement(By.xpath(".//a[.='" + ecid + "']//following::p[2]"));
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

    public int getTotalNotificationCount() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, totalCount, 10, false);
            String count = totalCount.getText();
            if (count != null) {
                count = count.replace("of", "").trim();
            }
            return Integer.parseInt(count);
        } catch (Exception e) {
            By by = By.xpath(".//*[@class='footer']//following::div/span[3]");
            WaitUtils.waitForElementToBeClickable(driver, by, 15, false);
            String count = driver.findElement(by).getText();
            return Integer.parseInt(count);
        }
    }

    public boolean isSearchResultsFor(String searchTermText) {
        WaitUtils.waitForElementToBeVisible(driver, searchResultsFor, 15, false);
        String text = PageUtils.getText(searchResultsFor).trim();
        boolean contains = searchTermText.contains(text);
        return contains;
    }
}

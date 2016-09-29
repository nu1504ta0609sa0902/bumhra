package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class NotificationsReport extends _Page {

    @FindBy(xpath = ".//*[.='Uploaded On']//following::a")
    List<WebElement> listOfLinks;
    @FindBy(xpath = ".//*[.='Uploaded On']//following::img[@aria-label='Next page']")
    WebElement nextPage;
    @FindBy(xpath = ".//*[.='Uploaded On']//following::img[@aria-label='Last page']")
    WebElement lastPage;

    @FindBy(xpath = ".//*[contains(@data-id, 'Count-of-Notifications')]")
    WebElement highChart;

    @FindBy(partialLinkText = "Show All Notifications")
    WebElement showAllNotifications;
    @FindBy(partialLinkText = "Hide Notifications")
    WebElement hideAllNotifications;


    @Autowired
    public NotificationsReport(WebDriver driver) {
        super(driver);
    }

    public boolean isHighChartDisplayed() {
        WaitUtils.waitForElementToBeClickable(driver, highChart, 5, false);
        boolean isDisplayed = highChart.isDisplayed();
        return isDisplayed;
    }

    public NotificationsReport showNotifications(boolean show){
        if(show){
            WaitUtils.waitForElementToBeClickable(driver, showAllNotifications, 5, false);
            PageUtils.doubleClick(driver, showAllNotifications);
            //showAllNotifications.click();
        }else{
            WaitUtils.waitForElementToBeClickable(driver, hideAllNotifications, 5, false);
            PageUtils.doubleClick(driver,hideAllNotifications);
            //hideAllNotifications.click();
        }
        return new NotificationsReport(driver);
    }

    public boolean isNotificationsSectionDisplayed() {
        WaitUtils.waitForElementToBeClickable(driver, nextPage, 10, false);
        boolean isDisplayed = nextPage.isDisplayed() && lastPage.isDisplayed();
        return isDisplayed;
    }

    public boolean isShowNotificationsLinkDisplayed() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, showAllNotifications, 10, false);
            boolean isDisplayed = showAllNotifications.isDisplayed() ;
            return isDisplayed;
        }catch (Exception e){
            return false;
        }
    }
}

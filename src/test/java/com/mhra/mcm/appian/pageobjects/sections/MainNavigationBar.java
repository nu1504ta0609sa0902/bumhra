package com.mhra.mcm.appian.pageobjects.sections;

import com.mhra.mcm.appian.pageobjects.*;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
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
public class MainNavigationBar extends _Page {

    @FindBy(partialLinkText = "News")
    WebElement news;
    @FindBy(partialLinkText = "Tasks")
    WebElement tasks;
    @FindBy(partialLinkText = "Records")
    WebElement records;
    @FindBy(partialLinkText = "Reports")
    WebElement reports;
    @FindBy(partialLinkText = "Actions")
    WebElement actions;

    @FindBy(css = ".appian-menu-item.appian-menu-item-selected")
    WebElement currentSelection;


    @Autowired
    public MainNavigationBar(WebDriver driver) {
        super(driver);
    }

    public NewsPage clickNews() {
        //try {
            WaitUtils.waitForElementToBeClickable(driver, news, 30, false);
            PageUtils.doubleClick(driver, news);
            //news.click();
            return new NewsPage(driver);
//        } catch (Exception e) {
//            return null;
//        }
    }

    public TasksPage clickTasks() {
        //try {
            WaitUtils.waitForElementToBeClickable(driver, tasks, 30, false);
            PageUtils.doubleClick(driver, tasks);
            //tasks.click();
            return new TasksPage(driver);
//        } catch (Exception e) {
//            return null;
//        }
    }

    public RecordsPage clickRecords() {
        //try {
            WaitUtils.waitForElementToBeClickable(driver, records, 30, false);
            PageUtils.doubleClick(driver, records);
            //records.click();
            return new RecordsPage(driver);
//        } catch (Exception e) {
//            return null;
//        }
    }

    public ReportsPage clickReports() {
        //try {
            WaitUtils.waitForElementToBeClickable(driver, reports, 30, false);
            PageUtils.doubleClick(driver, reports);
            //reports.click();
            return new ReportsPage(driver);
//        } catch (Exception e) {
//            return null;
//        }
    }

    public String getCurrentSelectedMenu() {
        //try {
            WaitUtils.waitForElementToBeClickable(driver, currentSelection, 30, false);
            String selectedMenu = currentSelection.getText();
            return selectedMenu;
//        } catch (Exception e) {
//            return null;
//        }
    }

    public ActionsPage clickActions() {
        //try {
            WaitUtils.waitForElementToBeClickable(driver, actions, 30);
            PageUtils.doubleClick(driver, actions);
            //actions.click();
            return new ActionsPage(driver);
//        } catch (Exception e) {
//            return null;
//        }
    }


}

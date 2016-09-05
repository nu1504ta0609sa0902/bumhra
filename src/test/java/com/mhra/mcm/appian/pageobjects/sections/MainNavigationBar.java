package com.mhra.mcm.appian.pageobjects.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.pageobjects.ActionsPage;
import com.mhra.mcm.appian.pageobjects.NewsPage;
import com.mhra.mcm.appian.pageobjects.RecordsPage;
import com.mhra.mcm.appian.pageobjects.ReportsPage;
import com.mhra.mcm.appian.pageobjects.TasksPage;
import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

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
        WaitUtils.waitForElementToBeClickable(driver, news, 5);
        PageUtils.doubleClick(driver, news);
        //news.click();
        return new NewsPage(driver);
    }

    public TasksPage clickTasks() {
        WaitUtils.waitForElementToBeClickable(driver, tasks, 5);
        PageUtils.doubleClick(driver, tasks);
        //tasks.click();
        return new TasksPage(driver);
    }

    public RecordsPage clickRecords() {
        WaitUtils.waitForElementToBeClickable(driver, records, 5);
        PageUtils.doubleClick(driver, records);
        //records.click();
        return new RecordsPage(driver);
    }

    public ReportsPage clickReports() {
        WaitUtils.waitForElementToBeClickable(driver, reports, 5);
        PageUtils.doubleClick(driver, reports);
        //reports.click();
        return new ReportsPage(driver);
    }

    public String getCurrentSelectedMenu() {
        WaitUtils.waitForElementToBeClickable(driver, currentSelection, 5);
        String selectedMenu = currentSelection.getText();
        return selectedMenu;
    }

    public ActionsPage clickActions() {
        WaitUtils.waitForElementToBeClickable(driver, actions, 5);
        PageUtils.doubleClick(driver, actions);
        //actions.click();
        return new ActionsPage(driver);
    }

    
}

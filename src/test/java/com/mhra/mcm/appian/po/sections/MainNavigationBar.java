package com.mhra.mcm.appian.po.sections;

import com.mhra.mcm.appian.po.*;
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


    @Autowired
    public MainNavigationBar(WebDriver driver) {
        super(driver);
    }

    public NewsPage clickNews() {
        WaitUtils.waitForElementToBeClickable(driver, news, 5);
        news.click();
        return new NewsPage(driver);
    }

    public TasksPage clickTasks() {
        WaitUtils.waitForElementToBeClickable(driver, tasks, 5);
        tasks.click();
        return new TasksPage(driver);
    }

    public RecordsPage clickRecords() {
        WaitUtils.waitForElementToBeClickable(driver, records, 5);
        records.click();
        return new RecordsPage(driver);
    }

    public ReportsPage clickReports() {
        WaitUtils.waitForElementToBeClickable(driver, reports, 5);
        reports.click();
        return new ReportsPage(driver);
    }

    public ActionsPage clickActions() {
        WaitUtils.waitForElementToBeClickable(driver, actions, 5);
        actions.click();
        return new ActionsPage(driver);
    }
}

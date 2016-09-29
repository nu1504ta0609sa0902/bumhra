package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public class Documents extends _Page {

    @FindBy(xpath = ".//*[.='Documents']")
    WebElement documentSection;

    @FindBy(xpath = ".//*[.='Active']//following::a")
    List<WebElement> listOfReports;


    @Autowired
    public Documents(WebDriver driver) {
        super(driver);
    }


    public boolean isCorrectPage() {
        WaitUtils.waitForElementToBeClickable(driver, documentSection, 10, false);
        boolean isDisplayed = documentSection.isDisplayed();
        return isDisplayed;
    }

    public boolean isNumberOfDocumentsDisplayedCorrect(int count) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//*[.='Active']//following::a"), 10, false);
        boolean matchedCount = listOfReports.size() >= count;
        return matchedCount;
    }

    public boolean isDocumentsDisplayedFor(String reportName) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//*[.='Active']//following::a"), 10, false);
        WebElement name = driver.findElement(By.partialLinkText(reportName));
        boolean containsName = name.getText().contains(reportName);
        return containsName;
    }
}

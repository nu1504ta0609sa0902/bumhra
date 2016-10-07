package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects._Page;
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
public class AssessmentReport extends _Page {

    @FindBy(xpath = ".//div[.='Banned Substance Check']//following::td[2]")
    WebElement actualValueBannedSubstanceCheck;
    @FindBy(xpath = ".//div[.='Banned Product Name Check']//following::td[2]")
    WebElement actualValueBannedProductNameCheck;
    @FindBy(xpath = ".//div[.='Toxicology Report Check']//following::td[2]")
    WebElement actualValueToxicologyReportCheck;

    @Autowired
    public AssessmentReport(WebDriver driver) {
        super(driver);
    }

    public boolean isBusinessRuleAssessmentReportDisplayed() {
        WaitUtils.waitForElementToBeClickable(driver, actualValueBannedProductNameCheck, 10, false);
        boolean bannedSubstanceCheckDisplayed = actualValueBannedSubstanceCheck.isDisplayed();
        boolean bannedProductNameCheckDisplayed = actualValueBannedProductNameCheck.isDisplayed();
        boolean toxicologyReportCheckDisplayed = actualValueToxicologyReportCheck.isDisplayed();

        return bannedSubstanceCheckDisplayed && bannedProductNameCheckDisplayed && toxicologyReportCheckDisplayed;
    }
}

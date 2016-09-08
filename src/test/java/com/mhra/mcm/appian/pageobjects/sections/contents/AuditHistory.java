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
public class AuditHistory extends _Page {

    @FindBy(xpath = ".//*[.='Status']//following::p[1]")
    WebElement status;

    @Autowired
    public AuditHistory(WebDriver driver) {
        super(driver);
    }


    public boolean isStatus(String paid) {
        WaitUtils.waitForElementToBeClickable(driver, status, 5, false);
        boolean matched = status.getText().equals(paid);
        return matched;
    }
}

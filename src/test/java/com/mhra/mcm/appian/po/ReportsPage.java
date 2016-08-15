package com.mhra.mcm.appian.po;

import com.mhra.mcm.appian.po.sections.contents.Exceptions;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ReportsPage extends _Page {
    @FindBy(linkText = "Exceptions")
    WebElement exceptionsLink;

    @Autowired
    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    public Exceptions gotoExceptionsPage() {
        WaitUtils.waitForElementToBeClickable(driver, exceptionsLink, 5, false);
        exceptionsLink.click();
        return new Exceptions(driver);
    }
}

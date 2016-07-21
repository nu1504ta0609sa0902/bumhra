package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.po.RecordsPage;
import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 20/07/2016.
 */
@Component
public class EditNotification extends _Page {

    @FindBy(xpath = ".//form/div[2]/div/div/div/div/div/div/div[3]/div[3]/div/div/div[1]/div/div/div[1]/div/div/div/div/div/input")
    WebElement submitterName;

    @Autowired
    public EditNotification(WebDriver driver) {
        super(driver);
    }

    public String appendTextToSubmitterName(String append) {
        WaitUtils.waitForElementToBeClickable(driver, submitterName, 5);
        String existingName = submitterName.getText();
        submitterName.clear();
        submitterName.sendKeys(existingName + append);
        return existingName;
    }

    public RecordsPage submitChanges() {
        submitterName.submit();
        return new RecordsPage(driver);
    }
}

package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.po.ActionsPage;
import com.mhra.mcm.appian.po.RecordsPage;
import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 20/07/2016.
 */
@Component
public class EditNotification extends _Page {

    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement submitterName;

    @Autowired
    public EditNotification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String appendTextToSubmitterName(String append) {
        WaitUtils.waitForElementToBeVisible(driver, submitterName, 5);
        String existingName = WaitUtils.getText(submitterName);
        submitterName.clear();
        submitterName.sendKeys(existingName + append);
        return existingName;
    }

    public RecordsPage submitChanges() {
        submitterName.submit();
        return new RecordsPage(driver);
    }
}

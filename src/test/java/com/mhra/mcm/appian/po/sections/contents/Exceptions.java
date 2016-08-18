package com.mhra.mcm.appian.po.sections.contents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class Exceptions extends _Page {

    @Autowired
    public Exceptions(WebDriver driver) {
        super(driver);
    }

    public boolean isNotificationDisplayed(String ecId) {
        boolean found = false;
        int attempt = 0;
        do {
            attempt++;

            try {
                WaitUtils.waitForElementToBeClickable(driver, By.linkText(ecId), 7, false);
                WebElement notification = driver.findElement(By.linkText(ecId));
                notification.click();
                found = true;
                break;
            }catch(Exception e){
            }

            //refresh page
            if(!found) {
                driver.navigate().refresh();
                PageFactory.initElements(driver, this);
            }

        }while(!found && attempt < 5);

        return found;
    }
}

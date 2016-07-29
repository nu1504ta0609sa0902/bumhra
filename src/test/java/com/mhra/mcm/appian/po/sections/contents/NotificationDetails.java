package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.po.RecordsPage;
import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 26/07/2016.
 */
@Component
public class NotificationDetails extends _Page {

    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement submitterName;

    @FindBy(css = ".GJEWJWHDFR")
    WebElement header;
    @FindBy(css = ".GJEWJWHDCP .GJEWJWHDIP")
    WebElement currentStatus;

    @Autowired
    public NotificationDetails(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean headerContainsID(String expectedNotificationID) {
        WaitUtils.waitForElementToBeClickable(driver, header, 5);
        return header.getText().contains(expectedNotificationID);
    }

    public String getCurrentStatus() {
        WaitUtils.waitForElementToBeClickable(driver, currentStatus, 5);
        return currentStatus.getText();
    }

    public boolean hasPageStatusChanged(String currentStatusText) {

        boolean found = false;
        WaitUtils.waitForElementToBeVisible(driver, currentStatus, 5);
        String updatedStatus = getCurrentStatus();
        if(!updatedStatus.equals(currentStatusText)){
            found = true;
        }else{
            driver.navigate().refresh();
            PageFactory.initElements(driver, this);
        }

//        boolean found = false;
//        int attempt = 0;
//        do {
//            attempt++;
//
//            try {
//                WaitUtils.waitForElementToBeVisible(driver, currentStatus, 5);
//                String updatedStatus = getCurrentStatus();
//                if(!updatedStatus.equals(currentStatusText)){
//                    found = true;
//                }
//            }catch(Exception e){
//            }
//
//            //refresh page
//            driver.navigate().refresh();
//            PageFactory.initElements(driver, this);
//
//        }while(!found && attempt < 5);

        return found;
    }
}

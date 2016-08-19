package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;

import java.util.List;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class Exceptions extends _Page {

    @FindBy(xpath = ".//*[.='Comments']//following::a")
    List<WebElement> listOfECIDLinks;

    @FindBy(xpath = ".//*[contains(@aria-label, 'Next page')]")
    WebElement nextPage;

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
                //PageFactory.initElements(driver, this);
                //It may be on the next page
                WaitUtils.waitForElementToBeClickable(driver, nextPage, 7, false);
                PageUtils.doubleClick(driver, nextPage);
                PageFactory.initElements(driver, this);
            }

        }while(!found && attempt < 5);

        return found;
    }


    public String selectARandomException() {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//*[.='Comments']//following::a[1]"), 5);
        if(listOfECIDLinks.size() > 0){
            WebElement element = listOfECIDLinks.get(listOfECIDLinks.size()-1);
            String ecID = element.getText();
            return ecID;
        }else{
            return null;
        }
    }

    public NotificationDetails clickOnNotificationWithEcid(String ecid){

        boolean nextPageChecked = false;
        int count = 0;
        do {
            try {
                WaitUtils.waitForElementToBeClickable(driver, By.linkText(ecid), 7, false);
                WebElement notification = driver.findElement(By.linkText(ecid));
                PageUtils.doubleClick(driver, notification);
                break;
            }catch(Exception e){
                //It may be on the next page
                WaitUtils.waitForElementToBeClickable(driver, nextPage, 7, false);
                PageUtils.doubleClick(driver, nextPage);
                PageFactory.initElements(driver, this);
                count++;

                if(count >= 2)
                    nextPageChecked = true;
            }
        }while(!nextPageChecked);
        //notification.click();
        return new NotificationDetails(driver);
    }
}

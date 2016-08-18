package com.mhra.mcm.appian.utils.helpers.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.mhra.mcm.appian.utils.helpers.WaitUtils;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class PageUtils {


    public static void selectByText(WebElement selectElement, String visibleText) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(visibleText);

        System.out.println("Selected Option : " + visibleText);
    }

    public static void selectByIndex(WebElement selectElement, String index) {
        Select select = new Select(selectElement);
        int i = Integer.parseInt(index);
        select.selectByIndex(i);
    }

    public static void clickOption(WebElement option1, WebElement option2, boolean status) {
        if(status){
            option1.click();
        }else{
            option2.click();
        }
    }

    public static void clickOptionAdvanced(WebDriver driver, WebElement option1, WebElement option2, boolean status) {
        if(status){
            clickIfVisible(driver, option1);
        }else{
            clickIfVisible(driver, option2);
        }
    }

    public static void enterDate(WebDriver driver, WebElement element, String dateTxt) {
        //element.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().sendKeys(dateTxt).build().perform();
    }

    public static void doubleClick(WebDriver driver, WebElement element) {
        Actions ac = new Actions(driver);
        ac.moveToElement(element).doubleClick(element).build().perform();
    }

    public static void singleClick(WebDriver driver, WebElement element) {
        Actions ac = new Actions(driver);
        ac.moveToElement(element).click(element).build().perform();
    }

    public static void clickIfVisible(WebDriver driver, WebElement element) {
        try{
            //IE sometimes doesn't click the element
            element.sendKeys(Keys.SPACE);
        }catch(Exception e){
            try {
                if (element.isDisplayed() && !element.isSelected()) {
                    Actions ac = new Actions(driver);
                    ac.moveToElement(element).doubleClick().sendKeys(Keys.SPACE).build().perform();
                }
            }catch(Exception e2){}
        }
    }

    public static void typeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static void uploadDocument(WebElement element, String fileName){
        WaitUtils.nativeWait(2);
        element.sendKeys(fileName);
        //We will have to wait for uploading to finish
        WaitUtils.nativeWait(6);
    }

}

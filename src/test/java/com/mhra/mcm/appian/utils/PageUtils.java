package com.mhra.mcm.appian.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class PageUtils {
    public static void select(WebDriver driver, WebElement selectElement, String index) {
        Select select = new Select(selectElement);
        int i = Integer.parseInt(index);
        select.selectByIndex(i);
    }

    public static void clickOption(WebElement option1, WebElement option2, boolean status) {
        if(status){
            option1.click();
            //option2.sendKeys(Keys.SPACE);
        }else{
            option2.click();
            //option2.sendKeys(Keys.SPACE);
        }
    }

    public static void enterDate(WebDriver driver, WebElement element, String dateTxt) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(dateTxt);
        actions.build().perform();
    }
}

package com.mhra.mcm.appian.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public static void selectOption(WebElement option1, WebElement option2, boolean status) {
        if(status){
            option1.click();
        }else{
            option2.click();
        }
    }
}

package com.mhra.mcm.appian.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by TPD_Auto on 15/07/2016.
 */
public class WaitUtils {

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int maxTimeToWait) {
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.elementToBeClickable(element));
    }
}

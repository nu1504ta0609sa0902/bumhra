package com.mhra.mcm.appian.utils.helpers.page;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by TPD_Auto on 15/07/2016.
 */
public class WaitUtils {

    //mhratest is very slow, set this to 0 once slow issue is fixed
    static int timeForTesting = 60;

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int maxTimeToWait) {
        maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By by,  int maxTimeToWait) {
        maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int maxTimeToWait) {
        maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeVisible(WebDriver driver, By by,  int maxTimeToWait) {
        maxTimeToWait = resetMaxTime(maxTimeToWait);
        WebElement element = driver.findElement(by);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.visibilityOf(element));
    }

    private static int resetMaxTime(int maxTimeToWait) {
        if(timeForTesting > 0){
            maxTimeToWait = timeForTesting;
        }
        return maxTimeToWait;
    }

    /**
     * Should be used for non selenium related tasks
     *
     * Example when we upload a document
     * @param tis
     */
    public static void nativeWait(int tis) {
        try {
            Thread.sleep(1000*tis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param driver
     * @param by
     * @param maxTimeToWait
     * @param overrideTimeSpecified
     */
    public static void waitForElementToBeClickable(WebDriver driver, By by, int maxTimeToWait, boolean overrideTimeSpecified) {
        if(overrideTimeSpecified)
        maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     *
     * @param driver
     * @param element
     * @param maxTimeToWait
     * @param overrideTimeSpecified
     */
    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int maxTimeToWait, boolean overrideTimeSpecified) {
        if(overrideTimeSpecified)
            maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int maxTimeToWait, boolean overrideTimeSpecified) {
        if(overrideTimeSpecified)
            maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForAlert(WebDriver driver, int maxTimeToWait, boolean overrideTimeSpecified) {
        if(overrideTimeSpecified)
            maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.alertIsPresent());
    }

    public static void setImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }



    public static void isElementPartOfDom(WebDriver driver, By by, int maxTimeToWait, boolean overrideTimeSpecified) {
        if(overrideTimeSpecified)
            maxTimeToWait = resetMaxTime(maxTimeToWait);
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public static void isElementPartOfDomAdvanced(WebDriver driver, final WebElement element, int maxTimeToWait, boolean overrideTimeSpecified) {
        if(overrideTimeSpecified)
            maxTimeToWait = resetMaxTime(maxTimeToWait);

        new WebDriverWait(driver, maxTimeToWait)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        boolean displayed = element.isDisplayed();
                        return displayed;
                    }
                });
    }


    public static void isElementPartOfDomAdvanced2(WebDriver driver, final By by, int maxTimeToWait, boolean overrideTimeSpecified) {
        if(overrideTimeSpecified)
            maxTimeToWait = resetMaxTime(maxTimeToWait);

        new WebDriverWait(driver, maxTimeToWait)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        WebElement element = driver.findElement(by);
                        return element!=null;
                    }
                });
    }
}

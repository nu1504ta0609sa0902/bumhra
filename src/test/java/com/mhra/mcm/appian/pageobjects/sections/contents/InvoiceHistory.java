package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.others.GenericUtils;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class InvoiceHistory extends _Page {

    @FindBy(xpath = ".//*[.='File Name']//following::div/a")
    List<WebElement> listOfHisoricalInvoices;

    @FindBy(xpath = ".//*[.='Invoice Type']//following::input[1]")
    WebElement standard;
    @FindBy(xpath = ".//*[.='Invoice Type']//following::input[2]")
    WebElement annual;
    @FindBy(xpath = ".//*[.='Creation Date']//following::input[1]")
    WebElement creationDate;
    @FindBy(xpath = ".//*[.='Invoice Files for Download']")
    WebElement heading;


    @FindBy(xpath = ".//*[contains(@aria-label, 'Next page')]")
    WebElement nextPage;
    @FindBy(xpath = ".//*[contains(@aria-label, 'Previous page')]")
    WebElement prevPage;
    @FindBy(xpath = ".//*[contains(@aria-label, 'First page')]")
    WebElement firstPage;
    @FindBy(xpath = ".//*[contains(@aria-label, 'Last page')]")
    WebElement lastPage;


    @Autowired
    public InvoiceHistory(WebDriver driver) {
        super(driver);
    }

    public int numberOfInvoicesDisplayed() {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath( ".//*[.='Invoice Type']//following::input[1]" ), 5, false );
        int size = listOfHisoricalInvoices.size();
        return size;
    }

    public InvoiceHistory filterByInvoiceType(boolean isStandard) {
        if(isStandard){
            WaitUtils.waitForElementToBeClickable(driver, standard, 5, false);
            standard.click();
        }else{
            WaitUtils.waitForElementToBeClickable(driver, annual, 5, false);
            annual.click();
        }
        return new InvoiceHistory(driver);
    }

    public InvoiceHistory filterByTodaysDate() {
        WaitUtils.waitForElementToBeClickable(driver, creationDate, 5, false);
        String dateInFutureMonths = RandomDataUtils.getDateInFutureMonths(0);
        creationDate.sendKeys(dateInFutureMonths);
        heading.click();
        return new InvoiceHistory(driver);
    }

    public boolean isLastInvoiceWithinTimeSpecified(double time) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath( ".//*[.='Invoice Type']//following::input[1]" ), 5, false );
        WebElement recentInvoice = listOfHisoricalInvoices.get(0);
        String invoiceFileName = recentInvoice.getText();
        invoiceFileName = invoiceFileName.substring(invoiceFileName.indexOf(":")-2, invoiceFileName.indexOf("GMT")-1);
        String[] dataTime = invoiceFileName.split("\\:");
        boolean isInvoiceWithinTimeLimit = GenericUtils.isRecentData(time, dataTime[0], dataTime[1]);
        return  isInvoiceWithinTimeLimit;
    }
}

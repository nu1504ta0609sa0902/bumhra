package com.mhra.mcm.appian.pageobjects.sections.filters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.pageobjects.RecordsPage;
import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class RecordsFilter extends _Page {

    @FindBy(linkText = "Uploaded")
    WebElement uploaded;
    @FindBy(linkText = "Ready for Payment")
    WebElement readyForPayment;
    @FindBy(linkText = "Paid")
    WebElement paid;
    @FindBy(linkText = "Unpaid")
    WebElement unpaid;
    @FindBy(linkText = "Failed")
    WebElement failed;
    @FindBy(linkText = "Successful")
    WebElement successful;
    @FindBy(linkText = "Quality Assurance")
    WebElement qualityAssurance;
    @FindBy(linkText = "Published")
    WebElement published;
    @FindBy(partialLinkText = "More..")
    WebElement more;

    @FindBy(linkText = "Last 30 days")
    WebElement last30Days;
    @FindBy(linkText = "Last 60 days")
    WebElement last60Days;
    @FindBy(linkText = "Last 90 days")
    WebElement last90Days;
    @FindBy(linkText = "Last 180 days")
    WebElement last180Days;

    @FindBy(linkText = "<A")
    WebElement lessThanA;
    @FindBy(linkText = ">A")
    WebElement greaterThanA;
    @FindBy(linkText = "A-L")
    WebElement aToL;
    @FindBy(linkText = "M-Z")
    WebElement mToZ;

    @FindBy(css = "li.selected a")
    List<WebElement> listOfSelectedFilters;

    @Autowired
    public RecordsFilter(WebDriver driver) {
        super(driver);
    }

    public RecordsPage filterByStatus(String filterByStatus) {
        if(filterByStatus!=null){
            WebElement element = null;
            switch (filterByStatus){
                case "Uploaded":
                    element = uploaded;break;
                case "Ready for Payment":
                    element = readyForPayment;break;
                case "Paid":
                    element = paid;break;
                case "Unpaid":
                    element = unpaid;break;
                case "Failed":
                    element = failed;break;
                case "Successful":
                    element = successful;break;
                case "Quality Assurance":
                    element = qualityAssurance;break;
                case "Published":
                    element = published;break;
                case "Last 30 days":
                    element = last30Days;break;
                case "Last 60 days":
                    element = last60Days;break;
                case "Last 90 days":
                    element = last90Days;break;
                case "Last 180 days":
                    element = last180Days;break;
                case "<A":
                    element = lessThanA;break;
                case "A-L":
                    element = aToL;break;
                case "M-Z":
                    element = mToZ;break;
                case ">A":
                    element = greaterThanA;break;
            }

            if(element!=null){
                WaitUtils.waitForElementToBeClickable(driver, element, 20, false);
                element.click();
            }
        }

        return new RecordsPage(driver);
    }

    public RecordsFilter expand() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, more, 20, false);
            more.click();
        }catch (Exception e){
            //More link may not be visible
        }
        return new RecordsFilter(driver);
    }

    public RecordsFilter clearSelection() {
        if(listOfSelectedFilters.size() > 0){
            //Clear selection
            listOfSelectedFilters.get(0).click();
        }
        return new RecordsFilter(driver);
    }


    public RecordsPage clickFilterText(String filter) {
        By by = By.linkText(filter);
        WaitUtils.waitForElementToBeClickable(driver, by, 20, false);
        WebElement element = driver.findElement(by);
        PageUtils.doubleClick(driver, element);
        return new RecordsPage(driver);
    }

    public RecordsPage clearSelection(String filter) {
        By selected = By.cssSelector("li.selected");
        WaitUtils.waitForElementToBeClickable(driver, selected, 20, false);
        //By by = By.xpath(".//*[.='"+filter+"']/a[1]");
        WebElement element = driver.findElement(selected);
        PageUtils.doubleClick(driver, element);
        return new RecordsPage(driver);
    }

    public void waitForOptionsToBeClickable() {
        WaitUtils.waitForElementToBeClickable(driver, uploaded, 20, false);
    }
}

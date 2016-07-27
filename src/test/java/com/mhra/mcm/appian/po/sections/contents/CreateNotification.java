package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.domain.sub.*;
import com.mhra.mcm.appian.po.ActionsPage;
import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.PageUtils;
import com.mhra.mcm.appian.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
@Component
public class CreateNotification extends _Page {

    //Summary
    @FindBy(xpath = ".//label[.='EC ID']//following::input[1]")
    WebElement ecId;
    @FindBy(xpath = ".//label[.='Start Date']//following::input[1]")
    WebElement startDate;
    @FindBy(xpath = ".//label[.='End Date']//following::input[1]")
    WebElement endDate;
    @FindBy(xpath = ".//span[.='Submission Type']//following::select[1]")
    WebElement submissionType;
    @FindBy(xpath = ".//span[.='Status']//following::select[1]")
    WebElement status;

    //Submitter details
    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement name;
    @FindBy(xpath = ".//label[.='EU Identifier']//following::input[1]")
    WebElement euIdentifier;
    @FindBy(xpath = ".//label[.='TCA Number']//following::input[1]")
    WebElement tcaNumber;
    @FindBy(xpath = ".//span[.='SME']//following::input[1]")
    WebElement smeYes;
    @FindBy(xpath = ".//span[.='SME']//following::input[2]")
    WebElement smeNo;
    @FindBy(xpath = ".//span[.='Confidential']//following::input[1]")
    WebElement confidentialYes;
    @FindBy(xpath = ".//span[.='Confidential']//following::input[2]")
    WebElement confidentialNo;
    @FindBy(xpath = ".//span[.='Submitter Type']//following::select[1]")
    WebElement submitterType;

    //Submitter Details
    @FindBy(xpath = ".//span[.='Has VAT?']//following::input[1]")
    WebElement hasVATYes;
    @FindBy(xpath = ".//span[.='Has VAT?']//following::input[2]")
    WebElement hasVatNo;
    @FindBy(xpath = ".//span[.='Has Enterer?']//following::input[1]")
    WebElement hasEntererYes;
    @FindBy(xpath = ".//span[.='Has Enterer?']//following::input[2]")
    WebElement hasEntererNo;
    @FindBy(xpath = ".//span[.='Has Parent?']//following::input[1]")
    WebElement hasParentYes;
    @FindBy(xpath = ".//span[.='Has Parent?']//following::input[2]")
    WebElement hasParentNo;
    @FindBy(xpath = ".//span[.='Has Affiliate?']//following::input[1]")
    WebElement hasAffiliateYes;
    @FindBy(xpath = ".//span[.='Has Affiliate?']//following::input[2]")
    WebElement hasAffiliateNo;

    //Product
    @FindBy(xpath = ".//label[.='Brand Name']//following::input[1]")
    WebElement productName;
    @FindBy(xpath = ".//label[.='Launch Date']//following::input[1]")
    WebElement launchDate;
    @FindBy(xpath = ".//span[.='Type']//following::select[1]")
    WebElement type;

    //Product design
    @FindBy(xpath = ".//label[.='Weight E-Liquid']//following::input[1]")
    WebElement liquidWeight;
    @FindBy(xpath = ".//label[.='Volume E-Liquid']//following::input[1]")
    WebElement liquidVolumne;

    //Fill address
    @FindBy(xpath = ".//span[.='Available Addresses']//following::input[1]")
    WebElement address;
    @FindBy(xpath = ".//span[.='Available Addresses']//following::select[1]")
    WebElement country;
    @FindBy(xpath = ".//span[.='Available Addresses']//following::input[2]")
    WebElement phone;
    @FindBy(xpath = ".//span[.='Available Addresses']//following::input[3]")
    WebElement email;
    @FindBy(xpath = ".//span[.='Available Addresses']//following::input[5]")
    WebElement productionSiteNo;
    @FindBy(xpath = ".//span[.='Available Addresses']//following::input[7]")
    WebElement addressConfidentialNo;

    //submit button
    @FindBy(xpath = ".//label[.='UPC Number']//following::input[1]")
    WebElement upcNumber;
    //@FindBy(xpath = ".//button[.='Submit']")
    @FindBy(css = ".buttonContainer .right button")
    WebElement submitBtn;
    @FindBy(css = ".buttonContainer")
    WebElement page;

    @Autowired
    public CreateNotification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public ActionsPage createRandomNotification(Notification notification) {
        WaitUtils.waitForElementToBeClickable(driver, ecId, 10);
        String prevUrl = driver.getCurrentUrl();
        log.info("Current URL : " + prevUrl);

        //Fill notification details
        fillSummary(notification.getSummary());
        fillSubmitter(notification.getSubmitter());
        fillSubmitterDetails(notification.getSubmitterDetails());
        fillProduct(notification.getProduct());
        fillProductDesign(notification.getProductDesign());

        //Now submit the notification and keep track of ecID
        page.click();
        PageUtils.doubleClick(driver, submitBtn);
        return new ActionsPage(driver);
    }

    private void fillProductDesign(ProductDesign productDesign) {
        liquidWeight.sendKeys(String.valueOf(productDesign.weightELiquid));
        liquidVolumne.sendKeys(String.valueOf(productDesign.volumeELiquid));
    }

    private void fillProduct(Product product) {
        productName.sendKeys(product.brandName);
        //launchDate.sendKeys(product.launchDate);
        PageUtils.enterDate(driver, launchDate, product.launchDate);
        PageUtils.selectByIndex(type, product.type);
    }

    private void fillSubmitterDetails(SubmitterDetails submitterDetails) {

        PageUtils.clickOption(hasEntererYes, hasEntererNo, submitterDetails.hasEnterer);
        WaitUtils.waitForElementToBeVisible(driver, hasEntererYes, 5);

        WaitUtils.waitForElementToBeVisible(driver, hasAffiliateYes, 5);
        PageUtils.clickOption(hasAffiliateYes, hasAffiliateNo, submitterDetails.hasAffiliate);
        WaitUtils.waitForElementToBeVisible(driver, hasAffiliateYes, 5);

        WaitUtils.waitForElementToBeVisible(driver, hasParentYes, 5);
        PageUtils.clickOption(hasParentYes, hasParentNo, submitterDetails.hasParent);
        WaitUtils.waitForElementToBeVisible(driver, hasParentYes, 5);

        PageUtils.clickOption(hasVATYes, hasVatNo, submitterDetails.hasVAT);
        if(submitterDetails.hasVAT){
            WaitUtils.waitForElementToBeClickable(driver,By.xpath(".//label[.='VAT']//following::input[1]"), 5 );
            WebElement vatField = driver.findElement(By.xpath(".//label[.='VAT']//following::input[1]"));
            vatField.sendKeys(submitterDetails.vatNumber);
        }
    }

    private void fillSubmitter(Submitter submitter) {
        name.sendKeys(submitter.name);
        euIdentifier.sendKeys(submitter.euIdentifier);
        tcaNumber.sendKeys(submitter.tcaNumber);
        PageUtils.selectByIndex(submitterType, submitter.submitterType);
        PageUtils.clickOption(smeYes, smeNo, submitter.sme);
        PageUtils.clickOption(confidentialYes, confidentialNo, submitter.confidential);

        //set address
        Address add = submitter.listOfAddresses.get(0);
        address.click();
        address.sendKeys(add.address);
        phone.sendKeys(add.phone);
        email.sendKeys(add.email);
        PageUtils.clickOption(productionSiteNo, productionSiteNo, false);
        PageUtils.clickOption(addressConfidentialNo, addressConfidentialNo, false);

        PageUtils.selectByText(country, add.countryName);
        //PageUtils.selectByIndex(country, add.country);
    }

    private void fillSummary(Summary summary) {
//        WaitUtils.waitForElementToBeVisible(driver, startDate, 5);
//        startDate.clear();
//        startDate.sendKeys(summary.startDate);
        PageUtils.enterDate(driver, startDate, summary.startDate);
        ecId.click();
        ecId.sendKeys(summary.ecId);
        PageUtils.selectByIndex(submissionType, summary.submissionType);
        PageUtils.selectByIndex(status, summary.status);
        PageUtils.enterDate(driver, endDate, summary.endDate);
    }
}

package com.mhra.mcm.appian.po.sections.contents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.domain.sub.Address;
import com.mhra.mcm.appian.domain.sub.Ingredient;
import com.mhra.mcm.appian.domain.sub.Product;
import com.mhra.mcm.appian.domain.sub.ProductDesign;
import com.mhra.mcm.appian.domain.sub.Submitter;
import com.mhra.mcm.appian.domain.sub.SubmitterDetails;
import com.mhra.mcm.appian.domain.sub.Summary;
import com.mhra.mcm.appian.po.ActionsPage;
import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;

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

    //Add ingredient
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[3]")
    WebElement ingredientName;
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[4]")
    WebElement casExistsYes;
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[5]")
    WebElement getCasExistsNo;
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[6]")
    WebElement casNumber;
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[7]")
    WebElement fema;
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[8]")
    WebElement additive;
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[9]")
    WebElement flNumber;
    @FindBy(xpath = ".//*[.='Ingredients']//following::input[10]")
    WebElement ecNumber;
    @FindBy(xpath = ".//*[.='Ingredients']//following::select[1]")
    WebElement toxicity;
    @FindBy(xpath = ".//*[.='Ingredients']//following::select[2]")
    WebElement nonVapourisedStatus;


    //submit button
    @FindBy(xpath = ".//label[.='UPC Number']//following::input[1]")
    WebElement upcNumber;
    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submitBtn;
    @FindBy(css = ".buttonContainer")
    WebElement page;

    public boolean ingredientAdded = false;

    @Autowired
    public CreateNotification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public ActionsPage createRandomNotification(Notification notification) {
        WaitUtils.waitForElementToBeClickable(driver, ecId, 10);
        //String prevUrl = driver.getCurrentUrl();
        //log.info("Current URL : " + prevUrl);

        //Fill notification details
        fillSummary(notification.getSummary());
        fillSubmitter(notification.getSubmitter());
        fillSubmitterDetails(notification.getSubmitterDetails());
        fillProduct(notification.getProduct());
        fillProductDesign(notification.getProductDesign());
        fillIngredients(notification.getIngredient());

        //Now submit the notification and keep track of ecID
        PageUtils.doubleClick(driver, submitBtn);
        return new ActionsPage(driver);
    }


    private void fillIngredients(Ingredient ingredientDetails) {
        String in = ingredientDetails.ingredientName;
        if(in!=null && !in.equals("")){
            //System.out.println("No ingredient");
            ingredientName.sendKeys(ingredientDetails.ingredientName);
            casNumber.sendKeys(ingredientDetails.cASNumber);
            fema.sendKeys(ingredientDetails.FEMA);
            additive.sendKeys(ingredientDetails.additive);
            flNumber.sendKeys(ingredientDetails.fLNumber);
            ecNumber.sendKeys(ingredientDetails.eCNumber);

            //select from dropdown
            PageUtils.selectByIndex(toxicity, ingredientDetails.toxicity);
            PageUtils.selectByIndex(nonVapourisedStatus, ingredientDetails.nonVapourisedStatus);

            //Checkbox or radiobuttons
            PageUtils.clickOptionAdvanced(driver, casExistsYes, getCasExistsNo, ingredientDetails.casExists);

            ingredientAdded = true;
        }
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

        PageUtils.clickOptionAdvanced(driver, hasEntererYes, hasEntererNo, submitterDetails.hasEnterer);
        PageUtils.clickOptionAdvanced(driver, hasAffiliateYes, hasAffiliateNo, submitterDetails.hasAffiliate);
        PageUtils.clickOptionAdvanced(driver, hasParentYes, hasParentNo, submitterDetails.hasParent);

        PageUtils.clickOptionAdvanced(driver, hasVATYes, hasVatNo, submitterDetails.hasVAT);
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

        //set address : this should be changed to take into account multiple addresses
        Address add = submitter.listOfAddresses.get(0);
        WaitUtils.waitForElementToBeClickable(driver, address, 10);
        address.sendKeys(add.address);
        phone.sendKeys(add.phone);
        email.sendKeys(add.email);
        PageUtils.clickOptionAdvanced(driver, productionSiteNo, productionSiteNo, add.productionSite);
        PageUtils.clickOptionAdvanced(driver, addressConfidentialNo, addressConfidentialNo, add.addressConfidential);

        PageUtils.selectByText(country, add.countryName);
        //PageUtils.selectByIndex(country, add.country);
    }

    private void fillSummary(Summary summary) {
        PageUtils.enterDate(driver, startDate, summary.startDate);
        ecId.click();
        ecId.sendKeys(summary.ecId);
        PageUtils.selectByIndex(submissionType, summary.submissionType);
        PageUtils.selectByIndex(status, summary.status);
        PageUtils.enterDate(driver, endDate, summary.endDate);
    }
}

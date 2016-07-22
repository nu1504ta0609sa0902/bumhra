package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.domain.sub.Submitter;
import com.mhra.mcm.appian.domain.sub.SubmitterDetails;
import com.mhra.mcm.appian.domain.sub.Summary;
import com.mhra.mcm.appian.po._Page;
import com.mhra.mcm.appian.utils.PageUtils;
import com.mhra.mcm.appian.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    @FindBy(xpath = ".//span[.='Has VAT?']//following::input[1]")
    WebElement hasEntererYes;
    @FindBy(xpath = ".//span[.='Has VAT?']//following::input[2]")
    WebElement hasEntererNo;
    @FindBy(xpath = ".//span[.='Has Parent?']//following::input[1]")
    WebElement hasParentYes;
    @FindBy(xpath = ".//span[.='Has Parent?']//following::input[2]")
    WebElement hasParentNo;
    @FindBy(xpath = ".//span[.='Has Affiliate?']//following::input[1]")
    WebElement hasAffiliateYes;
    @FindBy(xpath = ".//span[.='Has Affiliate?']//following::input[2]")
    WebElement hasAffiliateNo;



    @Autowired
    public CreateNotification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void createRandomNotification(Notification notification) {
        WaitUtils.waitForElementToBeClickable(driver, ecId, 10);
        fillSummary(notification.getSummary());
        fillSubmitter(notification.getSubmitter());
        fillSubmitterDetails(notification.getSubmitterDetails());
    }

    private void fillSubmitterDetails(SubmitterDetails submitterDetails) {
        PageUtils.selectOption(hasVATYes, hasVatNo, submitterDetails.hasVAT);
        PageUtils.selectOption(hasEntererYes, hasEntererNo, submitterDetails.hasEnterer);
        PageUtils.selectOption(hasParentYes, hasParentNo, submitterDetails.hasParent);
        PageUtils.selectOption(hasAffiliateYes, hasAffiliateNo, submitterDetails.hasAffiliate);

        if(submitterDetails.hasVAT){
            WebElement vatField = driver.findElement(By.xpath(".//label[.='VAT']//following::input[1]"));
            vatField.sendKeys(submitterDetails.vatNumber);
        }
    }

    private void fillSubmitter(Submitter submitter) {
        name.sendKeys(submitter.name);
        euIdentifier.sendKeys(submitter.euIdentifier);
        PageUtils.select(driver, submitterType, submitter.submitterType);
        PageUtils.selectOption(smeYes, smeNo, submitter.sme);
        PageUtils.selectOption(confidentialYes, confidentialNo, submitter.confidential);
    }

    private void fillSummary(Summary summary) {
        startDate.sendKeys(summary.startDate);
        ecId.click();
        ecId.sendKeys(summary.ecId);
        PageUtils.select(driver, submissionType, summary.submissionType);
        PageUtils.select(driver, status, summary.status);
        endDate.sendKeys(summary.endDate);
    }
}

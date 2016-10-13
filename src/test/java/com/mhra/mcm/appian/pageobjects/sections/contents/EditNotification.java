package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.domain.webPagePojo.sub.Address;
import com.mhra.mcm.appian.domain.xmlPojo.EcigProductSubmission;
import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.others.FileUtils;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TPD_Auto on 20/07/2016.
 */
@Component
public class EditNotification extends _Page {

    //Summary
    @FindBy(xpath = ".//label[.='Start Date']//following::input[1]")
    WebElement startDate;
    @FindBy(xpath = ".//label[.='End Date']//following::input[1]")
    WebElement endDate;
    @FindBy(xpath = ".//label[.='EC ID']//following::input[1]")
    WebElement ecId;
    @FindBy(xpath = ".//label[.='Previous EC ID']//following::input[1]")
    WebElement previousEcId;
    @FindBy(xpath = ".//label[.='Name']//following::input[1]")
    WebElement submitterName;
    @FindBy(xpath = ".//span[.='Status']//following::select[1]")
    WebElement status;
    @FindBy(xpath = ".//span[.='Submission Type']//following::select[1]")
    WebElement submissionType;
    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submitBtn;

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

    //Add an address
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

    @FindBy(xpath = ".//*[.='Active']//following::tr")
    List <WebElement> listOfAttachementsReports;
    @FindBy(xpath = ".//*[.='Document Type']//following::select[1]")
    WebElement documentType;

    @FindBy(xpath = ".//a[contains(text(),'+ Add Address')]")
    WebElement addAddressLink;


    @Autowired
    public EditNotification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String appendTextToSubmitterName(String append) {
        WaitUtils.waitForElementToBeVisible(driver, submitterName, 5);
        String existingName = PageUtils.getText(submitterName);
        submitterName.clear();
        submitterName.sendKeys(existingName + append);
        return existingName;
    }

    public NotificationDetails submitChanges() {
        submitterName.submit();
        return new NotificationDetails(driver);
    }

    public NotificationDetails addGenericToxicologyReportFromTempFolder(String fileName, Notification random) {
        String fullPath = FileUtils.getFileFullPath("tmp", fileName);
        String name = random.getIngredient().ingredientName;
        NotificationUtils.addDocumentNumber(1, driver, "5", fullPath, "Some Description", false, false, name);
        return new NotificationDetails(driver);
    }

    public NotificationDetails addGenericToxicologyReportFromTempFolder(String fileName, String name) {
        String fullPath = FileUtils.getFileFullPath("tmp", fileName);
        NotificationUtils.addDocumentNumber(1, driver, "5", fullPath, "Some Description", false, false, name);
        return new NotificationDetails(driver);
    }


    public NotificationDetails addOtherReportFromTempFolder(int docNumber, String documentType, String fileName,
                                                            String description, boolean confidential, boolean active, String name) {
        String fullPath = FileUtils.getFileFullPath("tmp", fileName);
        NotificationUtils.addDocumentNumber(docNumber, driver, documentType, fullPath, description, confidential, active, name);
        return new NotificationDetails(driver);
    }

    public NotificationDetails updateStatusTo(String updatedStatus) {
        WaitUtils.waitForElementToBeClickable(driver, status, 15, false);
        PageUtils.selectByText(status, updatedStatus);
        PageUtils.doubleClick(driver, submitBtn);
        return new NotificationDetails(driver);
    }

    public boolean isCorrectPage() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, submitBtn, 10, false);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Use to update submission type and set previous and new ecid
     * @param type
     * @param prevECID
     * @param newECID
     * @return
     */
    public NotificationDetails updateNotificationECID(String type, String prevECID, String newECID) {
        WaitUtils.waitForElementToBeClickable(driver, submissionType, 15, false);

        if(type!=null && !type.equals(""))
        PageUtils.selectByIndex(submissionType, type);
        
        ecId.clear();
        ecId.sendKeys(newECID);
        previousEcId.clear();
        previousEcId.sendKeys(prevECID);

        //Submit for notification update
        PageUtils.doubleClick(driver, submitBtn);
        return new NotificationDetails(driver);
    }


    public NotificationDetails updateNotificationStatus(String statusTxt, String prevECID, String newECID) {
        WaitUtils.waitForElementToBeClickable(driver, submissionType, 15, false);

        if(statusTxt!=null && !statusTxt.equals(""))
            PageUtils.selectByText(status, statusTxt);

        ecId.clear();
        ecId.sendKeys(newECID);
        previousEcId.clear();
        previousEcId.sendKeys(prevECID);

        //Submit for notification update
        PageUtils.doubleClick(driver, submitBtn);
        return new NotificationDetails(driver);
    }

    public int getNumberOfDocsAttached() {
        try {
            int numberOfAttachments = listOfAttachementsReports.size();
            Select sl = new Select(documentType);
            String selected = sl.getFirstSelectedOption().getText();
            if(selected.contains("Select a Value")){
                numberOfAttachments = 0;
            }
            return numberOfAttachments;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     *Only created because the work flow disrupted due to zip upload functionality
     * @param add
     * @param data
     * @return
     */
    public NotificationDetails addSubmitterAddress(Address add, EcigProductSubmission data) {

        WaitUtils.waitForElementToBeClickable(driver, address, 10);
        address.sendKeys(add.address);
        phone.sendKeys(add.phone);
        email.sendKeys(add.email);
        PageUtils.clickOptionAdvanced(driver, productionSiteNo, productionSiteNo, add.productionSite);
        PageUtils.clickOptionAdvanced(driver, addressConfidentialNo, addressConfidentialNo, add.addressConfidential);

        PageUtils.selectByText(country, add.countryName);

        PageUtils.doubleClick(driver, submitBtn);

        return new NotificationDetails(driver);
    }

    public NotificationDetails enterDate(boolean submitForm, boolean hasVAT) {

        WaitUtils.waitForElementToBeClickable(driver, startDate, 15, false);
        //Add start and end date
        PageUtils.enterDate(driver, endDate, RandomDataUtils.getDateInFutureMonths(6));
        PageUtils.enterDate(driver, startDate, RandomDataUtils.getDateInFutureMonths(18));

        //Update submitter details
        PageUtils.clickOptionAdvanced(driver, hasEntererYes, hasEntererNo, false);
        PageUtils.clickOptionAdvanced(driver, hasAffiliateYes, hasAffiliateNo, false);
        PageUtils.clickOptionAdvanced(driver, hasParentYes, hasParentNo, false);

        PageUtils.clickOptionAdvanced(driver, hasVATYes, hasVatNo, hasVAT);
        if(hasVAT){
            WaitUtils.waitForElementToBeClickable(driver,By.xpath(".//label[.='VAT']//following::input[1]"), 5 );
            WebElement vatField = driver.findElement(By.xpath(".//label[.='VAT']//following::input[1]"));
            vatField.sendKeys(RandomDataUtils.getSimpleRandomNumberBetween(100000, 1000000));
        }

        if(submitForm)
        PageUtils.doubleClick(driver, submitBtn);

        return new NotificationDetails(driver);
    }

    public EditNotification clickAddAddress(){
        WaitUtils.waitForElementToBeClickable(driver, addAddressLink, 15, false);
        ecId.click();
        PageUtils.singleClick(driver, addAddressLink);
        return new EditNotification(driver);
    }
}

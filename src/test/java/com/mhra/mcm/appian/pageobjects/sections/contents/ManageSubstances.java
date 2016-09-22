package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects.ActionsPage;
import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ManageSubstances extends _Page {

    @FindBy(xpath = ".//button[.='Add a new substance']")
    WebElement addANewSubstance;
    @FindBy(xpath = ".//label[.='Substance Name']//following::input[1]")
    WebElement substanceName;
    @FindBy(xpath = ".//*[.='Edit']//following::input[1]")
    WebElement editSubstanceName;

    //ADD OR EDIT A SUBSTANCE
    @FindBy(xpath = ".//*[contains(text(),'substance Actively Banned')]//following::input[1]")
    WebElement activelyBannedYes;
    @FindBy(xpath = ".//*[contains(text(),'substance Actively Banned')]//following::input[2]")
    WebElement activelyBannedNo;
    @FindBy(xpath = ".//*[contains(text(),'substance permissible')]//following::input[1]")
    WebElement substancePermissableYes;
    @FindBy(xpath = ".//*[contains(text(),'substance permissible')]//following::input[2]")
    WebElement substancePermissableNo;
    @FindBy(xpath = ".//*[contains(text(),'CAS number required')]//following::input[1]")
    WebElement casNumberRequiredYes;
    @FindBy(xpath = ".//*[contains(text(),'CAS number required')]//following::input[2]")
    WebElement casNumberRequiredNo;
    @FindBy(xpath = ".//*[.='CAS Numbers']//following::input[1]")
    WebElement casNumbers;
    @FindBy(xpath = ".//label[.='Comments']//following::textarea[1]")
    WebElement comments;

    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submit;


    //Search section
    @FindBy(xpath = ".//*[.='Search']//following::input[1]")
    WebElement searchSubstanceName;
    @FindBy(xpath = ".//*[contains(text(),'Actively Banned')]//following::input[1]")
    WebElement searchActivelyBannedYes;
    @FindBy(xpath = ".//*[contains(text(),'Actively Banned')]//following::input[2]")
    WebElement searchActivelyBannedNo;
    @FindBy(xpath = ".//button[.='Search']")
    WebElement searchSubmit;

    //Actively banned logo
    @FindBy(xpath = ".//*[.='Actively Banned']//following::img")
    List<WebElement> listOfImgActivelyBannedAfterSearch;
    @FindBy(xpath = ".//*[.='Actively Banned']//following::img[1]")
    WebElement imgActivelyBannedAfterSearch;


    @Autowired
    public ManageSubstances(WebDriver driver) {
        super(driver);
    }

    public ManageSubstances clickOnAddNewSubstances() {
        WaitUtils.waitForElementToBeClickable(driver, addANewSubstance, 10, false);
        addANewSubstance.click();
        return new ManageSubstances(driver);
    }

    public ActionsPage addNewSubstance(String substance, boolean isBanned ) {
        WaitUtils.waitForElementToBeClickable(driver, substanceName, 10, false);
        substanceName.sendKeys(substance);

        if(isBanned) {
            PageUtils.singleClick(driver, activelyBannedYes);
        }else {
            PageUtils.singleClick(driver, activelyBannedNo);
        }
        PageUtils.singleClick(driver, casNumberRequiredYes);
        PageUtils.singleClick(driver, substancePermissableYes);
        casNumbers.sendKeys(RandomDataUtils.generateCASNumber());
        comments.sendKeys("Test Comment for banned substances");
        PageUtils.singleClick(driver, submit);

        return new ActionsPage(driver);
    }


    public boolean verifyNewSubstanceAdded(String substance, boolean isBanned) {
        boolean found = false;
        WaitUtils.waitForElementToBeClickable(driver, searchSubstanceName, 10);
        searchSubstanceName.sendKeys(substance);

        //This should not be necessary
        if(isBanned)
            PageUtils.singleClick(driver, searchActivelyBannedYes);
        else
            PageUtils.singleClick(driver, searchActivelyBannedNo);

        PageUtils.singleClick(driver, searchSubmit);

        //Wait for partialLinks
        PageFactory.initElements(driver, this);
        WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(substance), 10, false);

        //Verify link is visible
        WebElement link = driver.findElement(By.partialLinkText(substance));
        found = link.isDisplayed();

        //And only 1 match
        int size = driver.findElements(By.partialLinkText(substance)).size();

        return found && size == 1;
    }

    public ManageSubstances searchForSubstance(String substance, boolean activelyBanned) {
        WaitUtils.waitForElementToBeClickable(driver, searchSubstanceName, 10, false);
        searchSubstanceName.clear();
        searchSubstanceName.sendKeys(substance);

        //This should not be necessary
        if(activelyBanned)
            PageUtils.singleClick(driver, searchActivelyBannedYes);
        else
            PageUtils.singleClick(driver, searchActivelyBannedNo);

        PageUtils.singleClick(driver, searchSubmit);
        return new ManageSubstances(driver);
    }

    public boolean isSubstanceBanned() {
        boolean isBanned = false;
        WaitUtils.waitForElementToBeClickable(driver, imgActivelyBannedAfterSearch, 10, false);
        String bannedTxt = imgActivelyBannedAfterSearch.getAttribute("aria-label");
        if(bannedTxt.equals("Banned"))
            isBanned = true;
        return isBanned;
    }

    public boolean isAtleastOneItemDisplayed(){
        return listOfImgActivelyBannedAfterSearch.size() > 0;
    }

    public ManageSubstances updateARandomSubstance(String appendText) {
        return null;
    }

    public ActionsPage updateSubstanceName(String substanceAppended) {
        WaitUtils.waitForElementToBeClickable(driver, editSubstanceName, 10, false);
        editSubstanceName.clear();
        editSubstanceName.sendKeys(substanceAppended);
        submit.click();
        return new ActionsPage(driver);
    }

    public ManageSubstances viewSubstance(String substance) {
        WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(substance), 10, false);
        WebElement link = driver.findElement(By.partialLinkText(substance));
        link.click();
        return new ManageSubstances(driver);
    }

    public ActionsPage addNewSubstanceForDelimitedData(String substance, String commanDelimitedDetails) {
        String[] data = commanDelimitedDetails.split(",");
        String isBanned = data[0].split("=")[1];
        String permissible = data[1].split("=")[1];
        String casNumber = data[2].split("=")[1];

        WaitUtils.waitForElementToBeClickable(driver, substanceName, 10, false);
        substanceName.sendKeys(substance);

        if(Boolean.valueOf(isBanned)) {
            PageUtils.singleClick(driver, activelyBannedYes);
        }else {
            PageUtils.singleClick(driver, activelyBannedNo);
        }

        if(Boolean.valueOf(permissible)) {
            PageUtils.singleClick(driver, substancePermissableYes);
            PageFactory.initElements(driver, this);
            WaitUtils.waitForElementToBeClickable(driver, casNumbers, 10, false);
            casNumbers.sendKeys(RandomDataUtils.generateCASNumber());
        }else{
            PageUtils.singleClick(driver, substancePermissableNo);
        }

        if(Boolean.valueOf(casNumber)) {
            PageUtils.singleClick(driver, casNumberRequiredYes);
            PageFactory.initElements(driver, this);
            WaitUtils.waitForElementToBeClickable(driver, casNumbers, 10, false);
            casNumbers.sendKeys(RandomDataUtils.generateCASNumber());
        }else{
            PageUtils.singleClick(driver, casNumberRequiredNo);
        }
        comments.sendKeys("Test Comment for banned substances");
        PageUtils.singleClick(driver, submit);

        return new ActionsPage(driver);
    }

    public ActionsPage updateActivelyBannedStatusTo(String substance, boolean isBanned) {

        //searchForSubstance(substance, !isBanned);
//        WaitUtils.waitForElementToBeClickable(driver, searchSubstanceName, 10, false);
//        searchSubstanceName.clear();
//        searchSubstanceName.sendKeys(substance);
//
//        //This should not be necessary
//        if(!isBanned)
//            PageUtils.singleClick(driver, searchActivelyBannedYes);
//        else
//            PageUtils.singleClick(driver, searchActivelyBannedNo);
//
//        PageUtils.singleClick(driver, searchSubmit);

        //viewSubstance(substance);
        PageFactory.initElements(driver, this);
        WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(substance), 10, false);
        WebElement link = driver.findElement(By.partialLinkText(substance));
        link.click();

        //updateStatusOfSubstanceTo(substance, isBanned);
        PageFactory.initElements(driver, this);
        if(Boolean.valueOf(isBanned)) {
            PageUtils.doubleClick(driver, activelyBannedYes);
        }else {
            PageUtils.doubleClick(driver, activelyBannedNo);
        }
        PageUtils.singleClick(driver, submit);

        return new ActionsPage(driver);
    }

    public ActionsPage updateStatusOfSubstanceTo(String substance, boolean isBanned) {
        if(substance!=null){
            searchForSubstance(substance, isBanned);
            viewSubstance(substance);
        }

        WaitUtils.waitForElementToBeClickable(driver, activelyBannedYes, 10, false);
        if(Boolean.valueOf(isBanned)) {
            PageUtils.doubleClick(driver, activelyBannedYes);
        }else {
            PageUtils.doubleClick(driver, activelyBannedNo);
        }
        PageUtils.singleClick(driver, submit);

        return new ActionsPage(driver);
    }
}

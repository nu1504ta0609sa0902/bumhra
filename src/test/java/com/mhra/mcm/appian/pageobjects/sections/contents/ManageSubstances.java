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

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ManageSubstances extends _Page {

    @FindBy(xpath = ".//button[.='Add a new substance']")
    WebElement addANewSubstance;
    @FindBy(xpath = ".//label[.='Substance Name']//following::input[1]")
    WebElement substanceName;

    @FindBy(xpath = ".//*[contains(text(),'Actively Banned')]//following::input[1]")
    WebElement activelyBannedYes;
    @FindBy(xpath = ".//*[contains(text(),'Actively Banned')]//following::input[2]")
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
        casNumbers.sendKeys(RandomDataUtils.generateCASNumber());
        comments.sendKeys("Test Comment for banned substances");

        if(isBanned) {
            PageUtils.singleClick(driver, activelyBannedYes);
        }else {
            PageUtils.singleClick(driver, activelyBannedNo);
        }
        PageUtils.singleClick(driver, substancePermissableYes);
        PageUtils.singleClick(driver, casNumberRequiredNo);
        PageUtils.singleClick(driver, submit);

        return new ActionsPage(driver);
    }


    public boolean verifyNewSubstanceAdded(String substance) {
        boolean found = false;
        WaitUtils.waitForElementToBeClickable(driver, searchSubstanceName, 10, false);
        searchSubstanceName.sendKeys(substance);

        //This should not be necessary
        //PageUtils.singleClick(driver, searchActivelyBannedNo);
        PageUtils.singleClick(driver, searchSubmit);

        //Wait for partialLinks
        PageFactory.initElements(driver, this);
        WaitUtils.waitForElementToBeClickable(driver, By.partialLinkText(substance), 10, false);

        //Verify link is visible
        WebElement link = driver.findElement(By.partialLinkText(substance));
        found = link.isDisplayed();

        return found;
    }

    public ManageSubstances searchForSubstance(String substance, boolean activelyBanned) {
        WaitUtils.waitForElementToBeClickable(driver, searchSubstanceName, 10, false);
        searchSubstanceName.sendKeys(substance);

        //This should not be necessary
        if(activelyBanned)
            PageUtils.singleClick(driver, searchActivelyBannedYes);
        else
            PageUtils.singleClick(driver, searchActivelyBannedNo);
        PageUtils.singleClick(driver, searchSubmit);
        return new ManageSubstances(driver);
    }
}

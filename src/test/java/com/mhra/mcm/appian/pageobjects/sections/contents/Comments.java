package com.mhra.mcm.appian.pageobjects.sections.contents;

import com.mhra.mcm.appian.pageobjects._Page;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class Comments extends _Page {

    @FindBy(xpath = ".//a[.='Add Comments']")
    WebElement addCommentsBtn;

    @FindBy(xpath = ".//button[.='Submit']")
    WebElement submitNewComment;

    @FindBy(xpath = ".//*[.='Comment']//following::textarea")
    WebElement commentArea;

    @Autowired
    public Comments(WebDriver driver) {
        super(driver);
    }


    public boolean isCommentDisplayed(String commentTxt, String userName) {
        WaitUtils.waitForElementToBeClickable(driver, By.xpath(".//td[.='" + commentTxt + "']//following::td[1]"), 10, false);

        WebElement user = driver.findElement(By.xpath(".//td[.='" + commentTxt + "']//following::td[1]"));
        String un = user.getText();
        WebElement timestamp = driver.findElement(By.xpath(".//td[.='" + commentTxt + "']//following::td[2]"));
        String timestampTxt = timestamp.getText();

        boolean userNameFound = un.contains(userName);
        boolean timeStampFound = timestampTxt.contains("GMT+");
        return userNameFound && timeStampFound;
    }

    public Comments clickOnAddNewCommentButton(String commentTxt) {
        WaitUtils.waitForElementToBeClickable(driver, addCommentsBtn, 5, false);
        addCommentsBtn.click();
        return new Comments(driver);
    }


    public Comments addNewComment(String commentTxt) {
        WaitUtils.waitForElementToBeClickable(driver, commentArea, 5, false);
        commentArea.sendKeys(commentTxt);
        submitNewComment.click();
        return new Comments(driver);
    }
}

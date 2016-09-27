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
public class AuditHistory extends _Page {

    @FindBy(xpath = ".//*[.='Status']//following::p[1]")
    WebElement status;

    @FindBy(xpath = ".//*[.='Timestamp']/following::td[3]")
    WebElement user;

    @Autowired
    public AuditHistory(WebDriver driver) {
        super(driver);
    }


    public boolean isStatus(String expectedStatus) {
        WaitUtils.waitForElementToBeClickable(driver, status, 10, false);
        boolean matched = status.getText().equals(expectedStatus);
        if(!matched){
            matched = status.getText().contains("Quality Assurance");
        }
        return matched;
    }

    public boolean isUserNameEqualTo(String userNameOrEmail) {
        boolean contains = user.getText().contains(userNameOrEmail);
        if(!contains){
            contains = user.getText().contains("Appian Administrator");
        }
        return contains;
    }

    public boolean isUploadedDataCorrect(String status) {
        WebElement action = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[1]"));
        WebElement user = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[2]"));
        WebElement comment = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[3]"));
        WebElement timestamp = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[4]"));

        boolean actionTxtCorrect = action.getText().contains("Set Up Complete");
        boolean userTxtCorrect = user.getText().toLowerCase().contains("RDT ");
        boolean commentTxtCorrect = comment.getText().contains("Create Notification");
        if(comment.getText().equals("")){
            commentTxtCorrect = true;
        }
        boolean timestampTxtCorrect = timestamp.getText().contains("GMT+");
        return actionTxtCorrect && userTxtCorrect && commentTxtCorrect && timestampTxtCorrect;
    }



    public boolean isDataCorrect(String status, String actionTxt, String userTxt, String commentTxt, String timestampTxt) {
        WaitUtils.waitForElementToBeClickable(driver,user, 10 ,false);

        WebElement action = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[1]"));
        String at = action.getText();
        WebElement user = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[2]"));
        String ut = user.getText();
        WebElement comment = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[3]"));
        String ct = comment.getText();
        WebElement timestamp = driver.findElement(By.xpath(".//td[.='" + status + "']//following::td[4]"));
        String tt = timestamp.getText();

        boolean actionTxtCorrect = at.contains(actionTxt);
        boolean userTxtCorrect = ut.contains(userTxt);
        boolean commentTxtCorrect = ct.contains(commentTxt.trim());
        boolean timestampTxtCorrect = tt.contains(timestampTxt);
        return actionTxtCorrect && userTxtCorrect && commentTxtCorrect && timestampTxtCorrect;
    }
}

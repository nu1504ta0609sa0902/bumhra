package com.mhra.mcm.appian.utils.helpers.page;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

/**
 * Created by TPD_Auto on 02/08/2016.
 */
public class NotificationUtils {

    /**
     * Change notification data with the values passed into the system
     * @param dataValues
     * @return
     */
    public static Notification updateDefaultNotification(Map<String, String> dataValues) {
        Notification notification = new Notification(2, 2);
        String type = dataValues.get("type");
        String tcaNumber = dataValues.get("tcaNumber");
        String submitterName = dataValues.get("submitterName");
        String ingredient = dataValues.get("ingredient");

        if(type!=null){
            notification.getSummary().submissionType = type;
        }
        if(tcaNumber!=null){
            notification.getSubmitter().tcaNumber = tcaNumber;
        }
        if(submitterName!=null){
            if(submitterName.equals("random")){
                String sn = notification.getSubmitter().name;
                sn = sn + RandomDataUtils.getRandomNumberBetween(1000,10000);
                notification.getSubmitter().name = sn;
            }
        }
        if(ingredient!=null){
            notification.getIngredient().ingredientName = ingredient;
        }

        return notification;
    }

    public static void addDocumentNumber(int docNumber, WebDriver driver, String documentType, String fileName, String description, boolean confidential, boolean active) {
        int countFromSelect = (docNumber - 1) * 1;  //Theres only 1 select box
        int countFromInput = (docNumber - 1) * 8;   //Position of last input element is 8
        WebElement documentTypeSelectBox = driver.findElement(By.xpath(".//h2[.='Document Type']//following::select[" + (countFromSelect + 1) + "]"));
        WebElement descriptionElement = driver.findElement(By.xpath(".//h2[.='Document Type']//following::input[" + (countFromInput + 4) + "]"));
        WebElement confidentialYes = driver.findElement(By.xpath(".//h2[.='Document Type']//following::input[" + (countFromInput + 5) + "]"));
        WebElement confidentialNo = driver.findElement(By.xpath(".//h2[.='Document Type']//following::input[" + (countFromInput + 6) + "]"));
        WebElement activeYes = driver.findElement(By.xpath(".//h2[.='Document Type']//following::input[" + (countFromInput + 7) + "]"));
        WebElement activeNo = driver.findElement(By.xpath(".//h2[.='Document Type']//following::input[" + (countFromInput + 8) + "]"));

        //Fill details
        PageUtils.selectByIndex(documentTypeSelectBox, documentType);
        PageUtils.typeText(descriptionElement, description);
        PageUtils.clickOptionAdvanced(driver, confidentialYes, confidentialNo, confidential);
        PageUtils.clickOptionAdvanced(driver, activeYes, activeNo, active);

        //Browse and load document
        System.out.println(fileName);
        WebElement browseElement = driver.findElement(By.xpath(".//h2[.='Document Type']//following::input[" + (countFromInput + 3) + "]"));
        PageUtils.uploadDocument(browseElement, fileName);

        //Submit the form
        WebElement submit = driver.findElement(By.xpath(".//button[.='Submit']"));
        PageUtils.doubleClick(driver, submit);
    }
}

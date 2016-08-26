package com.mhra.mcm.appian.utils.helpers.page;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mhra.mcm.appian.domain.excelpojo.*;
import com.mhra.mcm.appian.domain.xmlPojo.EcigProductSubmission;
import com.mhra.mcm.appian.domain.xmlPojo.sub.Product;
import com.mhra.mcm.appian.domain.xmlPojo.sub.SubmissionType;
import com.mhra.mcm.appian.domain.xmlPojo.sub.Submitter;
import com.mhra.mcm.appian.utils.helpers.FileUtils;
import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
        Notification notification = new Notification(2, 2, null);

        if(dataValues!=null){
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

        }


        return notification;
    }

    public static void addDocumentNumber(int docNumber, WebDriver driver, String documentType, String fileName, String description, boolean confidential, boolean active, String name) {
        int countFromSelect = (docNumber - 1) * 2;  //Theres only 2 select box
        int countFromInput = (docNumber - 1) * 8;   //Position of last input element is 8

        //Wait for elements to be clickable
        WaitUtils.waitForElementToBeClickable(driver,By.xpath(".//h2[.='Document Type']//following::select[" + (countFromSelect + 1) + "]"), 10);
        WaitUtils.waitForElementToBeVisible(driver,By.xpath(".//h2[.='Document Type']//following::select[" + (countFromSelect + 2) + "]"), 10);

        //Get elements
        WebElement documentTypeSelectBox = driver.findElement(By.xpath(".//h2[.='Document Type']//following::select[" + (countFromSelect + 1) + "]"));
        WebElement ingredientSelectBox = driver.findElement(By.xpath(".//h2[.='Document Type']//following::select[" + (countFromSelect + 2) + "]"));
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

        WaitUtils.waitForElementToBeClickable(driver, ingredientSelectBox, 5);
        PageUtils.selectByText(ingredientSelectBox, name);

        //Browse and load document
        System.out.println(fileName);
        WebElement browseElement = driver.findElement(By.xpath(".//h2[.='Document Type']//following::input[" + (countFromInput + 3) + "]"));
        PageUtils.uploadDocument(browseElement, fileName);

        //Submit the form
        WebElement submit = driver.findElement(By.xpath(".//button[.='Submit']"));
        PageUtils.doubleClick(driver, submit);

        WaitUtils.nativeWait(1);
    }

    public static EcigProductSubmission updateDefaultXMLNotification(Map<String, String> dataValues, Map<String, Map> mapOfExcelData) {


        EcigProductSubmission notification = new EcigProductSubmission();

        for(int i = 1; i <= 3; i++){
            if(dataValues!=null){
                String manufacturer1 = dataValues.get("manufacturer"+i);
                String presentation1 = dataValues.get("presentation"+i);
                String design1 = dataValues.get("design"+i);
                String submitter1 = dataValues.get("submitter"+i);
                String ingredient1 = dataValues.get("ingredient"+i);
                String toxicology1 = dataValues.get("toxicology"+i);
                String emission1 = dataValues.get("emission"+i);
                String product1 = dataValues.get("product"+i);

                //Set submission type
                String submissionType = dataValues.get("submissionType");
                SubmissionType st = notification.getSubmissionType();
                if(submissionType!=null){
                    st.type = submissionType;
                }

                //Submitter
                Submitter submitter = notification.getSubmitter();
                if(submitter1!=null && !submitter1.equals("none")){
                    DO_Submitter doSubmitter = (DO_Submitter) mapOfExcelData.get("Submitter").get(submitter1);
                    submitter.addSubmitter(dataValues, doSubmitter );
                }
                submitter.evaluate();

                //Product: Emissions,Ingredient,Presentations,Design
                Product product = notification.getProduct();
                String casNumber = product.getCasNumber();
                if(product1!=null && !product1.equals("none")) {
                    DO_Product doProduct = (DO_Product) mapOfExcelData.get("Product").get(product1);
                    product.addProductDetail(notification.getEcIDNumber(), dataValues, doProduct);
                }

                if(manufacturer1!=null && !manufacturer1.equals("none")){
                    product.addManufacturer(mapOfExcelData, manufacturer1);
                }

                if(presentation1!=null && !presentation1.equals("none")){
                    DO_Presentation doPresentation = (DO_Presentation) mapOfExcelData.get("Presentation").get(presentation1);
                    product.addPresentation(presentation1, dataValues, doPresentation);
                }
                if(emission1!=null && !emission1.equals("none")){
                    DO_Emission doEmission = (DO_Emission) mapOfExcelData.get("Emission").get(emission1);
                    casNumber = product.addEmission(emission1, casNumber, dataValues, doEmission);
                }
                if(ingredient1!=null && !ingredient1.equals("none")){
                    DO_Ingredient doIngredient = (DO_Ingredient) mapOfExcelData.get("Ingredient").get(ingredient1);
                    DO_ToxicologyDetails doToxicologyDetails = (DO_ToxicologyDetails) mapOfExcelData.get("ToxicologicalDetails").get(toxicology1);
                    product.addIngredients(ingredient1, casNumber, dataValues, doIngredient, doToxicologyDetails);
                }
                if(design1!=null && !design1.equals("none")){
                    DO_Design doDesign = (DO_Design) mapOfExcelData.get("Design").get(design1);
                    product.addDesign(design1, dataValues, doDesign);
                }
            }
        }


        return notification;
    }

    public static String createXmlNotificationData(EcigProductSubmission notification) {
        String fileName = null;
        try {
            JAXBContext context = JAXBContext.newInstance(EcigProductSubmission.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(notification, System.out);

            String sn = new Date().toString().substring(0, 18).replaceAll(" ", "").replace(":","");
            sn = "";
            String tmp = FileUtils.getFileFullPath("tmp", "test"+ sn +  ".xml");
            System.out.println(tmp);
            m.marshal(notification, new File(tmp));

            fileName = tmp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}

package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.excelpojo.*;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.ToxicityStatus;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxCardioPulmonary;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxCmr;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxOther;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.props.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Product {

    @XmlElement(name = "ProductID")
    private ProductID productID;
    @XmlElement(name = "OtherProductsExist")
    private OtherProductsExist otherProductsExist;
    @XmlElement(name = "SameCompositionProductsExist")
    private SameCompositionProductsExist sameCompositionProductsExist;

    @XmlElementWrapper(name = "Manufacturers")
    @XmlElement(name = "Manufacturer")
    private List<Manufacturer> manufacturers = new ArrayList<>();

    @XmlElement(name = "ProductType")
    private ProductType productType;

    @XmlElement(name = "Weight")
    private Weight weight;
    @XmlElement(name = "Volume")
    private Volume volume;
    @XmlElement(name = "ClpClassification")
    private ClpClassification clpClassification;

    @XmlElementWrapper(name = "Presentations")
    @XmlElement(name = "Presentation")
    private List<Presentation> presentations = new ArrayList<>();

    @XmlElementWrapper(name = "Emissions")
    @XmlElement(name = "Emission")
    private List<Emission> emissions = new ArrayList<>();

    @XmlElementWrapper(name = "Ingredients")
    @XmlElement(name = "Ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    @XmlElement(name = "Design")
    private Design design;

    public Product(){
    }

    public Product(double w, double v, String ecId) {
        productID = new ProductID(ecId);
        otherProductsExist = new OtherProductsExist("false");
        sameCompositionProductsExist = new SameCompositionProductsExist("false");

        productType = new ProductType("5");
        weight = new Weight(w);
        volume = new Volume(v);
        clpClassification = new ClpClassification("None");
        //design = new Design(null);

//        presentations.add(new Presentation(RandomDataUtils.getRandomTestName("BrandName")));
//        presentations.add(new Presentation(RandomDataUtils.getRandomTestName("BrandName")));
//        ingredients.add(new Ingredient(RandomDataUtils.getRandomTestName("SupplementXML")));
//        ingredients.add(new Ingredient(RandomDataUtils.getRandomTestName("SupplementXML2")));
//        emissions.add(new Emission());
//        emissions.add(new Emission());
//
//        manufacturers.add(new Manufacturer("Test", "151 Buckingham Palace Road, London, SW1V", "GB", "44(0)20-31787094", "mhra.uat@gmail.com"));
    }

    public void addIngredients(String ingredient1, String casNumberGenerated, Map<String, String> dataValues, DO_Ingredient doIngredient, DO_ToxicologyDetails doToxicologyDetails) {

        //Add toxicology data if required
        //String ingredient1ToxicologyCardioPulmonary = doToxicologyDetails.toxCardioPulmonary;
        //String ingredient1ToxicologyCardioPulmonaryReport = dataValues.get("ingredient1AddToxicologyCardioPulmonaryReport");
        //String ingredient1ToxicologyCrm = doToxicologyDetails.toxCmr;
        //String ingredient1ToxicologyCrmReport = dataValues.get("ingredient1AddToxicologyCmrReport");
        //String ingredient1ToxicologyOther = doToxicologyDetails.toxOther;
        //String ingredient1ToxicologyOtherReport = dataValues.get("ingredient1AddToxicologyOtherReport");

        //Override with default random values
//        if(ingredient1.equals("default") || ingredient1.equals("random")){
//            //Use default values
//            ingredient1 = RandomDataUtils.getRandomTestName("Supplement");
//            ingredient1ToxicologyCardioPulmonary = ""+RandomDataUtils.getRandomBooleanValue();
//            //ingredient1ToxicologyCardioPulmonaryReport = "true";
//            ingredient1ToxicologyCrm = ""+RandomDataUtils.getRandomBooleanValue();
//            //ingredient1ToxicologyCrmReport = "true";
//            ingredient1ToxicologyOther = ""+RandomDataUtils.getRandomBooleanValue();
//            //ingredient1ToxicologyOtherReport = "true";
//        }

        //Add ingredient
        Ingredient ingredient = new Ingredient(ingredient1, casNumberGenerated, doIngredient, doToxicologyDetails);
        String ingredient1ToxicologyStatus = doIngredient.toxicityStatus;
        ingredient.setToxicologyStatus(new ToxicityStatus(ingredient1ToxicologyStatus));

        if(doToxicologyDetails!=null) {

            String ingredient1ToxicologyCardioPulmonary = doToxicologyDetails.toxCardioPulmonary;
            String ingredient1ToxicologyCrm = doToxicologyDetails.toxCmr;
            String ingredient1ToxicologyOther = doToxicologyDetails.toxOther;

            //Only add CardioPulmonary report if its true
            if (ingredient1ToxicologyCardioPulmonary != null && !ingredient1ToxicologyCardioPulmonary.equals("false"))
                ingredient.setToxicologyCardioPulmonary(new ToxCardioPulmonary(ingredient1ToxicologyCardioPulmonary), doToxicologyDetails.toxCardioPulmonary);


            //Only add Cmr report if its true
            if (ingredient1ToxicologyCrm != null && !ingredient1ToxicologyCrm.equals("false"))
                ingredient.setToxicologyCmr(new ToxCmr(ingredient1ToxicologyCrm), doToxicologyDetails.toxCmr);

            //Only add ToxOther report if its true
            if (ingredient1ToxicologyOther != null && !ingredient1ToxicologyOther.equals("false"))
                ingredient.setToxicologyOther(new ToxOther(ingredient1ToxicologyOther), doToxicologyDetails.toxOther);
        }
        ingredients.add(ingredient);
    }


    public void addPresentation(String presentation1, Map<String, String> dataValues, DO_Presentation doPresentation) {
        presentations.add(new Presentation(doPresentation));
    }

    public void addDesign(String design1, Map<String, String> dataValues, DO_Design doDesign) {
        design = new Design(doDesign);
    }

    public void addProductDetail(String ecIDNumber, Map<String, String> dataValues, DO_Product doProduct) {
        String productIdValue = doProduct.productId;
        String productTypeValue = doProduct.productType;
        String weightValue = doProduct.weight;
        String volumeValue = doProduct.volume;
        String otherProductExists = doProduct.otherProductExists;
        String sameCompositionProductsExists = doProduct.sameCompositionProductsExists;
        String clpClassification = doProduct.clpClassification;

        if(productIdValue!=null){
            if(productIdValue.equals("random") || productIdValue.equals("default")){
                productID = new ProductID(ecIDNumber);
            }else{
                productID = new ProductID(productIdValue);
            }
        }

        if(productTypeValue!=null){
            productType = new ProductType(productTypeValue);
        }

        if(weightValue !=null){
            weight = new Weight(Double.valueOf(weightValue));
        }

        if(volumeValue!=null){
            volume = new Volume(Double.valueOf(volumeValue));
        }

        otherProductsExist = new OtherProductsExist(otherProductExists);
        sameCompositionProductsExist = new SameCompositionProductsExist(sameCompositionProductsExists);
        this.clpClassification = new ClpClassification(clpClassification);
    }

    public String addEmission(String emission1, String casNumberGenerated, Map<String, String> dataValues, DO_Emission doEmission) {
        String casNumber = doEmission.casNumber;
        if(casNumber!=null && casNumber.equals("random")){
            //Generate randomly
            casNumber = casNumberGenerated;
        }
        String emission1Quantity = doEmission.quantity;
        String emission1Unit = doEmission.unit;
        String emission1Name = doEmission.name;
        String emission1Attachement = doEmission.attachment;
        Emission emission = new Emission(casNumberGenerated, emission1Quantity, emission1Unit, emission1Name, emission1Attachement);
        emissions.add(emission);

//        if(emission1.equals("random") || emission1.equals("default")){
//            emission1Quantity = RandomDataUtils.getRandomFloatNumberBetween(1, 100);
//            emission1Unit = "<" + RandomDataUtils.getRandomFloatNumberBetween(1, 100) + "ug/" + RandomDataUtils.getRandomNumberBetween(50, 300) + "puffs";
//            emission1Name = RandomDataUtils.getRandomNumberBetween(1, 30);
//            Emission emission = new Emission(casNumberGenerated, emission1Quantity, emission1Unit, emission1Name, "true");
//            emissions.add(emission);
//        }else{
//            Emission emission = new Emission(casNumberGenerated, emission1Quantity, emission1Unit, emission1Name, emission1Attachement);
//            emissions.add(emission);
//        }

        return casNumber;
    }

    public String getCasNumber() {
        return RandomDataUtils.generateCASNumber();
    }

    public void addManufacturer(Map<String, Map> mapOfExcelData, DO_Manufacturer doManufacturer) {

        String manufacturerAddressKey = doManufacturer.manufacturerAddress;
        String prodcutionSiteAddress1Key = doManufacturer.prodcutionSiteAddress1Key;
        String prodcutionSiteAddress2Key = doManufacturer.prodcutionSiteAddress2Key;

        //Get addresses
        DO_Address manAddress = (DO_Address) mapOfExcelData.get("Addresses").get(manufacturerAddressKey);
        DO_Address site1 = (DO_Address) mapOfExcelData.get("Addresses").get(prodcutionSiteAddress1Key);
        DO_Address site2 = (DO_Address) mapOfExcelData.get("Addresses").get(prodcutionSiteAddress2Key);
        String name = doManufacturer.name;
        if(name!=null && name.equals("random")){
            name = RandomDataUtils.getRandomTestName("Manufacturer");
        }

        //Manufacturer address
        String address = manAddress.address ;
        String country = manAddress.country;
        String phoneNumber = manAddress.phoneNumber;
        String email = manAddress.email;

        manufacturers.add(new Manufacturer(name, address, country, phoneNumber, email, site1, site2));
    }
}
package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.ToxicityStatus;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxCardioPulmonary;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxCmr;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxEmission;
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
        otherProductsExist = new OtherProductsExist(false);
        sameCompositionProductsExist = new SameCompositionProductsExist(false);

        productType = new ProductType("5");
        weight = new Weight(w);
        volume = new Volume(v);
        clpClassification = new ClpClassification("None");
        design = new Design("");

        presentations.add(new Presentation(RandomDataUtils.getRandomTestName("BrandName")));
        presentations.add(new Presentation(RandomDataUtils.getRandomTestName("BrandName")));
        ingredients.add(new Ingredient(RandomDataUtils.getRandomTestName("SupplementXML")));
        ingredients.add(new Ingredient(RandomDataUtils.getRandomTestName("SupplementXML2")));
        emissions.add(new Emission());
        emissions.add(new Emission());

        manufacturers.add(new Manufacturer("Test", "151 Buckingham Palace Road, London, SW1V", "GB", "44(0)20-31787094", "mhra.uat@gmail.com"));
    }

    public void addIngredients(String ingredient1, Map<String, String> dataValues) {

        //Add toxicology data if required
        String ingredient1ToxicologyCardioPulmonary = dataValues.get("ingredient1AddToxicologyCardioPulmonary");
        String ingredient1ToxicologyCardioPulmonaryReport = dataValues.get("ingredient1AddToxicologyCardioPulmonaryReport");
        String ingredient1ToxicologyCrm = dataValues.get("ingredient1AddToxicologyCmr");
        String ingredient1ToxicologyCrmReport = dataValues.get("ingredient1AddToxicologyCmrReport");
        String ingredient1ToxicologyOther = dataValues.get("ingredient1AddToxicologyOther");
        String ingredient1ToxicologyOtherReport = dataValues.get("ingredient1AddToxicologyOtherReport");

        //Override with default random values
        if(ingredient1.equals("default") || ingredient1.equals("random")){
            //Use default values
            ingredient1 = RandomDataUtils.getRandomTestName("Supplement");
            ingredient1ToxicologyCardioPulmonary = ""+RandomDataUtils.getRandomBooleanValue();
            ingredient1ToxicologyCardioPulmonaryReport = "true";
            ingredient1ToxicologyCrm = ""+RandomDataUtils.getRandomBooleanValue();
            ingredient1ToxicologyCrmReport = "true";
            ingredient1ToxicologyOther = ""+RandomDataUtils.getRandomBooleanValue();
            ingredient1ToxicologyOtherReport = "true";
        }

        //Add ingredient
        Ingredient ingredient = new Ingredient(ingredient1);
        String ingredient1ToxicologyStatus = dataValues.get("ingredient1ToxicologyStatus");
        ingredient.setToxicologyStatus(new ToxicityStatus(ingredient1ToxicologyStatus));

        //Only add CardioPulmonary report if its true
        if (ingredient1ToxicologyCardioPulmonary != null && !ingredient1ToxicologyCardioPulmonary.equals("false"))
            ingredient.setToxicologyCardioPulmonary(new ToxCardioPulmonary(ingredient1ToxicologyCardioPulmonary), ingredient1ToxicologyCardioPulmonaryReport);

        //Only add Cmr report if its true
        if (ingredient1ToxicologyCrm != null && !ingredient1ToxicologyCrm.equals("false"))
            ingredient.setToxicologyCmr(new ToxCmr(ingredient1ToxicologyCrm), ingredient1ToxicologyCrmReport);

        //Only add ToxOther report if its true
        if (ingredient1ToxicologyOther != null && !ingredient1ToxicologyOther.equals("false"))
            ingredient.setToxicologyOther(new ToxOther(ingredient1ToxicologyOtherReport), ingredient1ToxicologyOther);

        ingredients.add(ingredient);
    }

    public void addManufacturer(String manufacturer1, Map<String, String> dataValues) {
        String address =  "151 Buckingham Palace Road, London, SW1V";
        String country = "GB";
        String phoneNumber = "44(0)20-31787094";
        String email = "mhra.uat@gmail.com";

        if(manufacturer1.equals("default")  || manufacturer1.equals("random")){
            manufacturer1 = RandomDataUtils.getRandomTestName("Manufacturer");
            address = RandomDataUtils.getRandomNumberBetween(1,100) + address;
            phoneNumber = "44(0)20-" + RandomDataUtils.getRandomDigits(6);
        }
        manufacturers.add(new Manufacturer(manufacturer1, address, country, phoneNumber, email));
    }

    public void addPresentation(String presentation1, Map<String, String> dataValues) {
        if(presentation1.equals("default") || presentation1.equals("random")){
            presentation1 = RandomDataUtils.getRandomTestName("BrandName");
        }
        presentations.add(new Presentation(presentation1));
    }

    public void addDesign(String design1, Map<String, String> dataValues) {
        if(design1.equals("default") || design1.equals("random")){
            design1 = "This vape is super cool " + RandomDataUtils.getRandomTestName("VAPE_DESIGN");
        }
        design = new Design(design1);
    }

    public void evaluate(String ecIDNumber, Map<String, String> dataValues) {
        String productIdValue = dataValues.get("productId");
        String productTypeValue = dataValues.get("productType");
        String weightValue = dataValues.get("weight");
        String volumeValue = dataValues.get("volume");
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

        otherProductsExist = new OtherProductsExist(false);
        sameCompositionProductsExist = new SameCompositionProductsExist(false);
        clpClassification = new ClpClassification("None");
    }

    public void addEmission(String emission1, Map<String, String> dataValues) {
        String emission1Quantity = dataValues.get("emission1Quantity");
        String emission1Unit = dataValues.get("emission1Unit");
        String emission1Name = dataValues.get("emission1Name");
        String emission1Attachement = dataValues.get("emission1Attachement");
        if(emission1.equals("random") || emission1.equals("default")){
            emission1Quantity = RandomDataUtils.getRandomFloatNumberBetween(1, 100);
            emission1Unit = "<" + RandomDataUtils.getRandomFloatNumberBetween(1, 100) + "ug/" + RandomDataUtils.getRandomNumberBetween(50, 300) + "puffs";
            emission1Name = RandomDataUtils.getRandomNumberBetween(1, 30);
            emissions.add(new Emission(emission1Quantity, emission1Unit, emission1Name, "true"));
        }else{
            emissions.add(new Emission(emission1Quantity, emission1Unit, emission1Name, emission1Attachement));
        }
    }
}
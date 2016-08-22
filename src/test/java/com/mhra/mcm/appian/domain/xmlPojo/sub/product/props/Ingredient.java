package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
public class Ingredient {

    public String ingredientName;
    public String toxicity;
    public String nonVapourisedStatus;
    public boolean casExists;
    public String cASNumber;
    public String FEMA;
    public String additive;
    public String fLNumber;
    public String eCNumber;


    public Ingredient(){
        createDefault();
    }

    public Ingredient(String ingredient){
        this.ingredientName = ingredient;
        createDefault();
    }

    private void createDefault() {
        toxicity = "3";
        nonVapourisedStatus = "3";
        casExists = false;
        cASNumber = "10" + (int) RandomDataUtils.getRandomDigits(7);
        FEMA = "FEMA" + (int) RandomDataUtils.getRandomDigits(7);
        additive = "Vitamin D1";
        fLNumber = "FL" + (int) RandomDataUtils.getRandomDigits(7);
        eCNumber = RandomDataUtils.getECID("ECNUM");
    }
}

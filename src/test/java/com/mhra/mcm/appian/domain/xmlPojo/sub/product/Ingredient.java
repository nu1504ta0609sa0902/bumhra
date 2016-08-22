package com.mhra.mcm.appian.domain.xmlPojo.sub.product;

import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

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

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientName='" + ingredientName + '\'' +
                ", toxicity='" + toxicity + '\'' +
                ", nonVapourisedStatus='" + nonVapourisedStatus + '\'' +
                ", casExists='" + casExists + '\'' +
                ", cASNumber='" + cASNumber + '\'' +
                ", FEMA='" + FEMA + '\'' +
                ", additive='" + additive + '\'' +
                ", fLNumber='" + fLNumber + '\'' +
                ", eCNumber='" + eCNumber + '\'' +
                '}';
    }
}

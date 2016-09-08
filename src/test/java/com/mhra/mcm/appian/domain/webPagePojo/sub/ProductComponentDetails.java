package com.mhra.mcm.appian.domain.webPagePojo.sub;

import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;

/**
 *
 */
public class ProductComponentDetails {

    public String coilComposition;
    public String coilResistance;
    public boolean airflowAdjustable;
    public boolean microprocessor;
    public boolean conformity;
    public boolean qualitySafety;
    public boolean nonRisk;
    public boolean wickChangeable;
    public boolean highPurity;
    public boolean tamperProof;
    public boolean consistentDosing;


    public ProductComponentDetails(){
        this.coilResistance = RandomDataUtils.getRandomNumberBetween(1, 20);
        this.coilComposition = RandomDataUtils.getRandomNumberBetween(1, 20);
        createDefault();
    }

    public ProductComponentDetails(String cc, String cr){
        this.coilComposition = cc;
        this.coilResistance = cr;
        createDefault();
    }

    private void createDefault() {
        //Default is all the other values are false
    }

    @Override
    public String toString() {
        return "ProductComponentDetails{" +
                "coilComposition='" + coilComposition + '\'' +
                ", coilResistance='" + coilResistance + '\'' +
                ", airflowAdjustable=" + airflowAdjustable +
                ", microprocessor=" + microprocessor +
                ", conformity=" + conformity +
                ", qualitySafety=" + qualitySafety +
                ", nonRisk=" + nonRisk +
                ", wickChangeable=" + wickChangeable +
                ", highPurity=" + highPurity +
                ", tamperProof=" + tamperProof +
                ", consistentDosing=" + consistentDosing +
                '}';
    }
}

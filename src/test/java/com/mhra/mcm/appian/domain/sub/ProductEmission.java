package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

/**
 *
 */
public class ProductEmission {

    public String emission;
    public String otherName;
    public String iupac;
    public String quantity;
    public String units;


    public ProductEmission(){
        createDefault();
    }

    public ProductEmission(String emissionNumber){
        this.emission = emissionNumber;
        createDefault();
    }

    private void createDefault() {
    }

    @Override
    public String toString() {
        return "ProductEmission{" +
                "emission='" + emission + '\'' +
                ", otherName='" + otherName + '\'' +
                ", iupac='" + iupac + '\'' +
                ", quantity='" + quantity + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}

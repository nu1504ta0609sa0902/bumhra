package com.mhra.mcm.appian.domain.webPagePojo.sub;

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
        this.emission = RandomDataUtils.getRandomNumberBetween(1000000, 10000000);
        createDefault();
    }

    public ProductEmission(String emissionNumber){
        this.emission = emissionNumber;
        createDefault();
    }

    private void createDefault() {
        this.otherName = RandomDataUtils.getRandomTestName("PE_");
        this.iupac = RandomDataUtils.getRandomNumberBetween(1000000, 10000000);
        this.quantity = RandomDataUtils.getRandomNumberBetween(1, 20);
        this.units = RandomDataUtils.getRandomNumberBetween(1, 20);
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

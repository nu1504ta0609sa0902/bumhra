package com.mhra.mcm.appian.domain.webPagePojo.sub;

import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;

/**
 *
 */
public class ProductIdentifiers {

    public String upcNumber;
    public String eanNumber;
    public String gtinNumber;
    public String skuNumber;
    public String packageUnits;


    public ProductIdentifiers(){
        this.upcNumber = RandomDataUtils.getRandomNumberBetween(100000, 1000000);
        createDefault();
    }

    public ProductIdentifiers(String upcNumber){
        this.upcNumber = RandomDataUtils.getRandomNumberBetween(1000000, 10000000);
        createDefault();
    }

    private void createDefault() {
        eanNumber = RandomDataUtils.getRandomNumberBetween(1000000, 10000000);
        gtinNumber = RandomDataUtils.getRandomNumberBetween(1000000, 10000000);
        skuNumber = RandomDataUtils.getRandomNumberBetween(1000000, 10000000);
        packageUnits = RandomDataUtils.getRandomNumberBetween(1, 100);
    }

    @Override
    public String toString() {
        return "ProductIdentifiers{" +
                "upcNumber='" + upcNumber + '\'' +
                ", eanNumber='" + eanNumber + '\'' +
                ", gtinNumber='" + gtinNumber + '\'' +
                ", skuNumber='" + skuNumber + '\'' +
                ", packageUnits='" + packageUnits + '\'' +
                '}';
    }
}

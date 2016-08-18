package com.mhra.mcm.appian.domain.sub;

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
        createDefault();
    }

    public ProductIdentifiers(String upcNumber){
        this.upcNumber = upcNumber;
        createDefault();
    }

    private void createDefault() {
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

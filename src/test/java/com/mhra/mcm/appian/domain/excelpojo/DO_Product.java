package com.mhra.mcm.appian.domain.excelpojo;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Product {
    public String key;
    public String productId;                        //If null than its auto generated
    public String otherProductExists;
    public String sameCompositionProductsExists;
    public String productType;
    public String weight;
    public String volume;
    public String clpClassification;

    /**
     *
     * @param line
     */
    public DO_Product(String line){
        System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            productId = data[1];
            otherProductExists = data[2];
            sameCompositionProductsExists = data[3];
            productType = data[4];
            weight = data[5];
            volume = data[6];
            clpClassification = data[7];
            System.out.println("");
        }
    }



    @Override
    public boolean equals(Object obj) {
        DO_Product o = (DO_Product) obj;
        return this.key.equals(o.key);
    }
}

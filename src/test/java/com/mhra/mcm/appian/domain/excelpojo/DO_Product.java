package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

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
        //System.out.println(line);
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
        }
    }

    public DO_Product(String[] dataUpdated) {
        for(String dt: dataUpdated){
            String fieldName = ExcelUtils.getFieldValue(dt, 0);
            String fieldValue = ExcelUtils.getFieldValue(dt, 1);
            if(fieldName!=null)
                populateCorrectField(fieldName, fieldValue);
        }
    }

    private void populateCorrectField(String fieldName, String fieldValue) {
        String field = fieldName.toLowerCase().trim();
        //System.out.println(field);
        if(field.equals("key")){
            key = fieldValue;
        }else if(field.equals("productid")){
            productId = fieldValue;
        }else if(field.equals("otherproductsexist")){
            otherProductExists = fieldValue;
        }else if(field.equals("samecompositionproductsexist")){
            sameCompositionProductsExists = fieldValue;
        }else if(field.equals("producttype")){
            productType = fieldValue;
        }else if(field.equals("weight")){
            weight = fieldValue;
        }else if(field.equals("volume")){
            volume = fieldValue;
        }else if(field.equals("clpclassification")){
            clpClassification = fieldValue;
        }
    }


    @Override
    public boolean equals(Object obj) {
        DO_Product o = (DO_Product) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Product{" +
                "key='" + key + '\'' +
                ", productId='" + productId + '\'' +
                ", otherProductExists='" + otherProductExists + '\'' +
                ", sameCompositionProductsExists='" + sameCompositionProductsExists + '\'' +
                ", productType='" + productType + '\'' +
                ", weight='" + weight + '\'' +
                ", volume='" + volume + '\'' +
                ", clpClassification='" + clpClassification + '\'' +
                '}';
    }
}

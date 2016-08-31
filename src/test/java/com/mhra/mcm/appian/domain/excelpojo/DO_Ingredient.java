package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Ingredient {

    public String key;
    public String name;
    public String casNumberExists;
    public String casNumber;
    public String fINumber;
    public String femaNumber;
    public String ecNumber;
    public String recipeQuantity;
    public String toxicityStatus;
    public String reachRegistration;
    public String clpWhetherClassification;

    /**
     *
     * @param line
     */
    public DO_Ingredient(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            name = data[1];
            casNumberExists = data[2];
            casNumber = data[3];
            fINumber = data[4];
            femaNumber = data[5];
            ecNumber = data[6];
            recipeQuantity = data[7];
            toxicityStatus = data[8];
            reachRegistration = data[9];
            clpWhetherClassification = data[10];

        }
    }

    public DO_Ingredient(String[] dataUpdated) {
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
        }else if(field.equals("name")){
            name = fieldValue;
        }else if(field.equals("casnumberexists")){
            casNumberExists = fieldValue;
        }else if(field.equals("casnumber")){
            casNumber = fieldValue;
        }else if(field.equals("flnumber")){
            fINumber = fieldValue;
        }else if(field.equals("femanumber")){
            femaNumber = fieldValue;
        }else if(field.equals("ecnumber")){
            ecNumber = fieldValue;
        }else if(field.equals("recipequantity")){
            recipeQuantity = fieldValue;
        }else if(field.equals("toxicitystatus")){
            toxicityStatus = fieldValue;
        }else if(field.equals("reachregistration")){
            reachRegistration = fieldValue;
        }else if(field.equals("clpwhetherclassification")){
            clpWhetherClassification = fieldValue;
        }
    }


    @Override
    public boolean equals(Object obj) {
        DO_Ingredient o = (DO_Ingredient) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Ingredient{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", casNumberExists='" + casNumberExists + '\'' +
                ", casNumber='" + casNumber + '\'' +
                ", fINumber='" + fINumber + '\'' +
                ", femaNumber='" + femaNumber + '\'' +
                ", ecNumber='" + ecNumber + '\'' +
                ", recipeQuantity='" + recipeQuantity + '\'' +
                ", toxicityStatus='" + toxicityStatus + '\'' +
                ", reachRegistration='" + reachRegistration + '\'' +
                ", clpWhetherClassification='" + clpWhetherClassification + '\'' +
                '}';
    }
}

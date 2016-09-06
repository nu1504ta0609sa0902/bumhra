package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.datadriven.ExcelUtils;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Emission {
    public String key;
    public String casNumber;
    public String quantity;
    public String unit;
    public String name;
    public String attachment;

    /**
     *
     * @param line
     */
    public DO_Emission(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            casNumber = data[1];
            quantity = data[2];
            unit = data[3];
            name = data[4];
            attachment = data[5];
        }
    }

    public DO_Emission(String[] dataUpdated) {
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
        }else if(field.equals("casnumber")){
            casNumber = fieldValue;
        }else if(field.equals("quantity")){
            quantity = fieldValue;
        }else if(field.equals("unit")){
            unit = fieldValue;
        }else if(field.equals("name")){
            name = fieldValue;
        }else if(field.equals("attachment")){
            attachment = fieldValue;
        }
    }

    @Override
    public boolean equals(Object obj) {
        DO_Emission o = (DO_Emission) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Emission{" +
                "key='" + key + '\'' +
                ", casNumber='" + casNumber + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unit='" + unit + '\'' +
                ", name='" + name + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}

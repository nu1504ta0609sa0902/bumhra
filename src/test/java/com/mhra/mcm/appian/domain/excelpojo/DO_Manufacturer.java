package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.datadriven.ExcelUtils;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Manufacturer {
    public String key;
    public String name;
    public String manufacturerAddress;
    public String prodcutionSiteAddress1Key;
    public String prodcutionSiteAddress2Key;

    /**
     *
     * @param line
     */
    public DO_Manufacturer(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            name = data[1];
            manufacturerAddress = data[2];
            prodcutionSiteAddress1Key = data[3];
            prodcutionSiteAddress2Key = data[4];
        }
    }

    public DO_Manufacturer(String[] dataUpdated) {
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
        }else if(field.equals("manufactureraddress")){
            manufacturerAddress = fieldValue;
        }else if(field.equals("productionsiteaddresses")){
            prodcutionSiteAddress1Key = fieldValue;
        }else if(field.equals("productionsiteaddresses2")){
            prodcutionSiteAddress2Key = fieldValue;
        }
    }

    @Override
    public boolean equals(Object obj) {
        DO_Manufacturer o = (DO_Manufacturer) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Manufacturer{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", manufacturerAddress='" + manufacturerAddress + '\'' +
                ", prodcutionSiteAddress1Key='" + prodcutionSiteAddress1Key + '\'' +
                ", prodcutionSiteAddress2Key='" + prodcutionSiteAddress2Key + '\'' +
                '}';
    }
}

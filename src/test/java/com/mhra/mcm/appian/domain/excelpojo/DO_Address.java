package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Address {
    public String key;
    public String name;
    public String address;
    public String country;
    public String phoneNumber;
    public String email;

    /**
     *
     * @param line
     */
    public DO_Address(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");

            key = data[0];
            name = data[1];
            address = data[2];
            //Make sure commas are replace with ++ in the excel sheet
            address = replace("++", ",", address);
            country = data[3];
            phoneNumber = data[4];
            email = data[5];
        }
    }

    public DO_Address(String[] dataUpdated) {
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
        }else if(field.equals("address")){
            address = fieldValue;
        }else if(field.equals("country")){
            country = fieldValue;
        }else if(field.equals("phonenumber")){
            phoneNumber = fieldValue;
        }else if(field.equals("email")){
            email = fieldValue;
        }

    }


    private String replace(String text, String with, String line) {
        line = line.replace(text, with);
        return line;
    }

    @Override
    public boolean equals(Object obj) {
        DO_Address o = (DO_Address) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Address{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

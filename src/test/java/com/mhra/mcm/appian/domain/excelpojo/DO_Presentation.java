package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Presentation {
    public String key;
    public String nationalMarket;
    public String brandName;
    public String brandSubtypeNameExists;
    public String brandSubtypeName;
    public String launchDate;
    public String withdrawalIndication;
    public String productNumberType;
    public String productNumber;
    public String packageUnits;
    public String unitPacketPictureFile;

    /**
     *
     * @param line
     */
    public DO_Presentation(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            nationalMarket = data[1];
            brandName = data[2];
            brandSubtypeNameExists = data[3];
            brandSubtypeName = data[4];
            launchDate = data[5];
            withdrawalIndication = data[6];
            productNumberType = data[7];
            productNumber = data[8];
            packageUnits = data[9];
            unitPacketPictureFile = data[10];
        }
    }

    public DO_Presentation(String[] dataUpdated) {
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
        }else if(field.equals("nationalmarket")){
            nationalMarket = fieldValue;
        }else if(field.equals("brandname")){
            brandName = fieldValue;
        }else if(field.equals("brandsubtypenameexists")){
            brandSubtypeNameExists = fieldValue;
        }else if(field.equals("brandsubtypename")){
            brandSubtypeName = fieldValue;
        }else if(field.equals("launchdate")){
            launchDate = fieldValue;
        }else if(field.equals("withdrawalindication")){
            withdrawalIndication = fieldValue;
        }else if(field.equals("productnumbertype")){
            productNumberType = fieldValue;
        }else if(field.equals("productnumber")){
            productNumber = fieldValue;
        }else if(field.equals("packageunits")){
            packageUnits = fieldValue;
        }else if(field.equals("unitpacketpicturefile")){
            unitPacketPictureFile = fieldValue;
        }

    }
    @Override
    public boolean equals(Object obj) {
        DO_Presentation o = (DO_Presentation) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Presentation{" +
                "key='" + key + '\'' +
                ", nationalMarket='" + nationalMarket + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandSubtypeNameExists='" + brandSubtypeNameExists + '\'' +
                ", brandSubtypeName='" + brandSubtypeName + '\'' +
                ", launchDate='" + launchDate + '\'' +
                ", withdrawalIndication='" + withdrawalIndication + '\'' +
                ", productNumberType='" + productNumberType + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", packageUnits='" + packageUnits + '\'' +
                ", unitPacketPictureFile='" + unitPacketPictureFile + '\'' +
                '}';
    }
}

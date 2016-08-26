package com.mhra.mcm.appian.domain.excelpojo;

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



    @Override
    public boolean equals(Object obj) {
        DO_Presentation o = (DO_Presentation) obj;
        return this.key.equals(o.key);
    }
}

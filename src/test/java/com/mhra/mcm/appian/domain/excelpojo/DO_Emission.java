package com.mhra.mcm.appian.domain.excelpojo;

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



    @Override
    public boolean equals(Object obj) {
        DO_Emission o = (DO_Emission) obj;
        return this.key.equals(o.key);
    }
}

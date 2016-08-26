package com.mhra.mcm.appian.domain.excelpojo;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_ToxicologyDetails {
    public String key;
    public String toxicologicalDataAvailable;
    public String toxEmision;
    public String toxCmr;
    public String toxCardioPulmonary;
    public String toxAdditive;
    public String toxOther;
    public String attachment;

    /**
     *
     * @param line
     */
    public DO_ToxicologyDetails(String line){
        System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");

            key = data[0];
            toxicologicalDataAvailable = data[1];
            toxEmision = data[2];
            toxCmr = data[3];
            toxCardioPulmonary = data[4];
            toxAdditive = data[5];
            toxOther = data[6];
            attachment = data[7];
            System.out.println();
        }
    }



    @Override
    public boolean equals(Object obj) {
        DO_ToxicologyDetails o = (DO_ToxicologyDetails) obj;
        return this.key.equals(o.key);
    }
}

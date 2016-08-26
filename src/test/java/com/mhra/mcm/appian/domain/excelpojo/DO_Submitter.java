package com.mhra.mcm.appian.domain.excelpojo;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Submitter {
    public String key;
    public String submitterType;
    public String hasEnterer;
    public String hasParent;
    public String hasAffiliate;

    /**
     *
     * @param line
     */
    public DO_Submitter(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")){
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            submitterType = data[1];
            hasEnterer = data[2];
            hasParent = data[3];
            hasAffiliate = data[4];
        }
    }



    @Override
    public boolean equals(Object obj) {
        DO_Submitter o = (DO_Submitter) obj;
        return this.key.equals(o.key);
    }
}

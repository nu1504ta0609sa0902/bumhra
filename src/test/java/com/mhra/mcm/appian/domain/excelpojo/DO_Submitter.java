package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

import java.util.List;

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

    public DO_Submitter(String[] dataUpdated) {
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
        }else if(field.equals("submittertype")){
            submitterType = fieldValue;
        }else if(field.equals("hasenterer")){
            hasEnterer = fieldValue;
        }else if(field.equals("hasparent")){
            hasParent = fieldValue;
        }else if(field.equals("hasaffiliate")){
            hasAffiliate = fieldValue;
        }
    }



    @Override
    public boolean equals(Object obj) {
        DO_Submitter o = (DO_Submitter) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Submitter{" +
                "key='" + key + '\'' +
                ", submitterType='" + submitterType + '\'' +
                ", hasEnterer='" + hasEnterer + '\'' +
                ", hasParent='" + hasParent + '\'' +
                ", hasAffiliate='" + hasAffiliate + '\'' +
                '}';
    }
}

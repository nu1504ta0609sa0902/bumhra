package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

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
        //System.out.println(line);
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
        }
    }

    public DO_ToxicologyDetails(String[] dataUpdated) {
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
        }else if(field.equals("toxicologicaldataavailable")){
            toxicologicalDataAvailable = fieldValue;
        }else if(field.equals("toxemission")){
            toxEmision = fieldValue;
        }else if(field.equals("toxcmr")){
            toxCmr = fieldValue;
        }else if(field.equals("toxcardiopulmonary")){
            toxCardioPulmonary = fieldValue;
        }else if(field.equals("toxaddictive")){
            toxAdditive = fieldValue;
        }else if(field.equals("toxother")){
            toxOther = fieldValue;
        }else if(field.equals("attachment")){
            attachment = fieldValue;
        }
    }


    @Override
    public boolean equals(Object obj) {
        DO_ToxicologyDetails o = (DO_ToxicologyDetails) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_ToxicologyDetails{" +
                "key='" + key + '\'' +
                ", toxicologicalDataAvailable='" + toxicologicalDataAvailable + '\'' +
                ", toxEmision='" + toxEmision + '\'' +
                ", toxCmr='" + toxCmr + '\'' +
                ", toxCardioPulmonary='" + toxCardioPulmonary + '\'' +
                ", toxAdditive='" + toxAdditive + '\'' +
                ", toxOther='" + toxOther + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}

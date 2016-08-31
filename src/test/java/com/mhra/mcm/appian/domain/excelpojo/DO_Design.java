package com.mhra.mcm.appian.domain.excelpojo;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Design {
    public String key;
    public String liquidVolumeCapacity;
    public String nicotineConcentration;
    public String voltageWattageAdjustable;
    public String voltage;
    public String wattage;
    public String nicotineDoseUptakeFile;
    public String productionFile;
    public String productionConformity;
    public String qualitySafety;
    public String openningRefillFile;
    public String childTemperProof;
    public String highPurity;
    public String nonRisk;
    public String consistentDosing;
    public String leafletFile;
    public String coilResistance;
    public String description;

    /**
     *
     * @param line
     */
    public DO_Design(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            liquidVolumeCapacity = data[1];
            nicotineConcentration = data[2];
            voltageWattageAdjustable = data[3];
            voltage = data[4];
            wattage = data[5];
            nicotineDoseUptakeFile = data[6];
            productionFile = data[7];
            productionConformity = data[8];
            qualitySafety = data[9];
            openningRefillFile = data[10];
            childTemperProof = data[11];
            highPurity = data[12];
            nonRisk = data[13];
            consistentDosing = data[14];
            leafletFile = data[15];
            coilResistance = data[16];
            description = data[17];
        }
    }

    public DO_Design(String[] dataUpdated) {
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
        }else if(field.equals("liquidvolumecapacity")){
            liquidVolumeCapacity = fieldValue;
        }else if(field.equals("nicotineconcentration")){
            nicotineConcentration = fieldValue;
        }else if(field.equals("voltagewattageadjustable")){
            voltageWattageAdjustable = fieldValue;
        }else if(field.equals("voltage")){
            voltage = fieldValue;
        }else if(field.equals("wattage")){
            wattage = fieldValue;
        }else if(field.equals("nicotinedoseuptakefile")){
            nicotineDoseUptakeFile = fieldValue;
        }else if(field.equals("productionfile")){
            productionFile = fieldValue;
        }else if(field.equals("productionconformity")){
            productionConformity = fieldValue;
        }else if(field.equals("qualitysafety")){
            qualitySafety = fieldValue;
        }else if(field.equals("openingrefillfile")){
            openningRefillFile = fieldValue;
        }else if(field.equals("childtamperproof")){
            childTemperProof = fieldValue;
        }else if(field.equals("highpurity")){
            highPurity = fieldValue;
        }else if(field.equals("nonrisk")){
            nonRisk = fieldValue;
        }else if(field.equals("consistentdosing")){
            consistentDosing = fieldValue;
        }else if(field.equals("leafletfile")){
            leafletFile = fieldValue;
        }else if(field.equals("coilresistance")){
            coilResistance = fieldValue;
        }else if(field.equals("description")){
            description = fieldValue;
        }
    }

    @Override
    public boolean equals(Object obj) {
        DO_Design o = (DO_Design) obj;
        return this.key.equals(o.key);
    }

    @Override
    public String toString() {
        return "DO_Design{" +
                "key='" + key + '\'' +
                ", liquidVolumeCapacity='" + liquidVolumeCapacity + '\'' +
                ", nicotineConcentration='" + nicotineConcentration + '\'' +
                ", voltageWattageAdjustable='" + voltageWattageAdjustable + '\'' +
                ", voltage='" + voltage + '\'' +
                ", wattage='" + wattage + '\'' +
                ", nicotineDoseUptakeFile='" + nicotineDoseUptakeFile + '\'' +
                ", productionFile='" + productionFile + '\'' +
                ", productionConformity='" + productionConformity + '\'' +
                ", qualitySafety='" + qualitySafety + '\'' +
                ", openningRefillFile='" + openningRefillFile + '\'' +
                ", childTemperProof='" + childTemperProof + '\'' +
                ", highPurity='" + highPurity + '\'' +
                ", nonRisk='" + nonRisk + '\'' +
                ", consistentDosing='" + consistentDosing + '\'' +
                ", leafletFile='" + leafletFile + '\'' +
                ", coilResistance='" + coilResistance + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

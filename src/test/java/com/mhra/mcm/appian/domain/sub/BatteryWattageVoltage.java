package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

/**
 *
 */
public class BatteryWattageVoltage {

    public String liquidVolumeCapacity;
    public String batteryType;
    public String wattage;
    public String voltageWattageAdjustable;
    public String voltage;
    public String nicotineConcentration;
    public String batteryCapacity;
    public String wattageLowerRange;
    public String wattageUpperRange;
    public String voltageLowerRange;
    public String voltageUpperRange;


    public BatteryWattageVoltage(){
        this.liquidVolumeCapacity = "2";
        this.nicotineConcentration = "20";
        createDefault();
    }

    public BatteryWattageVoltage(String lvc, String nc){
        this.liquidVolumeCapacity = lvc;
        this.nicotineConcentration = nc;
        createDefault();
    }

    private void createDefault() {
        batteryType = "";
        wattage = "";
        voltageWattageAdjustable = "1";
        voltage = "";
        batteryCapacity = "";
        wattageLowerRange = "";
        wattageUpperRange = "";
        voltageLowerRange = "";
        voltageUpperRange = "";
    }

    @Override
    public String toString() {
        return "BatteryWattageVoltage{" +
                "liquidVolumeCapacity='" + liquidVolumeCapacity + '\'' +
                ", batteryType='" + batteryType + '\'' +
                ", wattage='" + wattage + '\'' +
                ", voltageWattageAdjustable='" + voltageWattageAdjustable + '\'' +
                ", voltage='" + voltage + '\'' +
                ", nicotineConcentration='" + nicotineConcentration + '\'' +
                ", batteryCapacity='" + batteryCapacity + '\'' +
                ", wattageLowerRange='" + wattageLowerRange + '\'' +
                ", wattageUpperRange='" + wattageUpperRange + '\'' +
                ", voltageLowerRange='" + voltageLowerRange + '\'' +
                ", voltageUpperRange='" + voltageUpperRange + '\'' +
                '}';
    }
}

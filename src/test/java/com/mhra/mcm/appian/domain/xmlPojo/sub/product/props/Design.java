package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.design.*;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Design {

    @XmlElement(name = "Description")
    private Description description;
    @XmlElement(name = "LiquidVolumeCapacity")
    private LiquidVolumeCapacity liquidVolumeCapacity;
    @XmlElement(name = "NicotineConcentration")
    private NicotineConcentration nicotineConcentration;
    @XmlElement(name = "VoltageWattageAdjustable")
    private VoltageWattageAdjustable voltageWattageAdjustable;
    @XmlElement(name = "Voltage")
    private Voltage voltage;
    @XmlElement(name = "Wattage")
    private Wattage wattage;
    @XmlElement(name = "NicotineDoseUptakeFile")
    private NicotineDoseUptakeFile nicotineDoseUptakeFile;
    @XmlElement(name = "ProductionFile")
    private ProductionFile productionFile;
    @XmlElement(name = "ProductionConformity")
    private ProductionConformity productionConformity;
    @XmlElement(name = "QualitySafety")
    private QualitySafety qualitySafety;
    @XmlElement(name = "OpeningRefillFile")
    private OpeningRefillFile openingRefillFile;
    @XmlElement(name = "ChildTamperProof")
    private ChildTamperProof childTamperProof;
    @XmlElement(name = "HighPurity")
    private HighPurity highPurity;
    @XmlElement(name = "NonRisk")
    private NonRisk nonRisk;
    @XmlElement(name = "ConsistentDosing")
    private ConsistentDosing consistentDosing;
    @XmlElement(name = "LeafletFile")
    private LeafletFile leafletFile;
    @XmlElement(name = "CoilResistance")
    private CoilResistance coilResistance;

    public Design(String desc) {
        description = new Description(desc);
        liquidVolumeCapacity = new LiquidVolumeCapacity("1.5");
        nicotineConcentration = new NicotineConcentration("0");
        voltageWattageAdjustable = new VoltageWattageAdjustable("4");
        voltage = new Voltage("0");
        wattage = new Wattage("0");
        productionConformity = new ProductionConformity("true");
        qualitySafety = new QualitySafety("true");
        childTamperProof = new ChildTamperProof("false");
        highPurity = new HighPurity("true");
        nonRisk = new NonRisk("false");
        consistentDosing = new ConsistentDosing("true");
        coilResistance = new CoilResistance("2.8");

        //attachment
        nicotineDoseUptakeFile = new NicotineDoseUptakeFile("");
        productionFile = new ProductionFile("");
        openingRefillFile = new OpeningRefillFile("");
        leafletFile = new LeafletFile("");
    }
}

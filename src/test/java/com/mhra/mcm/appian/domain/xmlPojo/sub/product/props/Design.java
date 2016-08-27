package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.excelpojo.DO_Design;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.design.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

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

    public Design(DO_Design doDesign) {

        description = new Description(doDesign.description + RandomDataUtils.getRandomTestName("desc"));
        liquidVolumeCapacity = new LiquidVolumeCapacity(doDesign.liquidVolumeCapacity);
        nicotineConcentration = new NicotineConcentration(doDesign.nicotineConcentration);
        voltageWattageAdjustable = new VoltageWattageAdjustable(doDesign.voltageWattageAdjustable);
        voltage = new Voltage(doDesign.voltage);
        wattage = new Wattage(doDesign.wattage);
        productionConformity = new ProductionConformity(doDesign.productionConformity);
        qualitySafety = new QualitySafety(doDesign.qualitySafety);
        childTamperProof = new ChildTamperProof(doDesign.childTemperProof);
        highPurity = new HighPurity(doDesign.highPurity);
        nonRisk = new NonRisk(doDesign.nonRisk);
        consistentDosing = new ConsistentDosing(doDesign.consistentDosing);
        coilResistance = new CoilResistance(doDesign.coilResistance);

        //attachment
        nicotineDoseUptakeFile = new NicotineDoseUptakeFile(doDesign.nicotineDoseUptakeFile);
        productionFile = new ProductionFile(doDesign.productionFile);
        openingRefillFile = new OpeningRefillFile(doDesign.openningRefillFile);
        leafletFile = new LeafletFile(doDesign.leafletFile);
    }
}

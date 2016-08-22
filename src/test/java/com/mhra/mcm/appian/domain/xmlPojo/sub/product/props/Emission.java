package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Emission {

    @XmlElement(name = "CasNumber")
    private CasNumber casNumber;
    @XmlElement(name = "Quantity")
    private Quantity quantity;
    @XmlElement(name = "Unit")
    private Unit unit;
    @XmlElement(name = "Name")
    private Name name;

//    @XmlElement(name = "MethodsFile")
//    private MethodsFile methodsFile;

    @XmlElementWrapper(name = "MethodsFile")
    @XmlElement(name = "Attachment")
    public List<Attachment> attachments = new ArrayList<>();


    public Emission(){
        casNumber = new CasNumber(RandomDataUtils.generateCASNumber());
        quantity = new Quantity(RandomDataUtils.getRandomNumberBetween(1, 20));
        unit = new Unit("±1.1mg/100puffs");
        name = new Name(RandomDataUtils.getRandomNumberBetween(1, 20));
        //methodsFile = new MethodsFile("");

        attachments.add(new Attachment(""));
        attachments.add(new Attachment(""));
        attachments.add(new Attachment(""));
    }
}

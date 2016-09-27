package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.Attachment;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.CasNumber;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.EmissionName;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.Quantity;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.Unit;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;

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
    private EmissionName emissionName;

    @XmlElementWrapper(name = "MethodsFile")
    @XmlElement(name = "Attachment")
    public List<Attachment> attachments = new ArrayList<>();


    public Emission(){
        casNumber = new CasNumber(RandomDataUtils.generateCASNumber());
        quantity = new Quantity(RandomDataUtils.getRandomNumberBetween(1, 20));
        unit = new Unit("±1.1mg/100puffs");
        emissionName = new EmissionName(RandomDataUtils.getRandomNumberBetween(1, 20));
        //methodsFile = new MethodsFile("");

        attachments.add(new Attachment(""));
        attachments.add(new Attachment(""));
        attachments.add(new Attachment(""));
    }

    public Emission(String casNumberGenerated, String emission1Quantity, String emission1Unit, String emission1Name, String addAttachment) {
        casNumber = new CasNumber(casNumberGenerated);
        quantity = new Quantity(emission1Quantity);
        unit = new Unit(emission1Unit);
        emissionName = new EmissionName(emission1Name);

        if(addAttachment.equals("true")){
            attachments.add(new Attachment("41da3cd9-5220-4b26-830c-d6fc991407b5"));
        }
    }

}

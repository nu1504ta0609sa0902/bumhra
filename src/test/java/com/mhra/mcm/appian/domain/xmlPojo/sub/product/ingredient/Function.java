package com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class Function {

    @XmlAttribute
    private boolean confidential;
    @XmlValue
    private String value;

    public Function(String value) {
        this.value = value;
    }
}

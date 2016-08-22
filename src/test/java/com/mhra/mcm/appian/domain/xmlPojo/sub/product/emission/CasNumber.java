package com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class CasNumber {

    @XmlAttribute
    private boolean confidential;
    @XmlValue
    private String value;

    public CasNumber(String value) {
        this.value = value;
    }
}

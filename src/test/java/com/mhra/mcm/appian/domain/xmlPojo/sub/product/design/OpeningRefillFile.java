package com.mhra.mcm.appian.domain.xmlPojo.sub.product.design;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class OpeningRefillFile {

    @XmlAttribute
    private String attachmentID;
    //@XmlValue
    //private String value;

    public OpeningRefillFile(String value) {
        //this.value = value;
        this.attachmentID = "ead14ac1-8392-489b-a777-7a84bb1b0de5";
    }
}

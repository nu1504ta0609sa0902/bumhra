package com.mhra.mcm.appian.domain.xmlPojo.sub.product.design;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class LeafletFile {

    @XmlAttribute
    private String attachmentID;
    //@XmlValue
    //private String value;

    public LeafletFile(String value) {
        //this.value = value;
        this.attachmentID = "93459d62-b50c-40ba-8aa1-4611a323d6b8";
    }
}

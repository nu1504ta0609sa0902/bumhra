package com.mhra.mcm.appian.domain.xmlPojo.sub.product.design;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class ProductionFile {

    @XmlAttribute
    private String attachmentID;
    //@XmlValue
    //private String value;

    public ProductionFile(String value) {
        //this.value = value;
        this.attachmentID = "65da4094-d0cd-4322-ab6c-782e2f46bb21";
    }
}

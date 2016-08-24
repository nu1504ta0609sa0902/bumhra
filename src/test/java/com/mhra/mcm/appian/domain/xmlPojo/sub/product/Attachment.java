package com.mhra.mcm.appian.domain.xmlPojo.sub.product;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class Attachment {

    @XmlAttribute
    private String attachmentID;
    //@XmlValue
    //private String value;

    public Attachment(String value) {
        //this.value = value;
        this.attachmentID = "d31e0105-ed9c-4a95-8ccb-2ba71e5b69f5";
    }
}

package com.mhra.mcm.appian.domain.xmlPojo.sub.product.presentation;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class UnitPacketPictureFile {

    @XmlAttribute
    private String attachmentID;
    //@XmlValue
    //private String value;

    public UnitPacketPictureFile(String value) {
        //this.value = value;
        this.attachmentID = "d31e0105-ed9c-4a95-8ccb-2ba71e5b69f5";
    }
}

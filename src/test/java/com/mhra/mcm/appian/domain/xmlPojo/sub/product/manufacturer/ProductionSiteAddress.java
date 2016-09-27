package com.mhra.mcm.appian.domain.xmlPojo.sub.product.manufacturer;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by TPD_Auto on 23/08/2016.
 */
public class ProductionSiteAddress {


    @XmlAttribute
    private boolean confidential;

    @XmlElement(name = "Address")
    private final String address;
    @XmlElement(name = "Country")
    private final String country;
    @XmlElement(name = "PhoneNumber")
    private final String phoneNumber;
    @XmlElement(name = "Email")
    private final String email;


    public ProductionSiteAddress(String address, String country, String phoneNumber, String email){
        this.address = address;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

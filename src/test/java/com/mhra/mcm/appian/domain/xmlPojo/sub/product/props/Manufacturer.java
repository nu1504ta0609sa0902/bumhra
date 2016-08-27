package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.excelpojo.DO_Address;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.Attachment;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.CasNumber;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.EmissionName;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.Quantity;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission.Unit;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.manufacturer.ProductionSiteAddress;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Manufacturer {
    //@XmlAttribute
    private boolean confidential;

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Address")
    private final String address;
    @XmlElement(name = "Country")
    private final String country;
    @XmlElement(name = "PhoneNumber")
    private final String phoneNumber;
    @XmlElement(name = "Email")
    private final String email;

    @XmlElementWrapper(name = "ProductionSiteAddresses")
    @XmlElement(name = "ProductionSiteAddress")
    public List<ProductionSiteAddress> productionSiteAddresses = new ArrayList<>();


    public Manufacturer(String name, String address, String country, String phoneNumber, String email, DO_Address site1, DO_Address site2){
        this.name = name;
        this.address = address;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;

        if(site1!=null)
        productionSiteAddresses.add(new ProductionSiteAddress(site1.address, site1.country, site1.phoneNumber, site1.email));
        if(site2!=null)
        productionSiteAddresses.add(new ProductionSiteAddress(site2.address, site2.country, site2.phoneNumber, site2.email));
    }

}

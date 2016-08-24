package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

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


    public Manufacturer(String name, String address, String country, String phoneNumber, String email){
        this.name = name;
        this.address = address;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;

        productionSiteAddresses.add(new ProductionSiteAddress("123 Production Address, Shenzhen, China.", "CN", "86(0)777-888441094", "mhra.uat@gmail.com"));
        productionSiteAddresses.add(new ProductionSiteAddress("124 Production Address, Shenzhen, China.", "CN", "86(0)777-888441095", "mhra.uat@gmail.com"));
    }
}

package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class OtherProductsExist {


    @XmlAttribute
    private boolean confidential;
    @XmlValue
    private String otherProduct;

    public OtherProductsExist(boolean otherProduct){
        this.otherProduct = ""+otherProduct;
    }
}

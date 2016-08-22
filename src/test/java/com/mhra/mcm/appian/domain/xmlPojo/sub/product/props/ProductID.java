package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class ProductID {

    @XmlAttribute
    private boolean confidential;
    @XmlValue
    private String productId;

    public ProductID(String ecId) {
        this.productId = ecId;
    }

//    public boolean isConfidential() {
//        return confidential;
//    }
//
//    public void setConfidential(boolean confidential) {
//        this.confidential = confidential;
//    }

//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
}

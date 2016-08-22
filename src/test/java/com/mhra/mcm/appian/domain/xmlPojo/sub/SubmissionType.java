package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.sub.Address;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
//@XmlRootElement(name="SubmissionType")
public class SubmissionType {

    @XmlAttribute
    public boolean confidential;
    @XmlValue
    private String type;

    public SubmissionType(){
        this.type = "Testing";
    }

    public SubmissionType(boolean confidential, String type) {
        this.confidential = confidential;
        this.type = type;
    }

    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    public String getType() {
        return type;
    }
}

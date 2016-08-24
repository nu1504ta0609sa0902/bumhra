package com.mhra.mcm.appian.domain.xmlPojo.sub;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
//@XmlRootElement(name="SubmissionType")
public class GeneralComment {

    @XmlAttribute
    public boolean confidential;
    @XmlValue
    private String type;

    public GeneralComment(){
        this.type = "Testing";
    }

    public GeneralComment(boolean confidential, String type) {
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

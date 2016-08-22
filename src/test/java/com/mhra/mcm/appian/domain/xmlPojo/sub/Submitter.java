package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.xmlPojo.sub.submitter.HasAffiliate;
import com.mhra.mcm.appian.domain.xmlPojo.sub.submitter.HasEnterer;
import com.mhra.mcm.appian.domain.xmlPojo.sub.submitter.Parent;

import javax.xml.bind.annotation.*;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Submitter {

    @XmlAttribute
    public boolean confidential;
    @XmlAttribute
    public String submitterId;

    @XmlElement(name = "SubmitterType")
    public String submitterType;
    @XmlElement
    public boolean hasEnterer;
    @XmlElement
    public boolean hasParent;
    @XmlElement
    public boolean hasAffiliate;

    @XmlElement(name = "Parent")
    public Parent parent;
    @XmlElement(name = "Enterer")
    public HasEnterer enterer;
    @XmlElement(name = "Affiliate")
    public HasAffiliate affiliate;

    public Submitter(){
        this.confidential = false;
        this.submitterId = "GetRandomID";
        this.submitterType = "MANUFACTURER";

        this.hasAffiliate = false;
        this.hasEnterer = false;
        this.hasParent = false;
    }

    public Submitter(boolean confidential, String submitterId, String submitterType, boolean hasEnterer, boolean hasAffiliate, boolean hasParent) {
        this.confidential = confidential;
        this.submitterId = submitterId;
        this.submitterType = submitterType;

        this.hasAffiliate = hasAffiliate;
        this.hasEnterer = hasEnterer;
        this.hasParent = hasParent;

        if(hasEnterer)
            enterer = new HasEnterer();

        if(hasAffiliate)
            affiliate = new HasAffiliate();

        if(hasParent)
            parent = new Parent();
    }

//    public String getSubmitterType() {
//        return submitterType;
//    }
//
//    public void setSubmitterType(String submitterType) {
//        this.submitterType = submitterType;
//    }
//
//    public boolean isHasEnterer() {
//        return hasEnterer;
//    }
//
//    public void setHasEnterer(boolean hasEnterer) {
//        this.hasEnterer = hasEnterer;
//    }
//
//    public boolean isHasParent() {
//        return hasParent;
//    }
//
//    public void setHasParent(boolean hasParent) {
//        this.hasParent = hasParent;
//    }
//
//    public boolean isHasAffiliate() {
//        return hasAffiliate;
//    }
//
//    public void setHasAffiliate(boolean hasAffiliate) {
//        this.hasAffiliate = hasAffiliate;
//    }
//
//    public Parent getParent() {
//        return parent;
//    }
//
//    public void setParent(Parent parent) {
//        this.parent = parent;
//    }
//
//    public HasEnterer getEnterer() {
//        return enterer;
//    }
//
//    public void setEnterer(HasEnterer enterer) {
//        this.enterer = enterer;
//    }
//
//    public HasAffiliate getAffiliate() {
//        return affiliate;
//    }
//
//    public void setAffiliate(HasAffiliate affiliate) {
//        this.affiliate = affiliate;
//    }
}

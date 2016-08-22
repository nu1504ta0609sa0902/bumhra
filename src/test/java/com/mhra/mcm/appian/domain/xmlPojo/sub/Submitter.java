package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.xmlPojo.sub.submitter.HasAffiliate;
import com.mhra.mcm.appian.domain.xmlPojo.sub.submitter.HasEnterer;
import com.mhra.mcm.appian.domain.xmlPojo.sub.submitter.Parent;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Submitter {

    @XmlAttribute
    private boolean confidential;
    @XmlAttribute
    private String submitterId;
    private String submitterType;
    private boolean hasEnterer;
    private boolean hasParent;
    private boolean hasAffiliate;

    private Parent parent;
    private HasEnterer enterer;
    private HasAffiliate affiliate;

    public Submitter(boolean confidential, String submitterId, String submitterType, boolean hasEnterer, boolean hasAffiliate, boolean hasParent) {
        this.confidential = confidential;
        this.submitterId = submitterId;
        this.submitterType = submitterType;

        this.hasAffiliate = hasAffiliate;
        this.hasEnterer = hasEnterer;
        this.hasParent = hasParent;
    }

    public String getSubmitterType() {
        return submitterType;
    }

    public void setSubmitterType(String submitterType) {
        this.submitterType = submitterType;
    }

    public boolean isHasEnterer() {
        return hasEnterer;
    }

    public void setHasEnterer(boolean hasEnterer) {
        this.hasEnterer = hasEnterer;
    }

    public boolean isHasParent() {
        return hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public boolean isHasAffiliate() {
        return hasAffiliate;
    }

    public void setHasAffiliate(boolean hasAffiliate) {
        this.hasAffiliate = hasAffiliate;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public HasEnterer getEnterer() {
        return enterer;
    }

    public void setEnterer(HasEnterer enterer) {
        this.enterer = enterer;
    }

    public HasAffiliate getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(HasAffiliate affiliate) {
        this.affiliate = affiliate;
    }
}

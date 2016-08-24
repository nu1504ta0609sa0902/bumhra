package com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.Attachment;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class ToxicologicalDetails {

    //
    @XmlElement(name = "ToxicologicalDataAvailable")
    private ToxicologicalDataAvailable toxicologicalDataAvailable;
    @XmlElement(name = "ToxEmission")
    private ToxEmission toxEmission;
    @XmlElement(name = "ToxCmr")
    private ToxCmr toxCmr;
    @XmlElement(name = "ToxCardioPulmonary")
    private ToxCardioPulmonary toxCardioPulmonary;
    @XmlElement(name = "ToxAddictive")
    private ToxAddictive toxAddictive;
    @XmlElement(name = "ToxOther")
    private ToxOther toxOther;

    @XmlElementWrapper(name = "ToxCmrFiles")
    @XmlElement(name = "Attachment")
    public List<Attachment> toxCmrFiles = new ArrayList<>();

    @XmlElementWrapper(name = "ToxCardioPulmonaryFiles")
    @XmlElement(name = "Attachment")
    public List<Attachment> toxCardioPulmonaryFiles = new ArrayList<>();

    @XmlElementWrapper(name = "ToxOtherFiles")
    @XmlElement(name = "Attachment")
    public List<Attachment> toxOtherFiles = new ArrayList<>();

    public ToxicologicalDetails(String value) {
        toxicologicalDataAvailable = new ToxicologicalDataAvailable("2");
        toxEmission = new ToxEmission("false");
        toxCmr = new ToxCmr("true");
        toxCardioPulmonary = new ToxCardioPulmonary("true");
        toxAddictive = new ToxAddictive("false");
        toxOther = new ToxOther("false");

        //toxCmrFiles.add(new Attachment("afb907cb-2b2f-4fbf-8def-a9c9ae5e960b"));
        //toxCardioPulmonaryFiles.add(new Attachment("afb907cb-2b2f-4fbf-8def-a9c9ae5e960b"));
        //toxOtherFiles.add(new Attachment("afb907cb-2b2f-4fbf-8def-a9c9ae5e960b"));

    }

    public void setToxCardioPulmonary(ToxCardioPulmonary toxCardioPulmonary) {
        this.toxCardioPulmonary = toxCardioPulmonary;
    }

    public void setToxCardioPulmonaryFile() {
        toxCardioPulmonaryFiles.add(new Attachment("afb907cb-2b2f-4fbf-8def-a9c9ae5e960b"));
    }

    public void setToxCmr(ToxCmr toxCmr) {
        this.toxCmr = toxCmr;
    }

    public void setToxCmrFile() {
        toxCmrFiles.add(new Attachment("afb907cb-2b2f-4fbf-8def-a9c9ae5e960b"));
    }

    public void setToxOther(ToxOther toxother) {
        this.toxOther = toxother;
    }

    public void setToxOtherFile() {
        toxOtherFiles.add(new Attachment("afb907cb-2b2f-4fbf-8def-a9c9ae5e960b"));
    }
}

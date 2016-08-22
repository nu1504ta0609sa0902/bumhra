package com.mhra.mcm.appian.domain.xmlPojo.sub.product.emission;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.props.Emission;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/08/2016.
 */
public class MethodsFile {

    @XmlElementWrapper(name = "Attachments")
    @XmlElement(name = "Attachment")
    public List<Attachment> attachments = new ArrayList<>();

    public MethodsFile(String value) {
        attachments.add(new Attachment(""));
        attachments.add(new Attachment(""));
        attachments.add(new Attachment(""));
    }
}

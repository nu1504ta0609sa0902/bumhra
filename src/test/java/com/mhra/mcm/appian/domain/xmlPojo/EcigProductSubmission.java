package com.mhra.mcm.appian.domain.xmlPojo;

import com.mhra.mcm.appian.domain.excelpojo.*;
import com.mhra.mcm.appian.domain.xmlPojo.sub.*;
import com.mhra.mcm.appian.utils.helpers.FileUtils;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * Created by TPD_Auto on 22/07/2016.
 *
 * Capture all the mandatory fields
 *
 * NOTE THIS WILL USE RANDOM DATA
 *
 * - ONLY FOR TESTING PURPOSE
 */
@XmlRootElement(name = "EcigProductSubmission")
public class EcigProductSubmission {

    private String ecIDNumber;
    private String euId;

    @XmlElement(name = "Submitter")
    private Submitter submitter;
    @XmlElement(name = "SubmissionType")
    private SubmissionType submissionType;
    @XmlElement(name = "GeneralComment")
    private GeneralComment generalComment;
    @XmlElement(name = "Product")
    private Product product;

    public EcigProductSubmission(){
        String euIdentifier = RandomDataUtils.getEUIdentifier(null);
        String ecId = RandomDataUtils.getECID(euIdentifier);
        ecIDNumber = ecId;
        euId = euIdentifier;
        printInstruction();
    }

    private void printInstruction() {
        System.out.println("----------------------------------------------");
        System.out.println("MAKE SURE THE FOLLOWING: ");
        System.out.println("1.EXCEL SHEET SHOULD NOT CONTAIN EMPTY VALUES");
        System.out.println("\tREPLACE EMPTY CELLS WITH : none");
        System.out.println("2.REPLACE ANY COMMAS WITH ++");
        System.out.println("----------------------------------------------");
    }

    public String getEcIDNumber() {
        return ecIDNumber;
    }
    public String getEUId() {
        return euId;
    }

    public Submitter getSubmitter() {
        if(submitter == null)
            submitter = new Submitter(euId);
        return submitter;
    }

    public SubmissionType getSubmissionType() {
        if(submissionType == null)
            submissionType = new SubmissionType();
        return submissionType;
    }

    public GeneralComment getGeneralComment() {
        return generalComment;
    }

    public Product getProduct() {
        if(product == null)
            product = new Product();
        return product;
    }

    public static void main(String args[]){
        EcigProductSubmission n = NotificationUtils.generateXMLFromExcelData();
        NotificationUtils.createXmlNotificationData(n);
        System.out.println(n);
    }


    @Override
    public String toString() {
        String desc =  "EcigProductSubmission{" +
                "ecIDNumber='" + ecIDNumber + '\'' +
                ", euId='" + euId + '\'' +
                ", submitter=" + submitter +
                ", submissionType=" + submissionType +
                ", generalComment=" + generalComment +
                ", product=" + product +
                '}';

        return desc;
    }
}

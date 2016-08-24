package com.mhra.mcm.appian.domain.xmlPojo;

import com.mhra.mcm.appian.domain.xmlPojo.sub.*;
import com.mhra.mcm.appian.utils.helpers.FileUtils;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.Date;

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
    }

    public EcigProductSubmission(int weight, int volume, String fixedEUID){
        String euIdentifier = RandomDataUtils.getEUIdentifier(fixedEUID);
        String ecId = RandomDataUtils.getECID(euIdentifier);
        ecIDNumber = ecId;

        submitter = new Submitter(false, euIdentifier, "MANUFACTURER", true, true, true );
        submissionType = new SubmissionType(false, "1");
        generalComment = new GeneralComment(false, "Testing general comment");
        product = new Product(weight, volume, ecId);

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

//    public EcigProductSubmission(int weight, int volume, String fixedEUID, String submitterType, String st, boolean hasEnterer, boolean hasAffiliate, boolean hasParent){
//        String euIdentifier = RandomDataUtils.getEUIdentifier(fixedEUID);
//        String ecId = RandomDataUtils.getECID(euIdentifier);
//        ecIDNumber = ecId;
//
//        submitter = new Submitter(false, euIdentifier, submitterType, hasEnterer, hasAffiliate, hasParent );
//        submissionType = new SubmissionType(false, st);
//        //generalComment = new GeneralComment(false, "Testing general comment");
//        product = new Product(weight, volume, ecId);
//
//    }

    public static void generateXml(EcigProductSubmission n){
        try {
            JAXBContext context = JAXBContext.newInstance(EcigProductSubmission.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(n, System.out);

            String sn = new Date().toString().substring(0, 18).replaceAll(" ", "").replace(":","");
            sn = "";
            String tmp = FileUtils.getFileFullPath("tmp", "test"+ sn +  ".xml");
            System.out.println(tmp);
            m.marshal(n, new File(tmp));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[]){
        EcigProductSubmission
                n = new EcigProductSubmission(10,10,null);
                //n = new EcigProductSubmission();
        //System.out.println(n);

        try {
            JAXBContext context = JAXBContext.newInstance(EcigProductSubmission.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(n, System.out);

            String sn = new Date().toString().substring(0, 18).replaceAll(" ", "").replace(":","");
            sn = "";
            String tmp = FileUtils.getFileFullPath("tmp", "test"+ sn +  ".xml");
            System.out.println(tmp);
            m.marshal(n, new File(tmp));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}

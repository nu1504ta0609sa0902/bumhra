package com.mhra.mcm.appian.domain.xmlPojo;

import com.mhra.mcm.appian.domain.xmlPojo.sub.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by TPD_Auto on 22/07/2016.
 *
 * Capture all the mandatory fields
 *
 * NOTE THIS WILL USE RANDOM DATA
 *
 * - ONLY FOR TESTING PURPOSE
 */
@XmlRootElement
public class EcigProductSubmission {

    private String ecIDNumber;
    private Submitter submitter;
    private SubmissionType submissionType;
    private Product product;

    public EcigProductSubmission(){
        double weight = 2;
        double volume = 2;
        String euIdentifier = RandomDataUtils.getEUIdentifier(null);
        String ecId = RandomDataUtils.getECID(euIdentifier);
        ecIDNumber = ecId;

        submitter = new Submitter(false, euIdentifier, "MANUFACTURER", false, false, false );
        submissionType = new SubmissionType(false, "1");
        product = new Product(weight, volume, ecId);
    }

    public EcigProductSubmission(int weight, int volume, String fixedEUID){
        String euIdentifier = RandomDataUtils.getEUIdentifier(fixedEUID);
        String ecId = RandomDataUtils.getECID(euIdentifier);
        ecIDNumber = ecId;

    }

    public String getEcIDNumber() {
        return ecIDNumber;
    }

    public void setEcIDNumber(String ecIDNumber) {
        this.ecIDNumber = ecIDNumber;
    }

    public Submitter getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }

    public SubmissionType getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(SubmissionType submissionType) {
        this.submissionType = submissionType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static void main(String args[]){
        EcigProductSubmission n = new EcigProductSubmission(10,10,null);
        System.out.println(n);

        try {
            JAXBContext context = JAXBContext.newInstance(EcigProductSubmission.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(n, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}

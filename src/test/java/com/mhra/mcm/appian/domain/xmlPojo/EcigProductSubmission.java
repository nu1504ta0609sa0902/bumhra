package com.mhra.mcm.appian.domain.xmlPojo;

import com.mhra.mcm.appian.domain.excelpojo.*;
import com.mhra.mcm.appian.domain.xmlPojo.sub.*;
import com.mhra.mcm.appian.utils.helpers.FileUtils;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.Date;
import java.util.List;
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

            //m.marshal(n, System.out);

            String sn = new Date().toString().substring(0, 18).replaceAll(" ", "").replace(":","");
            sn = "";
            String tmp = FileUtils.getFileFullPath("tmp", "test"+ sn +  ".xml");
            System.out.println(tmp);
            m.marshal(n, new File(tmp));

            ExcelUtils excelUtils = new ExcelUtils();
//            List<DO_Submitter> submitter = excelUtils.getListOfSubmitters("configs/data/xmlTestData1.xlsx", "Submitter");
//            List<DO_Product> product = excelUtils.getListOfProduct("configs/data/xmlTestData1.xlsx", "Product");
//            List<DO_Ingredient> ingredient = excelUtils.getListOfIngredient("configs/data/xmlTestData1.xlsx", "Ingredient");
//            List<DO_Emission> emission = excelUtils.getListOfEmission("configs/data/xmlTestData1.xlsx", "Emission");
//            List<DO_Manufacturer> manufacturer = excelUtils.getListOfManufacturer("configs/data/xmlTestData1.xlsx", "Manufacturer");
//            List<DO_Presentation> presentation = excelUtils.getListOfPresentation("configs/data/xmlTestData1.xlsx", "Presentation");
//            List<DO_Design> design = excelUtils.getListOfDesign("configs/data/xmlTestData1.xlsx", "Design");
//            List<DO_Address> toxicologicalDetails = excelUtils.getListOfToxicologyDetails("configs/data/xmlTestData1.xlsx", "ToxicologicalDetails");
//            List<DO_Address> addresses = excelUtils.getListOfAddresses("configs/data/xmlTestData1.xlsx", "Addresses");

            Map<String, List> mapOfExcelData = excelUtils.getAllData("configs/data/xmlTestData1.xlsx");
            System.out.println(mapOfExcelData);

            Map<String, DO_Submitter> submitter = excelUtils.getMapOfSubmitters("configs/data/xmlTestData1.xlsx", "Submitter");
            Map<String, DO_Product> product = excelUtils.getMapOfProduct("configs/data/xmlTestData1.xlsx", "Product");
            Map<String, DO_Ingredient> ingredient = excelUtils.getMapOfIngredients("configs/data/xmlTestData1.xlsx", "Ingredient");
            Map<String, DO_Emission> emission = excelUtils.getMapOfEmissions("configs/data/xmlTestData1.xlsx", "Emission");
            Map<String, DO_Manufacturer> manufacturer = excelUtils.getMapOfManufacturers("configs/data/xmlTestData1.xlsx", "Manufacturer");
            Map<String, DO_Presentation> presentation = excelUtils.getMapOfPresentations("configs/data/xmlTestData1.xlsx", "Presentation");
            Map<String, DO_Design> design = excelUtils.getMapOfDesigns("configs/data/xmlTestData1.xlsx", "Design");
            Map<String, DO_ToxicologyDetails> toxicologicalDetails = excelUtils.getMapOfToxicologyDetails("configs/data/xmlTestData1.xlsx", "ToxicologicalDetails");
            Map<String, DO_Address> addresses = excelUtils.getMapOfAddress("configs/data/xmlTestData1.xlsx", "Addresses");
            System.out.println(submitter);


            Map<String, Map> allDataAsMap = excelUtils.getAllDataAsMap("configs/data/xmlTestData1.xlsx");
            System.out.println(allDataAsMap);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}

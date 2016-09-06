package com.mhra.mcm.appian.utils.helpers.others;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by TPD_Auto on 06/09/2016.
 * <p>
 * Schema validation
 */
public class XMLSchemaValidation {


    public static void main(String args[]) {

        String schemaDefinitionFile = "C:\\Selenium\\xmlData\\originalFiles\\schema\\submissions2.xsd";
        String xmlOriginalFiles = "C:\\Selenium\\xmlData\\originalFiles";

        //XML files to validate
        File location = new File(xmlOriginalFiles);
        String locationInputFullPath = location.getAbsolutePath();
        String[] listOfXmlFiles = location.list();

        //Schema validation
        for (String fileName : listOfXmlFiles) {
            try {
                String xmlToValidate = locationInputFullPath + File.separator + fileName;
                File isFile = new File(xmlToValidate);
                if (isFile.isFile()) {
                    //Validate xml files against XSD
                    XMLSchemaValidation xsv = new XMLSchemaValidation();
                    boolean valid = xsv.simpleValidation(xmlToValidate, schemaDefinitionFile);
                    //boolean valid = xsv.validateXMLSchema(xmlToValidate, schemaDefinitionFile);
                    //boolean valid = xsv.validateJAXBXmlSchema(xmlToValidate, schemaDefinitionFile);
                    System.out.println("Is valid : " + valid + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done");
    }


    public static boolean simpleValidation(String xmlFilePath, String xsdPath) {
        boolean valid = true;
        InputSource is = null;
        StreamSource xmlFileSource = new StreamSource(new File(xmlFilePath));
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(xmlFileSource, new SAXResult());
        } catch (IOException e) {
            System.out.println(xmlFileSource.getSystemId() + " is NOT valid");
            System.out.println("IOException: " + e.getMessage());
            valid = false;
        } catch (SAXException e1) {
            System.out.println(xmlFileSource.getSystemId() + " is NOT valid");
            System.out.println("SAX Exception: " + e1.getMessage());
            valid = false;
        } catch (Exception e) {
            System.out.println(xmlFileSource.getSystemId() + " is NOT valid");
            System.out.println("Exception: " + e.getMessage());
            valid = false;
        }

        return valid;
    }

    public static boolean validateXMLSchema(String xmlFilePath, String xsdPath) {

        final List<SAXParseException> exceptions = new ArrayList<>();
        try {
            System.out.println("\nValidate File : " + xmlFilePath);
            System.out.println("Schema File : " + xsdPath);

//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            docFactory.setNamespaceAware(true);
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            Document document = docBuilder.parse(xmlFilePath);

            //Validate xml
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();

            //Capture tags which are invalid
            validator.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }


            });

            validator.validate(new StreamSource(new File(xmlFilePath)));
            //validator.validate(new DOMSource(document));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e1) {
            System.out.println("SAX Exception: " + e1.getMessage());
            //return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(exceptions);

        return true;

    }


//    public static boolean validateJAXBXmlSchema(String xmlFile, String schemaFile){
//        try{
//            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = sf.newSchema(new File(schemaFile));
//
//            JAXBContext jc = JAXBContext.newInstance();
//
////            Unmarshaller unmarshaller = jc.createUnmarshaller();
////            unmarshaller.setSchema(schema);
////            unmarshaller.setEventHandler(new MyValidationEventHandler());
////            unmarshaller.unmarshal(new File(xmlFile));
//
//            Marshaller marshaller = jc.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.setSchema(schema);
//            marshaller.setEventHandler(new MyValidationEventHandler());
//            marshaller.marshal(new File(xmlFile), System.out);
//        }catch(SAXException e1){
//            System.out.println("SAX Exception: "+e1.getMessage());
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//
//    static class MyValidationEventHandler implements ValidationEventHandler {
//
//        public boolean handleEvent(ValidationEvent event) {
//            System.out.println("\nEVENT");
//            System.out.println("SEVERITY:  " + event.getSeverity());
//            System.out.println("MESSAGE:  " + event.getMessage());
//            System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
//            System.out.println("LOCATOR");
//            System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
//            System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
//            System.out.println("    OFFSET:  " + event.getLocator().getOffset());
//            System.out.println("    OBJECT:  " + event.getLocator().getObject());
//            System.out.println("    NODE:  " + event.getLocator().getNode());
//            System.out.println("    URL:  " + event.getLocator().getURL());
//            return true;
//        }
//
//    }
}

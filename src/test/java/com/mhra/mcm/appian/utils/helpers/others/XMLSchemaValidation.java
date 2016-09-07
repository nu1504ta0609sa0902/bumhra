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
import javax.xml.transform.Source;
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

        String schemaDefinitionFile = "C:\\Selenium\\xmlData\\originalFiles\\schema\\submissions.xsd";
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
                    System.out.println("\n----------------------------------------------------\n");
                    //boolean valid = xsv.simpleValidation(xmlToValidate, schemaDefinitionFile);
                    boolean valid = xsv.validateXMLSchema(xmlToValidate, schemaDefinitionFile);
                    System.out.println("IS VALID : " + valid);
//                    if(!valid){
//                        System.out.println("\n----------------------------------------------------\n");
//                        System.out.println("INVALID FILE FOUND : " + xmlToValidate + "\n");
//                        System.out.println("Validate File : " + xmlToValidate);
//                        System.out.println("Schema Definition File : " + schemaDefinitionFile);
//                        System.out.println("\n----------------------------------------------------\n");
//                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n----------------------------------------------------\n");
        System.out.println("ALL SCHEMA VALIDATION COMPLETE");
        System.out.println("\n----------------------------------------------------\n");
    }


    public static boolean simpleValidation(String xmlFilePath, String xsdPath) {
        System.out.println("\nValidate File : " + xmlFilePath);
        System.out.println("Schema File : " + xsdPath);
        boolean valid = true;
        InputSource is = null;
        Source xmlFileSource = new StreamSource(new File(xmlFilePath));
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(xmlFileSource);
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
        boolean isValid = true;
        final List<SAXParseException> exceptions = new ArrayList<>();
        try {
            //XML file and schema definition file
            System.out.println("\nValidate File : " + xmlFilePath);
            System.out.println("Schema File : " + xsdPath);

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

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            isValid = false;
        } catch (SAXException e) {
            System.out.println("SAX Exception: " + e.getMessage());
            isValid = false;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            isValid = false;
        }

        if(!isValid || exceptions.size() > 0){
            isValid = false;
            for(SAXParseException e: exceptions){
                System.out.println();
                //Print any issues found
                System.out.println("INVALID File Name : " + e.getSystemId());
                System.out.println("Issue : " + e.getLocalizedMessage());
                System.out.println("Line Number : " + e.getLineNumber());
            }
        }

        return isValid;

    }


}

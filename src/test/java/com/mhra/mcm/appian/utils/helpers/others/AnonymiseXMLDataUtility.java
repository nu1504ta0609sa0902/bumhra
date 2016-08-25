package com.mhra.mcm.appian.utils.helpers.others;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class AnonymiseXMLDataUtility {

//    ArrayList<String> lines = new ArrayList<String>();
//    String line = null;
//
//    public void doIt() {
//
//        String tmp = FileUtils.getFileFullPath("tmp", "test.xml");
//        File f1=null;
//        FileReader fr=null;
//        BufferedReader br=null;
//        FileWriter fw=null;
//        BufferedWriter out=null;
//        try {
//            f1 = new File(tmp);
//            fr = new FileReader(f1);
//            br = new BufferedReader(fr);
//            while ((line = br.readLine()) != null) {
//                lines.add(line);
//            }
//
//            fw = new FileWriter(f1);
//            out = new BufferedWriter(fw);
//            for (String s : lines)
//                out.write(s);
//            out.flush();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try{
//                fr.close();
//                br.close();
//                out.close();
//            }catch(IOException ioe)
//
//            {
//                ioe.printStackTrace();
//            }
//        }
//
//    }
//

    public static void XMLReplaceTextWith(String original, String created, String searchForTag, String replaceWith, List<String> ignoreTagList) {
        try {
            String filepath = original;
            String writeTo = created;
            System.out.println("Input : " + filepath);
            System.out.println("Output : " + writeTo);
            System.out.println("Search for tags matching xpath : " + searchForTag);
            System.out.println("Replace with following text : " + replaceWith);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = null;
            try {
                expr = xpath.compile(searchForTag);
                NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                //element count
                System.out.println("Number of items to annonymise : " + list.getLength());

                for (int i = 0; i < list.getLength(); i++) {

                    Node node = list.item(i);
                    boolean contains = ignoreTagList.contains(node.getNodeName());
                    if(!contains) {
                        NodeList children = node.getChildNodes();
                        if (children.getLength() > 1) {
                            for (int x = 0; x < children.getLength(); x++) {
                                Node child = children.item(x);
                                if (child.getNodeType() == Node.ELEMENT_NODE) {
                                    String childText = child.getTextContent();
                                    child.setTextContent(replaceWith);
                                    //System.out.println(child.getNodeName() + " " + childText);
                                }
                            }
                        } else {
                            //System.out.println("No children");
                            node.setTextContent(replaceWith);
                        }
                    }


//                    Node node = list.item(i);
//                    node.setTextContent("CONFIDENTIAL_DATA");


                }

                //Write new file to disk
                Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(new File(writeTo)));

            } catch (XPathExpressionException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }

            System.out.println("Done\n");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static void main(String args[]) {
        String xmlFolderLocation = "C:\\Selenium\\xmlData\\originalFiles";
        File locationOutput = new File("C:\\Selenium\\xmlData\\AnonymisedFiles" + new Date().toString().substring(0, 16).replace(":", "").replace(" ", "").trim());
        locationOutput.mkdir();
        File location = new File(xmlFolderLocation);
        String locationInputFullPath = location.getAbsolutePath();
        String locationOutputFullPath = locationOutput.getAbsolutePath();

        List<String> listOfIgnoreTags = getListOfIgnoreTags();


        String[] listOfXmlFiles = location.list();

        AnonymiseXMLDataUtility fr = new AnonymiseXMLDataUtility();
        for (String fileName : listOfXmlFiles) {
            try {
                String original = locationInputFullPath + "\\" + fileName;
                String created = locationOutputFullPath + "\\" + fileName;

                File isFile = new File(original);
                if(isFile.isFile())
                fr.XMLReplaceTextWith(original, created, ".//*[@confidential='true']", "CONFIDENTIAL DATA", listOfIgnoreTags);
            } catch (Exception e) {

            }
        }

    }

    private static List<String> getListOfIgnoreTags() {
        List<String> listOfIgnoreTags = new ArrayList<>();
        //listOfIgnoreTags.add("Submitter");
        //listOfIgnoreTags.add("Parent");
        //listOfIgnoreTags.add("LiquidVolumeCapacity");
        return listOfIgnoreTags;
    }
}
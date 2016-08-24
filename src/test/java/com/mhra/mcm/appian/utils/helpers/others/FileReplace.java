package com.mhra.mcm.appian.utils.helpers.others;
import com.mhra.mcm.appian.utils.helpers.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class FileReplace {
    ArrayList<String> lines = new ArrayList<String>();
    String line = null;

    public void doIt() {

        String tmp = FileUtils.getFileFullPath("tmp", "test.xml");
        File f1=null;
        FileReader fr=null;
        BufferedReader br=null;
        FileWriter fw=null;
        BufferedWriter out=null;
        try {
            f1 = new File(tmp);
            fr = new FileReader(f1);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            fw = new FileWriter(f1);
            out = new BufferedWriter(fw);
            for (String s : lines)
                out.write(s);
            out.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                fr.close();
                br.close();
                out.close();
            }catch(IOException ioe)

            {
                ioe.printStackTrace();
            }
        }

    }


    public static void anotherWay(){
        try {
            String tmp = FileUtils.getFileFullPath("tmp", "testCopy.xml");
            String writeTo = FileUtils.getFileFullPath("tmp", "data_new.xml");
            String filepath = tmp;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = null;
            try {
                expr = xpath.compile(".//*[@confidential='true']");
                NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                //element count
                System.out.println("Number of items : " + list.getLength());

                for (int i = 0; i < list.getLength(); i++) {

                    Node node = list.item(i);
                    String textContent = node.getTextContent();
                    System.out.println(textContent);
                    //Replace all strings with *
                    String alphaOnly = textContent.replaceAll("(?s).", "*");
                    if(!alphaOnly.contains("*"))
                        alphaOnly = textContent.replaceAll("\\P{L}", "X");

//                    if(textContent.contains("*\\d+.*")){
//                        System.out.println("coidsf");
//                        String[] split = textContent.split("-");
//                        for(String x: split){
//                            alphaOnly = alphaOnly + x.replaceAll("\\P{L}", "X");
//                        }
//                    }

                    System.out.println(alphaOnly);

                    node.setTextContent(alphaOnly);
                    //node.setTextContent("CONFIDENTIAL_DATA");

                    //System.out.println(node);

                }


                Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(new File(writeTo)));

            } catch (XPathExpressionException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }

            System.out.println("Done");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static void main(String args[]) {
        FileReplace fr = new FileReplace();
        //fr.doIt();

        fr.anotherWay();
    }
}
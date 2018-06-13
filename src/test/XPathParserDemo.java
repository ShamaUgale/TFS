package test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

public class XPathParserDemo {
	
	private static String getInnerXML(String xpath,File inputFile) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
	    StringWriter sw = new StringWriter();
	    try {
	    	 DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();

	         String expression = xpath;	        
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
	         for (int i = 0; i < nodeList.getLength(); i++) {
	            Node nNode = nodeList.item(i);
	            
	      Transformer t = TransformerFactory.newInstance().newTransformer();
	      t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	      t.setOutputProperty(OutputKeys.INDENT, "yes");
	      t.transform(new DOMSource(nNode), new StreamResult(sw));
	         }
	    } catch (TransformerException te) {
	      System.out.println("nodeToString Transformer Exception");
	    }
	    return sw.toString();
	  }
	
	private static String nodeToString(Node node) {
	    StringWriter sw = new StringWriter();
	    try {
	      Transformer t = TransformerFactory.newInstance().newTransformer();
	      t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	      t.setOutputProperty(OutputKeys.INDENT, "yes");
	      t.transform(new DOMSource(node), new StreamResult(sw));
	    } catch (TransformerException te) {
	      System.out.println("nodeToString Transformer Exception");
	    }
	    return sw.toString();
	  }
	

	
   public static void main(String[] args) {
      try {
    	  
 //*********************************************************PROJECTS******************************************************************************************

         File inputFile = new File("C:\\Users\\shama.ugale\\Documents\\Export script and output xmls\\output\\projects.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
         dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         XPath xPath =  XPathFactory.newInstance().newXPath();

         String expression = "/Projects/Project";	        
         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
         // iterates thru projects
         System.out.println("nodeList.getLength()  : "+nodeList.getLength());
         for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
//               System.out.println("Project Name : " + eElement.getAttribute("Name"));
               System.out.println("Project id : " + eElement.getAttribute("Id"));
//               System.out.println("Project ResourceType : " + eElement.getAttribute("ResourceType"));
//               System.out.println("Description : " + eElement.getElementsByTagName("Description").item(0).getTextContent());  
//               System.out.println("EntityState : " + nodeToString(nNode));         
               
               // this has to be written to a 1.xml file  (project id is 1)
               String project= getInnerXML("/Projects/Project["+(i+1)+"]", inputFile);
               System.out.println(project);
               System.out.println("******************************** End of PROJECT node "+i+"**************************************");
               
               
 //************************************************************EPICS***************************************************************************************
               // iterate thru epics.xml file now and search epics realted to  corresponding project
               File inputFile_epics = new File("C:\\Users\\shama.ugale\\Documents\\Export script and output xmls\\output\\epics.xml");
               DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
               DocumentBuilder dBuilder1 ;
               dBuilder1 = dbFactory1.newDocumentBuilder();
               Document doc1 = dBuilder1.parse(inputFile_epics);
               doc1.getDocumentElement().normalize();
               XPath xPath1 =  XPathFactory.newInstance().newXPath();

               String expression1 = "/Epics/Epic";	        
               NodeList nodeList1 = (NodeList) xPath1.compile(expression1).evaluate(doc1, XPathConstants.NODESET);
               // iterates thru projects
               System.out.println("nodeList.getLength()  : "+nodeList1.getLength());
               for (int j = 0; j < nodeList1.getLength(); j++) {
                  Node nNode1 = nodeList1.item(j);
                  System.out.println("\nCurrent Element :" + nNode1.getNodeName());
                  if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                     Element eElement1 = (Element) nNode1;
//                     System.out.println("Project Name : " + eElement.getAttribute("Name"));
                     System.out.println("Project id : " + eElement.getAttribute("Id"));

                     System.out.println("Epic id : " + eElement1.getAttribute("Id"));
//                     System.out.println("Project ResourceType : " + eElement.getAttribute("ResourceType"));
//                     System.out.println("Description : " + eElement.getElementsByTagName("Description").item(0).getTextContent());  
//                     System.out.println("EntityState : " + nodeToString(nNode));         
                     
                     // this has to be written to a 1.xml file  (project id is 1)
                     String epics= getInnerXML("/Epics/Epic["+(j+1)+"]", inputFile_epics);
                     System.out.println(epics);
                     System.out.println("******************************** End of EPIC node "+j+"**************************************");
                  }// end of epic element
               }// end of 
               
            }//end of project element 
         }// end of projects.xml
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (XPathExpressionException e) {
         e.printStackTrace();
      }
   }
}
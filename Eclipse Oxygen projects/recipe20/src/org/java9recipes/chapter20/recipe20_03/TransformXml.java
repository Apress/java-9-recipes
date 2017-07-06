package org.java9recipes.chapter20.recipe20_03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
/**
 * Recipe 20-3:  Transforming XML
 * @author John O'Conner (john@joconner.com)
 * Update: J. Juneau
 */
public class TransformXml {
    
    
    public void run(String xmlFile, String xslFile, String outputFile) throws FileNotFoundException, TransformerConfigurationException, TransformerException {
        InputStream xslInputStream = new FileInputStream(xslFile);
        Source xslSource = new StreamSource(xslInputStream);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslSource);
        InputStream xmlInputStream = new FileInputStream(xmlFile);
        StreamSource in = new StreamSource(xmlInputStream);
        StreamResult out = new StreamResult(outputFile);
        transformer.transform(in, out);
        
    }
    
    public static void main(String[] args) {
        String fileName = null;
        String fileName2 = null;
        String fileName3 = null;
        if (args.length != 3) {
            System.out.printf("Usage: java org.java8recipes.chapter20.recipe20_3.TransformXml <xmlFile> <xslFile> <outputFile>\n");
            fileName = "recipe20_03/patients.xml";
            fileName2 = "recipe20_03/patients.xsl";
            fileName3 = "recipe20_03/patients.html";
        } else {
            fileName = args[0];
            fileName2 = args[1];
            fileName3 = args[2];
        }
        TransformXml app = new TransformXml();
        try {
            app.run(fileName, fileName2, fileName3);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }
}

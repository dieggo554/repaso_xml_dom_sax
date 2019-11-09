/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlumnosIdDOM;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;

/**
 *
 * @author dpinepadi
 */

//Para trabajar con un id en DOM es imprescindible validar
public class Principal {
    
    //Especifica el lenguaje utilizado por el parser en el análisis con XSD
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    //especifica el espacio de nombres para XSD
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    private static Document documento;
    private static DocumentBuilder analizador;

    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);
        
//        validar XSD
//        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
        
        analizador = null;
        documento = null;
        
        try {
            
            analizador = dbf.newDocumentBuilder();
            documento = analizador.parse(new File("AlumnosID.xml"));
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DocumentType docType = documento.getDoctype();
        if(docType == null){
            System.out.println("No hay DTD asociado");
        } else {
            System.out.println("Nombre del DTD: " + docType.getName() +
                    " El nombre del fichero DTD: " + docType.getSystemId());
        }
        
        
        
        
        Node raiz = documento.getDocumentElement();
        
        
    }
}

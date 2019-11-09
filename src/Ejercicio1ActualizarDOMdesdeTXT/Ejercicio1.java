/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1ActualizarDOMdesdeTXT;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author dpinepadi
 */
public class Ejercicio1 {

    static final String XML = "Actores.xml";
    static final String TXT = "ModificaFecha.txt";
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    private static Document documento;
    private static DocumentBuilder analizador;
    private static String campo = "DataNacemento";

    public static void main(String[] args) {
        LeerTexto lector = new LeerTexto(TXT);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);

        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

        try {

            analizador = dbf.newDocumentBuilder();
            documento = analizador.parse(XML);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }

        lector.abrirArchivo();
        String linea;
        while ((linea = lector.leerLinea()) != null) {
            String id = linea.substring(0, linea.indexOf(","));
            String fecha = linea.substring(linea.indexOf(",") + 1);

            modificarElemento(id, campo, fecha, documento);
        }
        
        grabarDOM();

        lector.cerrarArchivo();
    }

    public static void modificarElemento(String id, String campo, String contenido, Document documento) {
        Element elemento = documento.getElementById(id);
        if (elemento != null) {
            NodeList listaHijos = elemento.getChildNodes();
            for (int i = 0; i < listaHijos.getLength(); i++) {
                if(listaHijos.item(i).getNodeName().equals(campo)){
                        Text data = (Text) listaHijos.item(i).getChildNodes().item(0);
                        data.setData(contenido);
                }
            }
        }
    }

    private static void grabarDOM() {
        TransformerFactory transFact;

        transFact = TransformerFactory.newInstance();
        transFact.setAttribute("indent-number", 3);
        Transformer trans = null;
        
        try {
            trans = transFact.newTransformer();
            //añadir sangrado
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        } catch (TransformerConfigurationException ex) {
            System.out.println("Error construir el motor de transformación");
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }

        DOMSource domSource = new DOMSource(documento);
        
        FileWriter fw = null;

        try {
            fw = new FileWriter(XML);

        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }

        StreamResult sr = new StreamResult(fw);

        //añadimos una salida para consola
        StreamResult consola = new StreamResult(System.out);

        try {
            trans.transform(domSource, sr);
            trans.transform(domSource, consola);
        } catch (TransformerException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

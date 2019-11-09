/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2ReemplazarTexto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dpinepadi
 */
public class Ejercicio2 {

    private static String XML = "DatosEmpleados.xml";
    private static String TXT = "CartaEmpleados.txt";
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    private static Document documento;
    private static DocumentBuilder analizador;
    private static LeerTexto lector = new LeerTexto(TXT);
    private static String separador;
//    private static TreeMap valores = new TreeMap();
    private static ArrayList<String> nombres = new ArrayList<>();
    private static ArrayList<String> valores = new ArrayList<>();

    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);

        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

        analizador = null;
        documento = null;

        try {

            analizador = dbf.newDocumentBuilder();
            documento = analizador.parse(XML);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }

        int numeroElementos;
        numeroElementos = documento.getDocumentElement().getChildNodes().getLength() - 1;
        Node nodo = documento.getDocumentElement().getFirstChild();
        separador = nodo.getFirstChild().getTextContent();

        for (int j = 0; j < numeroElementos; j++) {
            nodo = nodo.getNextSibling();
            NodeList listaHijos = nodo.getChildNodes();
            for (int i = 0; i < listaHijos.getLength(); i++) {
                Node hijo = listaHijos.item(i);
                String nombre, texto;
                nombre = hijo.getNodeName();
                nombres.add(nombre.trim());
                texto = hijo.getTextContent();
                valores.add(texto.trim());
//                valores.put(nombre, texto);
            }
            completarCarta();
            nombres.clear();
            valores.clear();
        }
        lector.cerrarArchivo();
    }

    private static void completarCarta() {
        
//        StringBuilder carta = new StringBuilder();

        lector.abrirArchivo();
        String linea;
        linea = lector.leerLinea();

        while (linea != null) {
//            carta.append(linea);
            for (int i = 0; i < nombres.size(); i++) {
//                int indice = linea.indexOf(nombres.get(i));
//                if (indice != -1) {
                linea = linea.replaceAll("(?i)" + separador + nombres.get(i) + separador, valores.get(i));
//                }
            }
            imprimirLinea(linea);
            linea = lector.leerLinea();
        }
    }

    private static void imprimirLinea(String linea) {
        System.out.println(linea);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioPesca;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author dpinepadi
 */
public class Principal {

    private static Pesca pesca;
    private static JAXBContext contexto = null;
    private static Unmarshaller um = null;
    private static File fichero = null;
    private static Marshaller m = null;

    public static void main(String[] args) {

        try {

            contexto = JAXBContext.newInstance(Pesca.class);
            um = contexto.createUnmarshaller();

        } catch (JAXBException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        fichero = new File("Pesca_1.xml");

        try {

            pesca = (Pesca) um.unmarshal(fichero);

        } catch (JAXBException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

       //Visualizar
        
        try {
            
            m = contexto.createMarshaller();
            
        } catch (JAXBException ex) {
            System.out.println("Error al crear el Marshaller");
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
        } catch (PropertyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            m.marshal(pesca, System.out);
            
        } catch (JAXBException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

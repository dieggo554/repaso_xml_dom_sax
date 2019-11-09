package Ejercicio2ReemplazarTexto;

import Ejercicio1ActualizarDOMdesdeTXT.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dpinepadi
 */
public class LeerTexto extends Archivo {

    BufferedReader entrada;

    public LeerTexto(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            entrada = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException ex) {
            System.out.println("Error al abrir el archivo");
        } catch (IOException ex) {
            Logger.getLogger(LeerTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cerrarArchivo() {
        try {
            //comprobar si es null antes de cerrar
            entrada.close();
        } catch (IOException ex) {
            System.out.println("Error al cerrar el archivo");
        }
    }

    public String leerLinea() {
        while (true) {
            try {
                return entrada.readLine();
            } catch (IOException ex) {
                Logger.getLogger(LeerTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

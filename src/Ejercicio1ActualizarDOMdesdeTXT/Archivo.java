package Ejercicio1ActualizarDOMdesdeTXT;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dpinepadi
 */
public abstract class Archivo {
    File ruta;
    
    public Archivo(String ruta){
        this.ruta = new File(ruta);
    }
    
    public abstract void abrirArchivo();
    
    public abstract void cerrarArchivo();
    
    public  boolean existe(){
        return (ruta.exists());
    }
    
    public boolean crearCarpeta(){
        boolean creada = false;
            if (ruta.mkdirs()){
                System.out.println("Carpeta madre creada");
                creada = true;
            } else {
                System.out.println("La carpeta ya existe");
                creada = false;
            }
        return creada;
    }
}

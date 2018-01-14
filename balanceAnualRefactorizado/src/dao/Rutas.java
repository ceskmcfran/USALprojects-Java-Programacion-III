/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 *
 * @author Patrulla Echo: David Flores, Juan Arellano, Fran Blázquez, Victor Castilla.
 */
public class Rutas {
 
    
    //Metodo que estrae la ruta del escritorio
    public static Path pathToDesktop(){ 
        Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
            File.separator
            + "Desktop");
        return p;
    }
    
    //Metodo que estrae la ruta de una carpeta del escritorio
    public static Path pathToFolderOnDesktop(String nombreCarpeta){ 
        Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
            File.separator
            + "Desktop"
            + File.separator
            + nombreCarpeta);
        return p;
    }
    
    //Metodo que estrae una ruta de un archivo del escritorio
    public static Path pathToFileOnDesktop(String nombreArchivo){   
        Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
            File.separator
            + "Desktop"
            + File.separator
            + nombreArchivo);
        return p;
    }
    
    //Metodo que estrae la ruta de un archivo en una carpeta
    //que le pasamos el nombre como argumento
    public static Path pathToFileInFolderOnDesktop(String nombreCarpeta, String nombreArchivo){ 
        Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),              
            File.separator
            + "Desktop"
            + File.separator
            + nombreCarpeta
            + File.separator
            + nombreArchivo);
        return p;

    }
}
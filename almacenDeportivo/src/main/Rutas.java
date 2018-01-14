/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class Rutas {
    
    private static String nombreCarpeta;
    private final String carpetaSeguridad = "backup"; 
    private final String carpetaAlmacen = "articulos";
    private final String carpetaPedidos = "pedidos";
    
    /**
     * Proporciona la ruta al escritorio.
     * @return Ruta al escritorio
     */
    public static Path pathToDesktop(){
        
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
        
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop");
            return p;
            
        }
        else{
            
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Escritorio");
            return p;
            
        }
    }
    
    /**
     * Proporciona la ruta a un directorio a situada en el escritorio.
     * @param nombreDirectorio
     * @return Ruta a un directorio en el escritorio
     */
    public static Path pathToFolderOnDesktop(String nombreDirectorio){
        
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
        
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Desktop"
                    + File.separator
                    + nombreDirectorio);
            return p;
            
        }
        else{
            
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Escritorio"
                    + File.separator
                    + nombreDirectorio);
            return p;
            
        }
    }
    
    /**
     * Proporciona la ruta a un fichero situado en el escritorio.
     * @param nombreFichero
     * @return Ruta a un fichero en el escritorio
     */
    public static Path pathToFileOnDesktop(String nombreFichero){
        
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
        
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Desktop"
                    + File.separator
                    + nombreFichero);
            return p;
            
        }
        else{
            
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Escritorio"
                    + File.separator
                    + nombreFichero);
            return p;
            
        }
        
    }
    
    /**
     * Proporciona la ruta a un fichero situado en un directorio del escritorio.
     * @param nombreFichero
     * @param nombreDirectorio
     * @return Ruta a un fichero en un directorio en el escritorio
     */
    public static Path pathToFileOnFolderOnDesktop(String nombreFichero, String nombreDirectorio){
        
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
        
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Desktop"
                    + File.separator
                    + nombreDirectorio
                    + File.separator
                    + nombreFichero);
            return p;
            
        }
        else{
            
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Escritorio"
                    + File.separator
                    + nombreDirectorio
                    + File.separator
                    + nombreFichero);
            return p;
            
        }
        
        
    }
    
    /**
     * Proporciona la ruta a un fichero situado en un subdirectorio de un directorio del escritorio.
     * @param nombreFichero
     * @param nombreSubDirectorio
     * @param nombreDirectorio
     * @return 
     */
    public static Path pathToFileOnFolderOnFolderOnDesktop(String nombreFichero, String nombreSubDirectorio, String nombreDirectorio){
        
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
        
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Desktop"
                    + File.separator
                    + nombreDirectorio
                    + File.separator
                    + nombreSubDirectorio 
                    + File.separator
                    + nombreFichero);
            return p;
            
        }
        else{
            
            Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                    File.separator
                    + "Escritorio"
                    + File.separator
                    + nombreDirectorio
                    + File.separator
                    + nombreSubDirectorio 
                    + File.separator
                    + nombreFichero);
            return p;
            
        }
        
        
    }
    
    /**
     * Crea o enlaza las rutas a la carpeta del escritorio.
     */
    public void crearCarpetaPrincipal(){
        
        Scanner scan = new Scanner(System.in);
        
        /*Se pide el nombre de la carpeta del escritorio. Si no existe, se creara*/
        System.out.print("Introduzca el nombre de la carpeta del escritorio(Si la carpeta no existe, se creara): ");
        setNombreCarpeta(scan.nextLine());
        /*Convertimos la ruta de la carpeta a String para crear un tipo File*/
        File directorio = new File(pathToFolderOnDesktop(nombreCarpeta).toString());
        /*Si no existe ese directorio se crea*/
        if(!directorio.exists()){
            
            directorio.mkdir();
            
        }
        
        /*Convertimos la ruta de la carpeta a String para crear un tipo File*/
        File directorioS = new File(pathToFileOnFolderOnDesktop(carpetaSeguridad, nombreCarpeta).toString());
        /*Si no existe ese directorio se crea*/
        if(!directorioS.exists()){
            
            directorioS.mkdir();
            
        }
        
        /*Convertimos la ruta de la carpeta a String para crear un tipo File*/
        File directorioA = new File(pathToFileOnFolderOnDesktop(carpetaAlmacen, nombreCarpeta).toString());
        /*Si no existe ese directorio se crea*/
        if(!directorioA.exists()){
            
            directorioA.mkdir();
            
        }
        
        /*Convertimos la ruta de la carpeta a String para crear un tipo File*/
        File directorioP = new File(pathToFileOnFolderOnDesktop(carpetaPedidos, nombreCarpeta).toString());
        /*Si no existe ese directorio se crea*/
        if(!directorioP.exists()){
            
            directorioP.mkdir();
            
        }
    }
    
    
    /*METODOS SETTER AND GETTER*/
    
    /**
     * 
     * @return Nombre de la carpeta principal del escritorio
     */
    public String getNombreCarpeta() {
        return nombreCarpeta;
    }

    /**
     *
     * @param nombreCarpeta
     */
    public void setNombreCarpeta(String nombreCarpeta) {
        this.nombreCarpeta = nombreCarpeta;
    }

    /**
     *
     * @return Nombre de la carpeta donde se guardan las copias de seguridad.
     */
    public String getCarpetaSeguridad() {
        return carpetaSeguridad;
    }

    /**
     *
     * @return Nombre de la carpeta donde se guarda el almacen.
     */
    public String getCarpetaAlmacen() {
        return carpetaAlmacen;
    }

    /**
     * 
     * @return Nombre de la carpeta donde se guardan los pedidos.
     */
    public String getCarpetaPedidos() {
        return carpetaPedidos;
    }
    
    
    
}

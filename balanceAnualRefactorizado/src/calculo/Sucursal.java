/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculo;

import dao.Rutas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Patrulla Echo: David Flores, Juan Arellano, Fran Blázquez, Victor Castilla.
 */
public class Sucursal{
    
    private static final String archivoGastos = "gastos.txt"; //Archivo de gastos de la sucursal
    private static final String archivoIngresos = "ingresos.txt"; //Archivo ingresos de la sucursal
    private final Path rutaGastos, rutaIngresos; //Rutas de los archivos
    private double[] gastos, ingresos; //Vector de los gastos y vector de ingresos de la sucursal
    private double totalIngresos, totalGastos; //Total de gastos e ingresos 
    private final String nombreSucursal; //Nombre de la sucursal

    public Sucursal(String directorioPrincipal, String nombreSucursal){
        
        this.nombreSucursal = nombreSucursal;

        /*Creamos la ruta del archivo de gastos*/
        rutaGastos = Rutas.pathToFileInFolderOnDesktop(directorioPrincipal +
                File.separator +
                nombreSucursal, archivoGastos);

        /*Validamos que el archivo de gastos existe*/
        if(!rutaGastos.toFile().exists()){
            System.err.println("El archivo no existe.");
        }
        /*Creamos la ruta del archivo de ingresos*/
        rutaIngresos = Rutas.pathToFileInFolderOnDesktop(directorioPrincipal +
                File.separator +
                nombreSucursal, archivoIngresos);
        
        /*Validamos que el archivo de ingresos existe*/
        if(!rutaIngresos.toFile().exists()){
            System.err.println("El archivo no existe.");
       }
        
        
    }
    
    public void importarDatosSucursal() throws IOException{
        
        //Leemos todos los gastos de nuestra sucursal
     //Por si hay problemas con la lectura de los double debido al . o la , de la separacion entre la parte entera y la parte
     //decimal, llamamos a este metodo. Que va a sustituir las comas por los puntos
     // 
     String linea;
     sustituirComasPorPuntos(rutaGastos.toString());
     sustituirComasPorPuntos(rutaIngresos.toString());
        
     try{
     Scanner Scan = new Scanner(new File(rutaGastos.toString()));
     int i=0;
     //Con esto sabemos cuantos datos tiene nuestro archivo de gastos
     while(Scan.hasNextLine()){
         String variableParaLeer;
         variableParaLeer = Scan.nextLine();
         i++;
     }
     Scan.close();
     
     
     //Creamos nuestro vector
     gastos = new double[i];
     i=0;
     //Le asignamos los datos a nuestro vector
     //Volvemos a abrir el archivo, y cogemos nuestros datos de dicho archivo
     Scan = new Scanner(new File(rutaGastos.toString()));
     while(Scan.hasNextLine()){
         linea=Scan.nextLine();
         gastos[i] = Double.parseDouble(linea);
         i++;
     }
     //Cerramos nuestro archivo
     Scan.close();
     
     ///////////////////////////////////////////////////////////////////////////
     //Hacemos lo mismo, pero con el archivo de Ingresos
     Scan = new Scanner(new File(rutaIngresos.toString()));
     i=0;
     //Con esto sabemos cuantos datos tiene nuestro archivo de gastos
     while(Scan.hasNextLine()){
         String variableParaLeer;
         variableParaLeer = Scan.nextLine();
         i++;
     }
     Scan.close();
     
     
     //Creamos nuestro vector
     ingresos = new double[i];
     i=0;
     //Le asignamos los datos a nuestro vector
     //Volvemos a abrir el archivo, y cogemos nuestros datos de dicho archivo
     Scan = new Scanner(new File(rutaIngresos.toString()));
     while(Scan.hasNextLine()){
         linea=Scan.nextLine();
         ingresos[i] = Double.parseDouble(linea);
         i++;
     }
     //Cerramos nuestro archivo
     Scan.close();

     }catch(FileNotFoundException F){
         System.out.println("Se ha producido un error abriendo el archivo de Gastos");
     }
    }
    
    public void ingresosTotalSucursal(){
    /*Aquí se calculan los beneficios totales de la ciudad*/
        for(int i=0;i<ingresos.length;i++){
            
            totalIngresos+=ingresos[i];

        }
        
    }
    
    public void gastoTotalSucursal(){
    /*Aquí se calculan los gastos totales de la ciudad*/
        for(int i=0;i<gastos.length;i++){
            totalGastos+=gastos[i];
        }
    }
    
    public void sustituirComasPorPuntos(String direccionArchivo){
           ArrayList<String> lineas = new ArrayList<>();
           String Temp;
           try{
               //Leemos linea a linea nuestro archivo
               Scanner Scan = new Scanner(new File(direccionArchivo));
               while(Scan.hasNextLine()){
                   Temp=Scan.nextLine();
                   //Si esa linea contiene el caracter ",", lo sustituye por un punto, y asi los podemos leer ya como doubles
                   if(Temp.contains(",")){
                       Temp = Temp.replace(",", ".");
                   }
                   lineas.add(Temp);
               }
               Scan.close();
               
               //Una vez leidas todas nuestras lineas del fichero, debemos volver a escribirlas
               PrintWriter Pw = new PrintWriter( new File(direccionArchivo));
               for(String linea : lineas){
               Pw.println(linea);
               }
               Pw.close();
           }catch(FileNotFoundException E){
               System.out.println("Fallo a la hora de abrir o escribir en nuestro fichero ubicado en la Ruta:"+direccionArchivo);
 
           }
           
    }
    
    public double getTotalIngresos() {  //Metodo que nos devuelve el total de ingresos
        return totalIngresos;
    }

    public double getTotalGastos() {    //Metodo que nos devuelve el total de gastos
        return totalGastos;
    }

    public double[] getGastos() {   //Metodo que nos devuelve los gastos
        return gastos;
    }

    public double[] getIngresos() { //Metodo que nos devuelve los ingresos
        return ingresos;
    }

    public String getNombreSucursal() { //Metodo que nos devuelve el nombre de la Sucursal
        return nombreSucursal;
    }
    
}

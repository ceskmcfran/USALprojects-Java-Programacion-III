/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculo;

import dao.Rutas;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrulla Echo: David Flores, Juan Arellano, Fran Blázquez, Victor Castilla.
 */
public class BalanceAnualRefactorizado {

    List<Sucursal> sucursal = new ArrayList<>(); //Array de las sucursales
    private Sucursal maxGastos; //Sucursal con el maximo gasto
    private Sucursal maxIngresos; //Sucursal con el maximo ingreso
    private double balanceAnual; //Balance anual
    private double totali=0;    //Variable que almacena el total de los ingresos
    private double totalg=0;    //Variable que almacena el total de los gastos
    private double gastoMaximo = 0; //Variable que almacena el maximo de los gastos
    private double ingresoMaximo = 0;   //Variable que almacena el maximo de los ingresos
    
  
    public BalanceAnualRefactorizado(String nombreCarpetaEnEscritorio){
        
        /*Le pasamos la ruta de un directorio en el escritorio*/
        Path p = Rutas.pathToFolderOnDesktop(nombreCarpetaEnEscritorio);
        /*Retorna las rutas de una lista con los ficheros y directorios de la ruta
        que se le ha pasado*/
        File[] contenido = p.toFile().listFiles();
        /*Recorremos los archivos del directorio*/
        for(File fichero : contenido){
            /*Si es un fichero oculto lo leido en la iteracion...*/
            if(fichero.isHidden()){
                /*No hacemos nada (continuamos el bucle)*/
                continue;
            }
            /*Si es un directorio lo leido en la iteracion...*/
            if(fichero.isDirectory()){
                /*Añadimos al arrayList "sucursal" un nuevo objeto de tipo Sucursal
                por cada iteracion del bucle pasandole como argumentos la ruta del
                directorio principal y el nombre del fichero para que forme la ruta del
                fichero*/
                sucursal.add(new Sucursal(nombreCarpetaEnEscritorio, fichero.getName()));
                
            }
        }
    }
    
    public void importarDatosDeSucursales(){
    /*Aqui se leen todos los datos de todas las cidades utilizando el metodo correspondiente de la clase Sucursal*/
        for(int i = 0; i<sucursal.size(); i++){
            
            try {
                sucursal.get(i).importarDatosSucursal();
            } catch (IOException ex) {
                System.out.println("Error en la importación de los datos");;
            }
            
        }
        
    }
    
    public void ingresosTotalesPorCiudad(){
    /*Aqui se hace que todas las ciudades calculen el total de sus beneficios*/
        
        for(int i=0; i<sucursal.size();i++){
            
            sucursal.get(i).ingresosTotalSucursal();
            totali+=sucursal.get(i).getTotalIngresos();
            
        }   
                     
    }

    public void gastosTotalesPorCiudad(){
    /*Aqui se hace que todas las ciudades calculen el total de sus gastos*/
      
      for(int i=0; i<sucursal.size();i++){
          
          sucursal.get(i).gastoTotalSucursal();
          totalg+=sucursal.get(i).getTotalGastos();
          
      }
    }
    
    public void ciudadMayoresIngresos(){
      /*Aquí se busca la ciudad con ingresos mas elevados, y se almacena su direccion en la variable preparada con este fin.*/
        double maxi = 0;
        /*Recorremos el arrayList de las sucursales*/
        for(int i=0; i<sucursal.size(); i++){
            /*Si el ingreso maximo es mayor que el ingreso maximo de una sucursal...*/
            if(maxi<sucursal.get(i).getTotalIngresos()){
                /*Sobreescribimos "max" con el ingreso maximo de la sucursal*/
                maxi = sucursal.get(i).getTotalIngresos();
                /*Ponemos en "maxIngresos" la sucursal que tiene el maximo ingreso*/
                maxIngresos = sucursal.get(i);
            }
        }
    }
    
    public void ciudadMayoresGastos(){
     /*Aquí se busca la ciudad con gactos mas elevados, y se almacena su direccion en la variable preparada con este fin.*/
        double maxg = 0;
        /*Recorremos el arrayList de las sucursales*/
        for(int i=0; i<sucursal.size(); i++){
            /*Si el gasto maximo es mayor que el gasto maximo de una sucursal...*/
            if(maxg<sucursal.get(i).getTotalGastos()){
                /*Sobreescribimos "max" con el gasto maximo de la sucursal*/
                maxg = sucursal.get(i).getTotalGastos();
                /*Ponemos en "maxGastos" la sucursal que tiene el maximo gasto*/
                maxGastos = sucursal.get(i);
            }
        }
        
    }    
      
    public void balanceAnual(){
    /*Aqui se calcula la suma de ingresos de todas las ciudades, y se le resta la sima de gastos de todas las ciudades. El resultado se almacena en la variable creada con este fin*/ 
    
        balanceAnual = totali - totalg;
        
    }
    
    public void imprimirTablaGeneral(){
        for(int i=0;i<sucursal.size();i++){
            System.out.println("       CIUDAD DE: "+sucursal.get(i).getNombreSucursal());
            System.out.println("-------------------------------------------");
            System.out.println("-------------------------------------------");
            System.out.println("|\t   GASTOS\t   INGRESOS\t|");
            System.out.println("-------------------------------------------");
            for(int j=0;j<sucursal.get(i).getGastos().length || j<sucursal.get(i).getIngresos().length;j++){
                if(j<sucursal.get(i).getGastos().length){
                    System.out.printf("|\t%8.2f   ||", sucursal.get(i).getGastos()[j] /*sucursal.get(i).getIngresos()[j]*/);
                    //System.out.printf("|\t%8.2f\t%8.2f\t|\n", sucursal.get(i).getGastos()[j], /*sucursal.get(i).getIngresos()[j]*/);
                }
                
                if(j<sucursal.get(i).getIngresos().length){
                    System.out.printf("    %8.2f\t|\n", sucursal.get(i).getIngresos()[j]);
                }
            }
            
            System.out.println("------------------------------------------");
            System.out.printf("Gastos: %8.2f Ingresos: %8.2f\n", sucursal.get(i).getTotalGastos(), sucursal.get(i).getTotalIngresos());
            System.out.println("******************************************");
            System.out.println("\n\n\n");
        }
        
        
    }
    
    public void imprimirResultados(){
        /*Aquí se mestra la ciudad que tiene mas gastos, la que tiene mas beneficios, y el balance anual.*/
        System.out.println("DATOS DE INTERES GENERAL\n");
        System.out.println("Ciudad con mas gastos:    "+maxGastos.getNombreSucursal());
        System.out.println("Ciudad con mas ingresos:  "+maxIngresos.getNombreSucursal());
        System.out.printf("Balance anual: %.2f\n", balanceAnual);
        
    }
    
     
}  
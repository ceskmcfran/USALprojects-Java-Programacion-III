/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pactica.pkg3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import Comparadores.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author Equipo Echo (Grupo PB1).
 * Francisco Blázquez Matías.
 * Juan Bautista Arellano Bruno.
 * David Flores Barbero.
 * Victor Castilla Garrido.
 */
public class Pasos {
    
    /**
     * Atribtos.
     */
    private String rutaCarpeta;
    private String rutaCarpetaDestino;
    private String delimitador = ";";
    private String[] nombreAlmacen;
    private int numeroAlmacenes;
    
    /**
     * ArrayList de los almacenes.
     */
    private ArrayList<Almacen> listadoAlmacen;
    
    /**
     * Vector con el nombre de los atributos de cada producto
     */
    private final String[] nombreAtributos={"Bodega", "Capacidad", "Denominacion", 
                                      "Fecha", "Graduacion", "Identificador", 
                                      "Nombre", "numBotellas", "PVD", "PVP", 
                                      "TipoUva"};

    
    /**
     * Aporta la ruta al directorio donde se va a encontrar nuestra futura carpeta.
     */
    public void rutaAlEscritorio(){
        
        String nombreEscritorio;
        /*Si el nombre del sistema operativo contiene "windows" pondremos en la
        ruta "Desktop"*/
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            nombreEscritorio = "Desktop";
        }
        /*Si no pondremos "Escritorio" (Para iOS o Linux)*/
        else{
            
            nombreEscritorio = "Escritorio";
        }
        /*Establecemos el comienzo de la ruta segun el sistema operativo*/
        setRutaCarpeta(System.getProperty("user.home")+File.separator+nombreEscritorio);
        setRutaCarpetaDestino(System.getProperty("user.home")+File.separator+nombreEscritorio);
        
    }
    
    /**
     * Pide al usuario que introduzca por teclado el nombre de la carpeta donde se encuentran los datos.
     */
    public void pedirCarpeta(){
        
        rutaAlEscritorio();
        
        String nombreCarpeta;
        boolean carpetaValida;
        Scanner scan = new Scanner(System.in);
        
        
        do{
            /*Pedimos al usuario el directorio donde se encuentran los datos*/
            System.out.printf("Teclee el nombre de la carpeta donde se encuentran los datos: ");
            nombreCarpeta = scan.nextLine();
        
            File folder = new File(getRutaCarpeta()+File.separator+nombreCarpeta);
            
            carpetaValida = folder.exists();
            
            /*Si el directorio existe listamos todos los archivos que no contengan 
            ".csv" y los eliminamos*/
            if(carpetaValida){
                
                File[] archivos = folder.listFiles();
                for(File archivo : archivos){
                    
                    if(!archivo.getName().contains(".csv")){
                        
                        archivo.delete();
                        
                    }
                }
            }
        
            /*Si el directorio existe y esta vacio ponemos el boolean a falso*/
            if(carpetaValida){
                
                File[] archivos = folder.listFiles();
                if(archivos.length==0){
                    
                    carpetaValida = false;
                    
                }
            }
            
        }while(!carpetaValida);
        
        /*Establecemos la ruta*/
        setRutaCarpeta(getRutaCarpeta()+File.separator+nombreCarpeta);
     
    }
    
    /**
     * Inicia el atributo ruta de cada almacen para poder acceder a el posteriormente
     */
    public void iniciarRutaALosArchivos(){
       
        listadoAlmacen = new ArrayList<>();
        File folder = new File(getRutaCarpeta());
        File[] archivos = folder.listFiles();
        /*Listamos los archivos y convertimos los archivos, pasamos la ruta del almacen a su atributo y
        añadimos al arrayList el almacen*/ 
        for(File archivo : archivos){
            
            Almacen Temp = new Almacen();
            Temp.setRuta(archivo.toString());
            Temp.setNombreAlmacen(archivo.getName());
            listadoAlmacen.add(Temp);
            
        }   
    }
    
    /**
     * Leemos uno a uno cada uno de los productos que tenemos en los distintos almacenes,
     * y vamos a ir añadiendolos al ArrayList de productos de cada uno de los almacenes.
     */
    public void leerDatos(){
        
        /*Recorremos el arrayList de los almacenes*/
        for(int i=0;i<listadoAlmacen.size();i++){
            try{

                Scanner scan = new Scanner(new File(listadoAlmacen.get(i).getRuta()));
                //Leemos el archivo linea a linea
                while(scan.hasNextLine()){
                String linea = scan.nextLine();
                
                /*Añadimos al arrayList de productos cada uno de los productos de cada uno de los archivos*/
                String[] vector = linea.split(";");
                Producto Temp = new Producto(vector[0],Float.parseFloat(vector[1]),vector[2],Integer.parseInt(vector[3]),vector[4],vector[5],vector[6],Integer.parseInt(vector[7]),vector[8],vector[9],vector[10]);
                listadoAlmacen.get(i).listadoProductos.add(Temp);
                }
            }catch(FileNotFoundException e){
                System.out.println("Fallo leyendo un dato del archivo: "+listadoAlmacen.get(i).getRuta());
            }
        }
    }
    
    /**
     * Presenta todos los datos de todos los almacenes por pantalla encolumnados.
     */
    public void presentarTodosAlmacenes(){
        
        int maxLongitud=0;
        
            for(int i=0;i<listadoAlmacen.size();i++){
                
                if(i==0){
                    
                    System.out.printf("%s", listadoAlmacen.get(i).getRuta());
                
                }
                else{
                    
                    System.out.printf("%288s", listadoAlmacen.get(i).getRuta());
                    
                }
            }
            System.out.println("");
            for(int i=0;i<288*listadoAlmacen.size();i++){
            
                System.out.printf("-");
            
            }
            System.out.println("");
            for(int i=0;i<listadoAlmacen.size();i++){
                for(int j=0;j<nombreAtributos.length;j++){
                
                    System.out.printf("%25s ", nombreAtributos[j]);
                
                }
                    System.out.printf("||");
            }
            System.out.println("");
            
            for(int i=0;i<288*listadoAlmacen.size();i++){
                
                System.out.printf("-");
                
            }
            System.out.println("");
            
            for(int i=0;i<listadoAlmacen.size();i++){
                if(listadoAlmacen.get(i).listadoProductos.size()>maxLongitud){
                    
                    maxLongitud = listadoAlmacen.get(i).listadoProductos.size();
                    
                }
            }
            
            for(int i=0;i<maxLongitud;i++){
                for(int j=0;j<listadoAlmacen.size();j++){
                    
                    if(i<listadoAlmacen.get(j).listadoProductos.size()){
                        
                        System.out.printf("%25s%25.2f%25s  %25d%25s%29s%25s%26d%25s%27s%26s ", 
                                            listadoAlmacen.get(j).listadoProductos.get(i).getBodega(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getCapacidad(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getDenominacionDeOrigen(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getFechaEnvasado(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getGraduacionAlcoholica(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getIdentificador(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getNombreVino(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getBotellasDisponibles(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getPVD(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getPVP(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getTipoUva());
                        
                    }
                    else{
                        for(int c=0;c<nombreAtributos.length;c++){
                            
                            System.out.printf("%25s ", "N/D");
                            
                        }
                    }
                    System.out.printf("||");
 
                }
                System.out.println("");
            }
            
    }

    /**
     * Ordena la salida de datos por pantalla mediante el campo que se le especifica
     * en el menu.
     */
    public void ordenarAlmacen(){
        
        int nAlmacen = 0;
        String campo;
        Scanner scan;
        boolean variableControl=true;
        boolean excepcion = true;
        String linea;
        scan = new Scanner(System.in);
        
        /*Presentamos las opciones a elegir por pantalla*/
        do{    
        
            for(int i=0;i<listadoAlmacen.size();i++){
            
                System.out.println(i+")"+"-"+listadoAlmacen.get(i).getNombreAlmacen());
            
            }
            System.out.print("\nIntroduce el almacen por el que ordenar: ");
            linea=scan.nextLine();
        
                try{
                    
                    nAlmacen = Integer.parseInt(linea);
                    excepcion = false;
                }catch(NumberFormatException e){
                    System.out.println("Caracter no valido.");
                }
        
        }while(nAlmacen<0 || nAlmacen>listadoAlmacen.size() || excepcion);
        
        
        /*Presentamos el menu*/
        do{
            System.out.println("Introduce el campo por el que se desea ordenar: ");
            System.out.println("a"+" ) "+"Bodega");
            System.out.println("b"+" ) "+"Botellas");
            System.out.println("c"+" ) "+"Capacidad");
            System.out.println("d"+" ) "+"Denominacion de origen");
            System.out.println("e"+" ) "+"Fecha envasado");
            System.out.println("f"+" ) "+"Graducacion alcoholica");
            System.out.println("g"+" ) "+"Identificador");
            System.out.println("h"+" ) "+"Nombre");
            System.out.println("i"+" ) "+"PVD");
            System.out.println("j"+" ) "+"PVP");
            System.out.println("k"+" ) "+"Tipo uva");
        
            campo=scan.nextLine();
            campo=campo.toLowerCase();
            
            /*En cada una de las opciones tendremos un comprador que ordena.*/
            switch(campo){
                case "a":
                    System.out.println("Vamos a ordenar por Bodega.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorBodega());
                    break;
                
                case "b":
                    System.out.println("Vamos a ordenar por nBotellasDisponibles.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorBotellasDisponibles());
                    break;
                    
                case "c":
                    System.out.println("Vamos a ordenar por Capacidad.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorCapacidad());
                    break;
                        
                case "d":
                    System.out.println("Vamos a ordenar por Denominacion De Origen.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorDenominacionDeOrigen());
                    break;
                            
                case "e":
                    System.out.println("Vamos a ordenar por Fecha Envasado.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorFechaEnvasado());
                    break;
                                
                case "f":
                    System.out.println("Vamos a ordenar por Graducion Alcoholica.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorGraduacionAlcoholica());
                    break;
                                    
                case "g":
                    System.out.println("Vamos a ordenar por Identificador.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorIdentificador());
                    break;
                                        
                case "h":
                    System.out.println("Vamos a ordenar por Nombre.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorNombre());
                    break;
                            
                case "i":
                    System.out.println("Vamos a ordenar por PVD.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorPVD());
                    break;
                                
                case "j":
                    System.out.println("Vamos a ordenar por PVP.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorPVP());
                    break;
                                    
                case "k":
                    System.out.println("Vamos a ordenar por TipoUva.");
                    Collections.sort(listadoAlmacen.get(nAlmacen).listadoProductos, new ComparadorPorTipoUva());
                    break;
                    
                default:
                    variableControl=false;
        
            }
        
        }while(variableControl==false);
        
        presentarUnAlmacen(nAlmacen);
        
        System.out.println();
        System.out.println();
        
    
    }

    /**
     * Presenta por pantalla un unico almacen.
     * @param x
     */
    public void presentarUnAlmacen(int x){
        System.out.println("Almacen: "+listadoAlmacen.get(x).getNombreAlmacen());
        for(int i=0;i<nombreAtributos.length;i++){
            System.out.printf("%25s ", nombreAtributos[i]);
        }
        System.out.println("");
            
        for(int i=0;i<288;i++){
             System.out.printf("-");
        }
        System.out.println("");
        
        for(int i=0;i<listadoAlmacen.get(x).listadoProductos.size();i++){
            System.out.printf("%25s%25.2f%25s  %25d%25s%29s%25s%26d%25s%27s%26s ", 
                                            listadoAlmacen.get(x).listadoProductos.get(i).getBodega(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getCapacidad(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getDenominacionDeOrigen(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getFechaEnvasado(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getGraduacionAlcoholica(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getIdentificador(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getNombreVino(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getBotellasDisponibles(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getPVD(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getPVP(),
                                            listadoAlmacen.get(x).listadoProductos.get(i).getTipoUva());
            System.out.println("");
        }
        System.out.println("");
        
    }

    /**
     * Ordena todos los almacenes por PVP.
     */
    public void ordenarTodoPorPVP(){
        
        for(int i=0;i<listadoAlmacen.size();i++){
            Collections.sort(listadoAlmacen.get(i).listadoProductos, new ComparadorPorPVP());
        }
        
        //LO PRESENTAMOS CON SOLO CON LOS ATRIBUTOS QUE NOS PIDEN
        
        presentarSegundaForma();
    }

    /**
     * Ordena todos los almacenes por precio.
     */
    public void ordenarPorPrecio(){
        for(int i=0;i<listadoAlmacen.size();i++){
            listadoAlmacen.get(i).setPrecioAlmacen();
        }
        Collections.sort(listadoAlmacen, new ComparadorPorPrecio());
        
        presentarTerceraForma();
    }
    
    /**
     * Presenta por pantalla los atributos que se piden al ordenar por precio.
     */
    void presentarSegundaForma(){
        
        int maxLongitud=0;
        
        for(int i=0;i<listadoAlmacen.size();i++){
            System.out.printf("%s\t\t\t\t\t\t\t", listadoAlmacen.get(i).getRuta());
        }
        System.out.println("");
        for(int i=0;i<95*listadoAlmacen.size();i++){
                System.out.printf("-");
            }
        System.out.println("");
        for(int i=0;i<listadoAlmacen.size();i++){
            System.out.printf("%30s %30s   %30s", nombreAtributos[9], nombreAtributos[6], nombreAtributos[10]);
        }
        System.out.println("");
        
        for(int i=0;i<95*listadoAlmacen.size();i++){
                System.out.printf("-");
        }
        System.out.println("");
        
        for(int i=0;i<listadoAlmacen.size();i++){
                if(listadoAlmacen.get(i).listadoProductos.size()>maxLongitud){
                    maxLongitud = listadoAlmacen.get(i).listadoProductos.size();
                }
            }
        
        
        for(int i=0;i<maxLongitud;i++){
                for(int j=0;j<listadoAlmacen.size();j++){
                    
                    if(i<listadoAlmacen.get(j).listadoProductos.size()){
                        System.out.printf("%30s %30s  %30s",
                                            listadoAlmacen.get(j).listadoProductos.get(i).getPVP(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getNombreVino(),
                                            listadoAlmacen.get(j).listadoProductos.get(i).getTipoUva());
                    }
                    else{
                            for(int c=0;c<3;c++){
                                System.out.printf("%30s ", "N/D");
                            }
                    }
                    
                    System.out.printf("||");
                    
                    
 
                }
                System.out.println("");
            }
        
        
    }
    
    /**
     * Presenta por pantalla los almacenes ya ordenados por precio.
     */
    void presentarTerceraForma(){
        System.out.printf("%15s\t\t%20s\n", "Precio", "Almacen");
        System.out.println("-------------------------------------------");
        
        for(int i=0;i<listadoAlmacen.size();i++){
                //System.out.println(listadoAlmacen.get(i).getPrecioAlmacen()+ "\t" +listadoAlmacen.get(i).getRuta());
                System.out.printf("%15.2f\t\t\t%30s\n", listadoAlmacen.get(i).getPrecioAlmacen(), listadoAlmacen.get(i).getRuta());
            }
    }
    
    /**
     * Presenta el menu para la eleccion de un almacen y además busca una denominacion de origen
     * que queramos y nos dice cuantas botellas hay.
     */
    void contarDenominacionDeOrigen(){
        
        String linea;
        String denominacion;
        Scanner scan = new Scanner(System.in);
        int nAlmacen=0;
        boolean excepcion;
        int contador=0;
        do{
            
            /*Presentamos el menu de eleccion*/
            for(int i=0;i<listadoAlmacen.size();i++){
            
                System.out.println(i+")"+"-"+listadoAlmacen.get(i).getRuta());
                
            }
            System.out.println("Introduce el almacen para conocer el numero de botellas de la misma D.O: ");
            linea=scan.nextLine();
        
                try{
                    
                    nAlmacen = Integer.parseInt(linea);
                    excepcion = false;
                
                }catch(NumberFormatException e){
                    
                    System.out.println("Caracter no valido.");
                    excepcion=true;
                    
                }
                
        }while(nAlmacen<0 || nAlmacen > listadoAlmacen.size() || excepcion);
        
        
            System.out.println("Teclee la D.O de la que quieres saber el numero de botellas:");
            denominacion = scan.nextLine();
            
            /*Busca todas las denominaciones que contienen la palabra que hemos metido y aumenta el contador*/
            for(int i = 0;i<listadoAlmacen.get(nAlmacen).listadoProductos.size();i++){
                if(listadoAlmacen.get(nAlmacen).listadoProductos.get(i).getDenominacionDeOrigen().equals(denominacion)){
                    
                    contador++;
                    
                }
            }
        
            System.out.println("De la denominacion de Origen: "+denominacion+" tenemos "+contador+" botellas.");
    }
    
    /**
     * Crea la carpeta para guardar los nuevos datos y se da a elegir al usuario entre
     * generar los datos aleatoriamente o guardar los que ha usado el programa(copia de seguridad).
     */
    void deseoDelUsuario(){
        //-----------------------------------------------
        //Variables
        Scanner scan = new Scanner(System.in);
        String opcion;
        int opcionInt = 0;
        boolean excepcion = true;
        String nombreCarpetaDestino;
        File folder;
         
        //----------------------------
        do{
            System.out.println("Finalizada la ejecucion del programa.");
            //Pedimos el nombre de la carpeta donde queremos guardar los datos
            System.out.printf("Introduce la carpeta donde deseas guardar los datos: ");
            nombreCarpetaDestino = scan.nextLine();
            //Nos aseguramos que la carpeta que vamos a crear no exista, y si no existe la creamos
            folder = new File(getRutaCarpetaDestino() + File.separator + nombreCarpetaDestino);

            if(folder.exists()){
                System.out.println("La carpeta ya existe. Lo siento.");
            }
        }while(folder.exists());
        folder.mkdir();
         
        setRutaCarpetaDestino(getRutaCarpetaDestino() + File.separator + nombreCarpetaDestino);

        //Pedimos al usuario que elija una de estas dos opciones
        do{
            System.out.println("Tiene que elegir una de estas dos opciones: ");
            System.out.println("   1) Crear *.csv con los datos cargados en la aplicacion.");
            System.out.println("   2) Crear *.csv con unos datos aleatorios.");
            System.out.println("---------------------------------------------------");
            try{
                System.out.printf("Indique que es lo que desea hacer: ");
                opcion = scan.nextLine();
                opcionInt = Integer.parseInt(opcion);
                excepcion = false;
            }catch(NumberFormatException e){
                excepcion = true;
                System.out.println("Error. Has introducido un carcter no valido.");
            }
         
        }while(opcionInt > 2 || opcionInt <= 0 ||excepcion);
        //En funcion de la opcion elegida. Haremos una cosa u otra
        switch(opcionInt){
            case 1:
                //Iniciamos la ruta de cada almacen a esa nueva carpeta
                for(int i=0;i<listadoAlmacen.size();i++){
                    listadoAlmacen.get(i).setRutaDestino(getRutaCarpetaDestino()+File.separator + listadoAlmacen.get(i).getNombreAlmacen());
                }
                
                System.out.println("Creando archivos con los datos de la aplicaion.");
                crearArchivosConDatosDeLaApp();
                for(int i=0;i<listadoAlmacen.size();i++){
                    rellenarUnArchivo(listadoAlmacen.get(i).getRutaDestino(), listadoAlmacen.get(i));
                }
                break;
            case 2:
                crearArchivosConDatosAleatorios();
                break;
             
        }
    }
    
    /**
     * Crea los archivos con los usados en el programa.
     */
    void crearArchivosConDatosDeLaApp(){
        File archivo;
        for(int i=0;i<listadoAlmacen.size();i++){
            archivo = new File(listadoAlmacen.get(i).getRutaDestino());
            try{
                archivo.createNewFile();
            }catch(IOException e){
                    System.out.println("Fallo creando un archivo de nombre: "+ listadoAlmacen.get(i).getNombreAlmacen());
            }
        }
    }
    
    /**
     * Rellena un archivo de los creados con los datos del programa.
     * @param rutaDestino
     * @param Temp 
     */
    void rellenarUnArchivo(String rutaDestino, Almacen Temp){
        //Creamos un PrintWriter donde guardar los datos
        try{
            PrintWriter pw = new PrintWriter( new File((rutaDestino)));
            for(int i=0;i<Temp.listadoProductos.size();i++){
                
                pw.println(Temp.listadoProductos.get(i).getBodega()+delimitador +
                           Temp.listadoProductos.get(i).getCapacidad()+delimitador +
                           Temp.listadoProductos.get(i).getDenominacionDeOrigen()+delimitador +
                           Temp.listadoProductos.get(i).getFechaEnvasado()+delimitador +
                           Temp.listadoProductos.get(i).getGraduacionAlcoholica()+delimitador +
                           Temp.listadoProductos.get(i).getIdentificador()+delimitador +
                           Temp.listadoProductos.get(i).getNombreVino()+delimitador +
                           Temp.listadoProductos.get(i).getBotellasDisponibles()+delimitador +
                           Temp.listadoProductos.get(i).getPVD()+delimitador +
                           Temp.listadoProductos.get(i).getPVP()+delimitador +
                           Temp.listadoProductos.get(i).getTipoUva()+delimitador
                        );
            }
            pw.close();
        }catch(FileNotFoundException e){
            System.out.println("Fallo abriendo el archivo de ruta: "+ rutaDestino);
        }
        
    }
    
    /**
     * Genera archivos con datos aleatorios.
     */
    void crearArchivosConDatosAleatorios(){
        
        Scanner scan = new Scanner(System.in);
        String temp;
        String linea;
        boolean excepcion = true;
        //Pedimos el numero de almacenes a crear, que sea entre 0 y 10
        do{
            try{
                System.out.print("Escriba el numero de almacenes: ");
                temp = scan.nextLine();
                numeroAlmacenes = Integer.parseInt(temp);
                excepcion = false;
                System.out.println();
            }catch(NumberFormatException e){
                excepcion = true;
                System.out.println("Error. Has introducido un carcter no valido.");
            }
        
        }while(excepcion || (numeroAlmacenes <= 0) || (numeroAlmacenes > 10));
        //Creamos un vector con los nombres de los almacenes
        nombreAlmacen = new String[numeroAlmacenes];
        //Pedimos los nombres de los almacenes
        for(int i=0; i<numeroAlmacenes; i++){
            
            System.out.print("Escribe el nombre del almacen numero "+(i+1)+": ");
            temp = scan.nextLine();
            nombreAlmacen[i] = temp;
            System.out.println();
            
        }

        /*Creamos los archivos*/
        for(int i=0; i<nombreAlmacen.length; i++){
            
            File archivo = new File(getRutaCarpetaDestino() + File.separator + nombreAlmacen[i] + ".csv");
            
            try {
                archivo.createNewFile();
            } catch (IOException ex){
                System.out.println("Fallo creando los archivos correspondientes a los almacenes");
            }
            
        }
        //Llamamos al constructor para que cree datos aleatorios
        DatosAleatorios dt = new DatosAleatorios(numeroAlmacenes);
        //Escribimos en los archivos los campos correspondientes con el delimitador ';'
        for(int i=0;i<numeroAlmacenes;i++){
            /*Escribimos los datos en el fichero*/
            try{
                
                PrintWriter pw = new PrintWriter(new File(getRutaCarpetaDestino()+File.separator+ nombreAlmacen[i]+".csv"));
                
                for(int j=0;j<dt.getNumeroDeProductosPorAlmacen()[i]; j++){
                    
                    linea="";
                
                    linea += dt.devolverCampo(dt.nombreBodega)+ delimitador; 
                    linea += dt.devolverCampo(dt.capacidadBotella)+delimitador;
                    linea += dt.devolverCampo(dt.denominacionDeOrigen)+delimitador;
                    linea += dt.devolverCampo(dt.fechaDeEnvasado)+delimitador;
                    linea += dt.devolverCampo(dt.graduacionAlcoholica)+delimitador;
                    linea += dt.devolverCampo(dt.identificador)+delimitador;
                    linea += dt.devolverCampo(dt.nombreVino)+delimitador;
                    linea += dt.devolverCampo(dt.botellasDisponibles)+delimitador;
                    linea += dt.devolverCampo(dt.PVD)+delimitador;
                    linea += dt.devolverCampo(dt.PVP)+delimitador;
                    linea += dt.devolverCampo(dt.tipoUva);
                
                    pw.println(linea);    
                }
                //Cerramos el archivo
                pw.close();
                
            }catch(FileNotFoundException e){
                System.out.println("Error intentado escribir en alguno de los archivos '*.csv'");
            }
        }
        
        
    }
    /**
     * METODOS SETTER AND GETTER.
     */
    
    /**
     *
     * @return rutaCarpeta
     */
    public String getRutaCarpeta() {
        return rutaCarpeta;
    }

    /**
     *
     * @param rutaCarpeta
     */
    public void setRutaCarpeta(String rutaCarpeta) {
        this.rutaCarpeta = rutaCarpeta;
    }
    
    
    public String getRutaCarpetaDestino() {
        return rutaCarpetaDestino;
    }

    public void setRutaCarpetaDestino(String rutaCarpetaDestino) {
        this.rutaCarpetaDestino = rutaCarpetaDestino;
    }

}

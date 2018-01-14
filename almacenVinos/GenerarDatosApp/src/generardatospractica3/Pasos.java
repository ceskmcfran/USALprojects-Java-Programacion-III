/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generardatospractica3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

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
     * Atributos.
     */
    private String Ruta; //String que contendrá la ruta.
    private String nombreCarpeta; //String que contendrá el nombre de la carpeta.
    private int numeroAlmacenes; //Numero de almacenes.
    private int[] numeroDeproductosPorAlmacen; //Vector que contendrá el numero de productos por almacen.
    private String caracterDelimitador; //Caracter que separará los datos de los ficheros.
    
    /**
     * Vector con el nombre de los almacenes
     */
    private final String nombreAlmacenes[] = {"Salamanca", "Jaen", "Gerona",
                                              "Segoivia", "Madrid", "Barcelona",
                                              "Oviedo", "Mallorca"};
    
    /**
     * Vector con el nombre de las bodegas
     */
    private final String nombreBodega[] ={"Ysios", "Baigorri", "Darien",
                                          "Portia", "Protos"};
    
    /**
     * Vector con las capacidades de las botellas
     */
    private final String capacidadBotella[] = {"0.5", "0.75", "1","1.25","1.5"};
    
    /**
     * Vector con el nombre de las denominaciones
     */
    private final String denominacionDeOrigen[] = {"Toro", "Rioja", "Ribeiro", 
                                                   "Bierzo", "Arlanza"};
    
    /**
     * Vector con las fechas de envasado
     */
    private final String fechaDeEnvasado[] = {"2015", "2013", "1996", "2000", 
                                              "1999"};
    
    /**
     * Vector con las graduaciones alcoholicas
     */
    private final String graduacionAlcoholica[] = {"13.5", "12", "15", "10", "8.3"};
    
    /**
     * Vector con el nombre de los identificador
     */
    private final String identificador[] = {"Blanco", "Rosado", "Violeta", 
                                            "Dorado", "Ocre"};
    
    /**
     * Vector con el nombre de los vinos
     */
    private final String nombreVino[] = {"Rocca delle Macie", "Lechthaler Priot", 
                                         "Avignonesi", "Antinori", "Pio Cesare"};
    
    /**
     * Vector con la cantidad de botellas disponibles
     */
    private final String botellasDisponibles[] = {"25", "100", "45", "34", "76"};
    
    /**
     * Vector con los PVD
     */
    private final String PVD[] = {"15", "19", "18", "11", "7"};
    
    /**
     * Vector con los PVP
     */
    private final String PVP[] = {"20", "22", "24", "27", "26"};
    
    /**
     * Vector con los tipos de uva
     */
    private final String tipoUva[] = {"Albarello", "Alarije", "Albillo", "Albariño", "Albarello"};
    
    
    /**
     * Metodo con el que vamos a crear una ruta a nuestro escritorio.
     */
    public void crearRutaAlEscritorio(){
        
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
        setRuta(System.getProperty("user.home")+ File.separator + nombreEscritorio + File.separator);
    }
    
    /**
     * Pide al usuario que introduzca por teclado la carpeta donde desea crear todos los datos.
     */
    public void seleccionarCarpeta(){
        
        String temp;
        Scanner scan = new Scanner (System.in); 
        
        /*Pedimos introducir el nombre de la carpeta donde se crearán los datos*/
        System.out.printf("Selecciona la carpeta donde deseas crear los datos: ");
        temp = scan.nextLine();
        /*Establecemos la ruta con el nombre de esa carpeta*/
        File carpeta = new File(getRuta()+File.separator+temp);
        /*Si la carpeta no existe, la creamos*/
        if(!carpeta.exists()){
            carpeta.mkdir();
        }
        /*Guardamos el nombre de la carpeta para su posterior utilización*/
        setNombreCarpeta(temp);
    } 
    
    /**
     * Pide el numero de almacenes a crear y crea un numero de productos por cada uno de ellos.
     */
    public void pedirInformacion(){
        
        Scanner scan = new Scanner(System.in);
        Random rand = new Random(System.nanoTime());
        
        int numAlmacenes=0;
        boolean excepcion;
        
        /*Mientras el numero de almacenes que tecleamos sea negativo o se produzca una
        excepcion o el numero de almacenes que tecleamos sea mayor que la longitud del vector
        de nombres de almacenes...*/
        do{
            try{
            /*Pedimos el numero de almacenes que queremos crear.*/
            System.out.printf("Teclea el numero de almacenes que deseas crear: ");
            /*Convertimos a entero el String que leemos*/
            numAlmacenes = Integer.parseInt(scan.nextLine());
            excepcion = false;
            }catch(NumberFormatException e ){
                excepcion = true;
            }
        }while((numAlmacenes<=0)||(numAlmacenes > nombreAlmacenes.length)||(excepcion));
        
        /*Guardamos el numero de almacenes en su correspondiente atributo*/
        setNumeroAlmacenes(numAlmacenes);
        //Damos un numero de productos distintos a cada almacen
        numeroDeproductosPorAlmacen = new int[getNumeroAlmacenes()];
        
        for(int i=0;i<getNumeroAlmacenes();i++){
            /*Sumamos 1 al numero de productos para que ningun almacen esté vacio*/
            numeroDeproductosPorAlmacen[i]= rand.nextInt(6)+1;
        
        }   
        /*Pedimos teclear el caracter que separará los datos en los ficheros y lo
        guardamos.*/
        System.out.print("Teclea el caracter delimitador que deseas usar: ");
        setCaracterDelimitador(scan.nextLine());
    }
    
    /**
     * Crea los archivos "*.csv".
     */
    public void crearArchivos(){
        
        System.out.println();
        /*Recorremos el vector de los almacenes y creamos el archivo*/ 
        for(int i=0;i<getNumeroAlmacenes();i++){
            File archivo = new File(getRuta()+File.separator+nombreAlmacenes[i]+".csv");
            
            try {
                archivo.createNewFile();
            } catch (IOException ex){
                System.out.println("Fallo creando los archivos correspondientes a los almacenes");
            }
              
        }
    }
    
    /**
     * Modificamos la ruta para poder acceder a la carpeta donde vamos a crear los datos. 
     */
    public void crearRutaACarpeta(){
        
        setRuta(getRuta()+getNombreCarpeta());
        
    }
    
    /**
     * Escribe los datos en los ficheros "*.csv".
     */
    public void iniciarArchivos(){
        
        String linea;
        
        for(int i=0;i<getNumeroAlmacenes();i++){
            /*Escribimos los datos en el fichero*/
            try{
                
                PrintWriter pw = new PrintWriter( new File(getRuta()+File.separator+ nombreAlmacenes[i]+".csv"));
                
                for(int j=0;j<numeroDeproductosPorAlmacen[i];j++){
                    
                    linea="";
                
                    linea += devolverCampo(nombreBodega)+ getCaracterDelimitador(); 
                    linea += devolverCampo(capacidadBotella)+getCaracterDelimitador();
                    linea += devolverCampo(denominacionDeOrigen)+getCaracterDelimitador();
                    linea += devolverCampo(fechaDeEnvasado)+getCaracterDelimitador();
                    linea += devolverCampo(graduacionAlcoholica)+getCaracterDelimitador();
                    linea += devolverCampo(identificador)+getCaracterDelimitador();
                    linea += devolverCampo(nombreVino)+getCaracterDelimitador();
                    linea += devolverCampo(botellasDisponibles)+getCaracterDelimitador();
                    linea += devolverCampo(PVD)+getCaracterDelimitador();
                    linea += devolverCampo(PVP)+getCaracterDelimitador();
                    linea += devolverCampo(tipoUva);
                
                    pw.println(linea);    
                }
                
                pw.close();
                
            }catch(FileNotFoundException e){
                System.out.println("Error intentado escribir en alguno de los archivos '*.csv'");
            }
        }
        
    }
    
    /**
     * Genera el dato a guardar en el archivo aleatoriamente escogiendo un valor
     * del vector que se le pasa cada vez.
     * @param vector
     * @return vector[numero aleatorio basado en la longitud de ese vector]
     */
    public String devolverCampo(String[] vector){
        
        Random rand = new Random(System.nanoTime());
        
        return(vector[rand.nextInt(vector.length)]);
    }

    /**
     * METODOS SETTER AND GETTER.
     */
    
    /**
     * 
     * @return Ruta
     */
    public String getRuta() {
        return Ruta;
    }

    /**
     *
     * @param Ruta
     */
    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    /**
     *
     * @return nombreCarpeta
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
     * @return numeroAlamacenes
     */
    public int getNumeroAlmacenes() {
        return numeroAlmacenes;
    }

    /**
     *
     * @param numeroAlmacenes
     */
    public void setNumeroAlmacenes(int numeroAlmacenes) {
        this.numeroAlmacenes = numeroAlmacenes;
    }

    /**
     *
     * @return numeroDeproductosPorAlmacen
     */
    public int[] getNumeroDeproductosPorAlmacen() {
        return numeroDeproductosPorAlmacen;
    }

    /**
     *
     * @param numeroDeproductosPorAlmacen
     */
    public void setNumeroDeproductosPorAlmacen(int[] numeroDeproductosPorAlmacen) {
        this.numeroDeproductosPorAlmacen = numeroDeproductosPorAlmacen;
    }

    /**
     *
     * @return caracterDelimitador
     */
    public String getCaracterDelimitador() {
        return caracterDelimitador;
    }

    /**
     *
     * @param caracterDelimitador
     */
    public void setCaracterDelimitador(String caracterDelimitador) {
        this.caracterDelimitador = caracterDelimitador;
    }

}
    

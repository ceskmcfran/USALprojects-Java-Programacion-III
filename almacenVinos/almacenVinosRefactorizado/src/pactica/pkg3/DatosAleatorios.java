/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pactica.pkg3;

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
public class DatosAleatorios {

    private int numeroAlmacenes; //Numero de almacenes.
    private int[] numeroDeProductosPorAlmacen; //Vector que contendrá el numero de productos por almacen.
    
    /**
     * Vector con el nombre de las bodegas
     */
    final String nombreBodega[] ={"Ysios", "Baigorri", "Darien",
                                          "Portia", "Protos"};
    
    /**
     * Vector con las capacidades de las botellas
     */
    final String capacidadBotella[] = {"0.5", "0.75", "1","1.25","1.5"};
    
    /**
     * Vector con el nombre de las denominaciones
     */
    final String denominacionDeOrigen[] = {"Toro", "Rioja", "Ribeiro", 
                                                   "Bierzo", "Arlanza"};
    
    /**
     * Vector con las fechas de envasado
     */
    final String fechaDeEnvasado[] = {"2015", "2013", "1996", "2000", 
                                              "1999"};
    
    /**
     * Vector con las graduaciones alcoholicas
     */
    final String graduacionAlcoholica[] = {"13.5", "12", "15", "10", "8.3"};
    
    /**
     * Vector con el nombre de los identificador
     */
    final String identificador[] = {"Blanco", "Rosado", "Violeta", 
                                            "Dorado", "Ocre"};
    
    /**
     * Vector con el nombre de los vinos
     */
    final String nombreVino[] = {"Rocca delle Macie", "Lechthaler Priot", 
                                         "Avignonesi", "Antinori", "Pio Cesare"};
    
    /**
     * Vector con la cantidad de botellas disponibles
     */
    final String botellasDisponibles[] = {"25", "100", "45", "34", "76"};
    
    /**
     * Vector con los PVD
     */
    final String PVD[] = {"15", "19", "18", "11", "7"};
    
    /**
     * Vector con los PVP
     */
    final String PVP[] = {"20", "22", "24", "27", "26"};
    
    /**
     * Vector con los tipos de uva
     */
    final String tipoUva[] = {"Albarello", "Alarije", "Albillo", "Albariño", "Albarello"};

    /**
     * Constructor.
     * @param numeroAlmacenes 
     */
    public DatosAleatorios(int numeroAlmacenes){
        
        Random rand = new Random(System.nanoTime());
        Scanner scan = new Scanner(System.in);
        
        this.numeroAlmacenes = numeroAlmacenes;
        
        numeroDeProductosPorAlmacen = new int[numeroAlmacenes];
        
        for(int i = 0; i<numeroAlmacenes; i++){
            
            numeroDeProductosPorAlmacen[i] = rand.nextInt(6) + 1;
            
        }
        
    }
    
    /**
     * Genera el dato a guardar en el archivo aleatoriamente escogiendo un valor
     * del vector que se le pasa cada vez.
     * @param vector
     * @return vector[numero aleatorio basado en la longitud de ese vector
     */
    public String devolverCampo(String[] vector){
        
        Random rand = new Random(System.nanoTime());
        
        return(vector[rand.nextInt(vector.length)]);
    }

    /*METODOS SETTER AND GETTER*/

    public int getNumeroAlmacenes() {
        return numeroAlmacenes;
    }

    public void setNumeroAlmacenes(int numeroAlmacenes) {
        this.numeroAlmacenes = numeroAlmacenes;
    }

    public int[] getNumeroDeProductosPorAlmacen() {
        return numeroDeProductosPorAlmacen;
    }

    public void setNumeroDeProductosPorAlmacen(int[] numeroDeProductosPorAlmacen) {
        this.numeroDeProductosPorAlmacen = numeroDeProductosPorAlmacen;
    }

 
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioquiniela;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Equipo Echo (Grupo PB1)
 * Juan Bautista Arellano Bruno
 * Francisco Blázquez Matías
 * David Flores Barbero
 * Lucia Goyenechea S. de Castro
 * Victor Castilla Garrido
 * 
 */
public class EjercicioQuiniela {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException{
        // TODO code application logic here
        
    //Creamo el objeto de la clase quiniela
    Quiniela Q = new Quiniela();
    Scanner Scan = new Scanner(System.in);
    int Opcion;
    
    //Es un menu normal y corriente con dos opciones, jugar, o salir
    do{
    
        System.out.println("*************************************");
        System.out.println("*  LOTERIAS Y APUESTAS DEL ESTADO   *");
        System.out.println("*  ------------------------------   *");
        System.out.println("*            LA QUINIELA            *");
        System.out.println("*************************************");
        
        System.out.println(" 1) Jugar Quiniela");
        System.out.println(" 2) Salir del programa");
        System.out.println("--------------------------------------");
        System.out.print("Teclee una opcion: ");
        Opcion = Scan.nextInt();
    
        switch(Opcion){
            //si es 1, jugamos a la quiniela
            //
            case 1:
                //Dinero con el que jugamos
                Q.Dinero(); //Archivo "Quiniela.java" linea 188
                //Localizamos el archivo de los partidos
                Q.PedirArchivoDePartidos(); //Archivo "Quiniela.java" linea 40
                //Localizamos el archivo de los resultados
                Q.PedirArchivoDeResultados();//Archivo "Quiniela.java" linea 69
                //Leemos los dos arhivos localizados anteriormente
                Q.LeerPartidos();//Archivo "Quiniela.java" linea 84
                Q.LeerResultados();//Archivo "Quiniela.java" linea 108
                //Imprimimos todos los partidos de dicha jornada
                Q.VisualizarPartidos();//Archivo "Quiniela.java" linea 406
                //Apostamos
                Q.Apuesta();//Archivo "Quiniela.java" linea 275
                //Comparamos e imprimimos nuestras apuestas con los resultados de verdad
                Q.MostrarQuiniela();//Archivo "Quiniela.java" linea 136
                //Vemos si hemos ganado dinero o no
                Q.Premios();//Archivo "Quiniela.java" linea 375
                
            break;
            
            case 2:
                //Si es 2, despedimos al jugador y termina el programa
                System.out.println("VUELVA PRONTO A JUGAR A LOTERIAS Y APUESTAS DEL ESTADO");
            break;
                
            default:
                //si el usuario teclea una opcion incorrecta, se lo comunicamos, y volvemos al principio del menu
                System.out.println("!!OPCION INCORRECTA¡¡ VOLVIENDO AL MENU");
        }
        
        try{
            System.in.read();
            }catch(Exception e){};
    }while(Opcion!=2);
    }
    
}

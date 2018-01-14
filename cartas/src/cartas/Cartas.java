/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Francisco Blazquez Matias
 */
public class Cartas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*-------------------------------------List<E>-------------------------------------*/
        /*ArrayList<E> ---> Mejor rendimiento si no hay inserciones y eliminaciones en posicion intermedia.
        LinkedList<E> ---> Mejor rendimiento cuando hay inserciones y eliminaciones en posicion intermedia.
        */
        
        String[] palo = new String[]{
            "oros", "copas", "espadas", "bastos"
        };
        String[] carta = new String[]{
            "as","2","3","4","5","6","7","8",
            "9","10","sota","caballo","rey"
        };
        
        //Añadimos las cartas a la baraja
        List<String> baraja = new ArrayList<String>();
        for(int i=0; i<palo.length; i++){
            for(int j=0;j<carta.length;j++){
                baraja.add(carta[j]+ " de " +palo[i]);
            }
        }
        
        //Barajamos
        Collections.shuffle(baraja);
        
        System.out.println(baraja);
        
    }
    
    
    
}

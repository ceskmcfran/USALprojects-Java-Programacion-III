/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadefrecuencias;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Francisco Blazquez Matias
 */
public class ListaDeFrecuencias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        /*--------------------------------Map<K, V>--------------------------------------*/
        /*HashMap<K, V>
        TreeMap<K, V>
        LinkedHashMap<K, V>
        */
        
        Map<String, Integer> m = new HashMap<String, Integer>();
        
        File f = new File("texto.txt");
        List<String> lineas = new ArrayList<String>();
        
        Scanner sf = new Scanner(f);
        while(sf.hasNext()){
            String t = sf.next();
            lineas.add(t);
        }
        sf.close();
        
        for(Iterator<String> it = lineas.iterator();it.hasNext();){
            Integer frecuencia = m.get(it.next());
            m.put(it.next(), (frecuencia == null) ? 1 : frecuencia + 1);
        }
        System.out.println("Hay "+m.size()+ " palabras distintas: ");
        System.out.println(m);
        
        List<Palabra> listaPalabras = new ArrayList<Palabra>();
        for(Map.Entry<String, Integer> entrada: m.entrySet()){
            
            String clave = entrada.getKey();
            int valor = entrada.getValue();
            Palabra unaP = new Palabra(clave, valor);
            listaPalabras.add(unaP);
            
        }
        
        Collections.sort(listaPalabras, new ComparadorPorFrecuencia());
        
        for (int i = 0; i < listaPalabras.size(); i++) {
            System.out.println("Palabra: "+listaPalabras.get(i).getClave()+" -----> Repetida "+listaPalabras.get(i).getValor()+" veces.");
        }
        
    }
    
}

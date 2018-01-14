/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadefrecuencias;

import java.util.Comparator;

/**
 *
 * @author Francisco Blazquez Matias
 */
public class ComparadorPorFrecuencia implements Comparator<Palabra>{

    @Override
    public int compare(Palabra a, Palabra b) {
        return(Integer.compare(a.getValor(), b.getValor()));
    }
    
}

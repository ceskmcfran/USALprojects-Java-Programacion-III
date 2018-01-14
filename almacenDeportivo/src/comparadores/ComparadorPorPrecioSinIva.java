/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparadores;

import java.util.Comparator;
import moduloAlmacen.Producto;

/**
 *
 * @author Francisco Blazquez Matias
 */
public class ComparadorPorPrecioSinIva implements Comparator<Producto>{
    /**
     * 
     * @param a
     * @param b
     * @return Comparacion entre los productos
     */
    @Override
    public int compare(Producto a, Producto b){
        return(a.getPrecioSinIva().compareTo(b.getPrecioSinIva()));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparadores;

import java.util.Comparator;
import pactica.pkg3.Almacen;

/**
 *
 * @author Equipo Echo (Grupo PB1).
 * Francisco Blázquez Matías.
 * Juan Bautista Arellano Bruno.
 * David Flores Barbero.
 * Victor Castilla Garrido.
 */
public class ComparadorPorPrecio implements Comparator <Almacen>{
 
      @Override
      
      public int compare(Almacen a, Almacen b){
          return (Double.compare(a.getPrecioAlmacen(), b.getPrecioAlmacen()));
      }
}

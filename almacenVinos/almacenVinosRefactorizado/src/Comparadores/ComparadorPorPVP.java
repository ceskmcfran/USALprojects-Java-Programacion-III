/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparadores;

import java.util.Comparator;
import pactica.pkg3.Producto;

/**
 *
 * @author Equipo Echo (Grupo PB1).
 * Francisco Blázquez Matías.
 * Juan Bautista Arellano Bruno.
 * David Flores Barbero.
 * Victor Castilla Garrido.
 */
public class ComparadorPorPVP implements Comparator<Producto>{
    @Override
    public int compare(Producto a, Producto b){
        //return (a.getPVP().compareTo(b.getPVP()));
        return (Float.compare(Float.parseFloat(a.getPVP()), Float.parseFloat(b.getPVP())));
    }
}

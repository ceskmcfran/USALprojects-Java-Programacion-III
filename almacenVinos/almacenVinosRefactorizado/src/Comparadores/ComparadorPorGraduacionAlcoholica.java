package Comparadores;


import java.util.Comparator;
import pactica.pkg3.Producto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Equipo Echo (Grupo PB1).
 * Francisco Blázquez Matías.
 * Juan Bautista Arellano Bruno.
 * David Flores Barbero.
 * Victor Castilla Garrido.
 */
public class ComparadorPorGraduacionAlcoholica implements Comparator<Producto>{

    @Override
    public int compare(Producto a, Producto b) {
        //return(a.getGraduacionAlcoholica().compareTo(b.getGraduacionAlcoholica()));
        return (Float.compare(Float.parseFloat(a.getGraduacionAlcoholica()), Float.parseFloat(b.getGraduacionAlcoholica())));
    }
    
}

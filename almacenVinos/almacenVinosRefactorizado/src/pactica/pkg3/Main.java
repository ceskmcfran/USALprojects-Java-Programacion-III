/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pactica.pkg3;

/**
 *
 * @author Equipo Echo (Grupo PB1).
 * Francisco Blázquez Matías.
 * Juan Bautista Arellano Bruno.
 * David Flores Barbero.
 * Victor Castilla Garrido.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pasos p = new Pasos();
        p.pedirCarpeta();
        p.iniciarRutaALosArchivos();
        p.leerDatos();
        p.presentarTodosAlmacenes();
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("           MENU DE ORDENACION");
        System.out.println("---------------------------------------");
        p.ordenarAlmacen();
        System.out.println("---------------------------------------");
        System.out.println("           ORDENACION POR PVP");
        System.out.println("---------------------------------------");
        p.ordenarTodoPorPVP();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("          ORDENACION POR PRECIO");
        System.out.println("---------------------------------------");
        p.ordenarPorPrecio();
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("          MENU DE BUSQUEDA D.O");
        System.out.println("---------------------------------------");
        p.contarDenominacionDeOrigen();
        System.out.println("---------------------------------------");
        System.out.println("     MENU CREACION DE ARCHIVOS .CSV");
        System.out.println("---------------------------------------");
        p.deseoDelUsuario();
    }
    
}

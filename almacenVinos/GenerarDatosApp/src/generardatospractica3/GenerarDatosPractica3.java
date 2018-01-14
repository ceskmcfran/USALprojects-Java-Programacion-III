/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generardatospractica3;

/**
 *
 * @author Equipo Echo (Grupo PB1).
 * Francisco Blázquez Matías.
 * Juan Bautista Arellano Bruno.
 * David Flores Barbero.
 * Victor Castilla Garrido.
 */
public class GenerarDatosPractica3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pasos p = new Pasos();
        p.crearRutaAlEscritorio();
        p.seleccionarCarpeta();
        p.crearRutaACarpeta();
        p.pedirInformacion();
        p.crearArchivos();
        //Llegados a este punto solo quedar iniciar los archivos con distintos valores semialeatorios
        p.iniciarArchivos();
        
        
        
    }
    
}

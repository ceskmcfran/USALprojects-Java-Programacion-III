/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import calculo.BalanceAnualRefactorizado;
import java.io.IOException;

/**
 *
 * @author Patrulla Echo: David Flores, Juan Arellano, Fran Blázquez, Victor Castilla.
 */
public class BalanceAnual {
    /** 
     * 2param args the command line arguments
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[]args) throws IOException{
            //La carpeta general esta en el escritorio datos
            String nombreCarpeta="datos_economicos";
            BalanceAnualRefactorizado b = new BalanceAnualRefactorizado(nombreCarpeta);
            b.importarDatosDeSucursales();
            b.ingresosTotalesPorCiudad();
            b.gastosTotalesPorCiudad();
            b.ciudadMayoresGastos();
            b.ciudadMayoresIngresos();
            b.balanceAnual();
            b.imprimirTablaGeneral();
            b.imprimirResultados();
    }
}
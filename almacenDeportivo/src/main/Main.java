/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import moduloAlmacen.ModuloAlmacen;
import moduloClientes.ModuloCliente;
import moduloPedidos.ModuloPedido;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        Rutas R = new Rutas();
        ModuloCliente MC = new ModuloCliente();
        ModuloPedido MP = new ModuloPedido();
        ModuloAlmacen MA = new ModuloAlmacen();
        Scanner scan = new Scanner(System.in);
        boolean excepcion = true;
        String opcion;
        int opcionInt = 0;

        R.crearCarpetaPrincipal();
        
        do{

            System.out.println("==============================================================");
            System.out.println("\t\t\tMENU PRINCIPAL");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("1) Modulo clientes.");
            System.out.println("2) Modulo pedidos.");
            System.out.println("3) Modulo almacen.");
            System.out.println();
            System.out.println("4) Salir.");
            System.out.println();
            System.out.println();

            do{

                try{

                    System.out.print("Introduzca una opcion: ");
                    opcion = scan.nextLine();
                    opcionInt = Integer.parseInt(opcion);
                    excepcion = false;

                }
                catch(NumberFormatException e){

                    excepcion = true;
                    System.out.println("Has introducido un caracter no valido.");

                }

            }while(excepcion);

            switch(opcionInt){

                case 1:
                    MC.menuCliente();
                    break;

                case 2:
                    MP.menuPedidos();
                    break;

                case 3:
                    MA.menuAlmacen();
                    break;
                    
                case 4:
                    System.out.println();
                    System.out.println("Saliendo..");
                    System.out.println();
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("Has introducido una opcion incorrecta, vuelve a intentarlo.");
                    break;

            }


        }while(opcionInt != 4);


    }

}

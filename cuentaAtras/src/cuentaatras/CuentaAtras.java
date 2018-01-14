/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentaatras;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Francisco Blazquez Matias
 */
public class CuentaAtras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int time;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Escribe un numero para hacer la cuenta atras: ");
        time = scan.nextInt();
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = time; i >= 0; i--) {
            queue.add(i);
        }
        while(!queue.isEmpty()){
            System.out.println(queue.remove());
            Thread.sleep(1000);
        }
        
    }
    
}

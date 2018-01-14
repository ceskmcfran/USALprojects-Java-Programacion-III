/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPedidos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import moduloAlmacen.Producto;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class Pedido {
    
    private String codPed = "";
    private String fecha;
    private String mailCli;
    private ArrayList<Producto> productoPedido;
    
    /**
     * Constructor de los pedidos, genera un codigo de pedido aleatorio, establece la fecha del pedido
     * y enlaza el mail del cliente.
     * @param mailCli 
     */
    public Pedido(String mailCli){
        
        this.productoPedido = new ArrayList<>();
        
        Random rand = new Random(System.nanoTime());
        
        /*Enlazamos el mail del cliente con el pedido*/
        this.mailCli = mailCli;
        
        /*Creamos un codigo de pedido aleatorio para cada pedido*/
        for(int i=0; i<7; i++){
            
            codPed = codPed + Integer.toString(rand.nextInt(9));
            
        }
        
        /*Establecemos la fecha del dia que se realiza para cada pedido*/
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        fecha = formateador.format(ahora);

    }
    
    /*METODOS SETTER AND GETTER*/

    /**
     * 
     * @return ArrayList de los productos del pedido
     */
    public ArrayList<Producto> getProductoPedido() {
        return productoPedido;
    }

    /**
     * 
     * @param productoPedido 
     */
    public void setProductoPedido(ArrayList<Producto> productoPedido) {
        this.productoPedido = productoPedido;
    }

    /**
     * 
     * @return Codigo del pedido
     */
    public String getCodPed() {
        return codPed;
    }

    /**
     * 
     * @param codPed 
     */
    public void setCodPed(String codPed) {
        this.codPed = codPed;
    }

    /**
     * 
     * @return Fecha del pedido
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha 
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return Correo electronico del cliente que ha hecho el pedido
     */
    public String getMailCli() {
        return mailCli;
    }

    /**
     * 
     * @param mailCli 
     */
    public void setMailCli(String mailCli) {
        this.mailCli = mailCli;
    }
    
}

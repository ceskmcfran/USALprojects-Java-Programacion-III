/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPedidos;

import java.util.Random;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class Factura {
    
    private String codFact = "";
    private String mailCli;
    private String codPed;
    private String totalBase;
    private String totalIva;
    private String total;
    
    /**
     * Constructor de las facturas.
     * @param codPed
     * @param mailCli 
     */
    public Factura(String codPed, String mailCli){
        
        Random rand = new Random(System.nanoTime());
        
        this.mailCli = mailCli;
        this.codPed = codPed;
        
        for(int i=0; i<7; i++){
            
            codFact = codFact + Integer.toString(rand.nextInt(9));
            
        }
        
        
    }

    /*METODOS SETTER AND GETTER*/
    
    /**
     * 
     * @return Codigo de la factura
     */
    public String getCodFact() {
        return codFact;
    }
    
    /**
     * 
     * @param codFact 
     */
    public void setCodFact(String codFact) {
        this.codFact = codFact;
    }

    /**
     * 
     * @return Correo del cliente
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
     * @return Coste total base (sin iva)
     */
    public String getTotalBase() {
        return totalBase;
    }

    /**
     * 
     * @param totalBase 
     */
    public void setTotalBase(String totalBase) {
        this.totalBase = totalBase;
    }

    /**
     * 
     * @return Coste total del iva del pedido
     */
    public String getTotalIva() {
        return totalIva;
    }

    /**
     * 
     * @param totalIva 
     */
    public void setTotalIva(String totalIva) {
        this.totalIva = totalIva;
    }

    /**
     * 
     * @return Coste total del pedido
     */
    public String getTotal() {
        return total;
    }

    /**
     * 
     * @param total 
     */
    public void setTotal(String total) {
        this.total = total;
    }
    
    
}

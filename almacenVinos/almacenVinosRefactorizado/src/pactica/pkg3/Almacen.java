/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pactica.pkg3;

import java.util.ArrayList;

/**
 *
 * @author Equipo Echo (Grupo PB1).
 * Francisco Blázquez Matías.
 * Juan Bautista Arellano Bruno.
 * David Flores Barbero.
 * Victor Castilla Garrido.
 */
public class Almacen {

    /**
     * Atributos.
     */
    private String ruta; //Direccion del archivo del almacen
    private String rutaDestino;

    private double precioAlmacen;
    private String nombreAlmacen;
    
    /**
     * ArrayList con todos los productos de dicho almacen.
     */
    ArrayList<Producto> listadoProductos=new ArrayList<>();
    
    
    /**
     * METODOS SETTER AND GETTER.
     */
    
    /**
     *
     * @return ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     *
     * @param ruta
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     *
     * @return precioAlmacen
     */
    public double getPrecioAlmacen() {
        return precioAlmacen;
    }
    
    /**
     * Sumatorio de los precios de cada producto (botellas * pvp).
     */
    public void setPrecioAlmacen(){
        
        double precio=0;
        
        for(int i=0;i<listadoProductos.size();i++){
            
            precio += ((double) listadoProductos.get(i).getBotellasDisponibles() * Double.parseDouble(listadoProductos.get(i).getPVP()));
        
        }
        this.precioAlmacen = precio;
    }
    
    
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }
    
    
    
    public String getRutaDestino() {
        return rutaDestino;
    }

    public void setRutaDestino(String rutaDestino) {
        this.rutaDestino = rutaDestino;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloAlmacen;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class Producto {
    
    private String codNum;
    private String descripcion;
    private String marca;
    private String deporte;
    private String destinatario;
    private String color;
    private String talla;
    private String precioSinIva;
    private String cantidad;
    private String precioIva;
    private final String iva = "21";
    private String precioConIva;
    
    
    /*METODOS SETTER AND GETTER*/
    
    /**
     * 
     * @return Codigo del producto
     */
    public String getCodNum() {
        return codNum;
    }

    /**
     *
     * @param codNum
     */
    public void setCodNum(String codNum) {
        this.codNum = codNum;
    }

    /**
     *
     * @return Descripcion del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return Deporte del producto
     */
    public String getDeporte() {
        return deporte;
    }

    /**
     *
     * @param deporte
     */
    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    /**
     *
     * @return Destinatario del producto
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     *
     * @param destinatario
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     *
     * @return Color del producto
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return Talla del producto
     */
    public String getTalla() {
        return talla;
    }

    /**
     *
     * @param talla
     */
    public void setTalla(String talla) {
        this.talla = talla;
    }

    /**
     *
     * @return Precio del producto sin iva
     */
    public String getPrecioSinIva() {
        return precioSinIva;
    }

    /**
     *
     * @param precioSinIva
     */
    public void setPrecioSinIva(String precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    /**
     *
     * @return Cantidad de productos que hay
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param cantidad
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     *
     * @return iva del producto (21%)
     */
    public String getIva() {
        return iva;
    }

    /**
     * 
     * @return Precio final (Precio sin iva * iva / 100)
     */
    public String getPrecioConIva() {
        return precioConIva;
    }

    /**
     * 
     * @param precioConIva 
     */
    public void setPrecioConIva(String precioConIva) {
        this.precioConIva = precioConIva;
    }

    /**
     * 
     * @return Marca del producto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * 
     * @param marca 
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * 
     * @return Precio del iva para el producto
     */
    public String getPrecioIva() {
        return precioIva;
    }

    /**
     * 
     * @param precioIva 
     */
    public void setPrecioIva(String precioIva) {
        this.precioIva = precioIva;
    }

}

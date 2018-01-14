/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloClientes;

/**
 * Clase que contiene los campos de un cliente.
 * @author Francisco Blázquez Matías (PB1)
 */
public class Cliente {
    
    private String mail;
    private String pass;
    private String nombre;
    private String apellido1, apellido2;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String cp;
    private String telefono;

    /*METODOS GETTER AND SETTER*/
    
    /**
     * 
     * @return e-mail del cliente
     */
    public String getMail() {
        return mail;
    }
    
    /**
     * 
     * @param mail 
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    /**
     * 
     * @return password del cliente
     */
    public String getPass() {
        return pass;
    }

    /**
     * 
     * @param pass 
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * 
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return primer apellido del cliente
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * 
     * @param apellido1 
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * 
     * @return segundo apellido del cliente
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * 
     * @param apellido2 
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * 
     * @return direccion del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * 
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * 
     * @return poblacion del cliente
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * 
     * @param poblacion 
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * 
     * @return provincia del cliente
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * 
     * @param provincia 
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * 
     * @return codigo postal del cliente
     */
    public String getCp() {
        return cp;
    }

    /**
     * 
     * @param cp 
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * 
     * @return telefono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * 
     * @param telefono 
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}

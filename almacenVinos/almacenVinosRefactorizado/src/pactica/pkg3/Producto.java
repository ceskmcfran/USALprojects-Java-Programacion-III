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
public class Producto {

    /**
     * Atributos.
     */
    private final String bodega;
    private final float capacidad;
    private final String denominacionDeOrigen;
    private final int fechaEnvasado;
    private final String graduacionAlcoholica;
    private final String identificador;
    private final String nombreVino;
    private final int botellasDisponibles;
    private final String PVD;
    private final String PVP;
    private final String tipoUva;

    /**
     * Constructor.
     * @param bodega
     * @param capacidad
     * @param denominacionDeOrigen
     * @param fechaEnvasado
     * @param graduacionAlcoholica
     * @param identificador
     * @param nombreVino
     * @param botellasDisponibles
     * @param PVD
     * @param PVP
     * @param tipoUva 
     */
    public Producto(String bodega, float capacidad, String denominacionDeOrigen, int fechaEnvasado, String graduacionAlcoholica, String identificador, String nombreVino, int botellasDisponibles, String PVD, String PVP, String tipoUva ){
        this.bodega = bodega;
        this.capacidad = capacidad;
        this.denominacionDeOrigen = denominacionDeOrigen;
        this.fechaEnvasado = fechaEnvasado;
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.identificador = identificador;
        this.nombreVino = nombreVino;
        this.botellasDisponibles = botellasDisponibles;
        this.PVD=PVD;
        this.PVP=PVP;
        this.tipoUva = tipoUva;
    
    }
    
    /**
     * METODOS SETTER AND GETTER.
     */

    /**
     *
     * @return bodega
     */
    public String getBodega() {
        return bodega;
    }

    /**
     *
     * @return capacidad
     */
    public float getCapacidad() {
        return capacidad;
    }

    /**
     *
     * @return denominacionDeOrigen
     */
    public String getDenominacionDeOrigen() {
        return denominacionDeOrigen;
    }

    /**
     *
     * @return fechaEnvasado
     */
    public int getFechaEnvasado() {
        return fechaEnvasado;
    }

    /**
     *
     * @return graduacionAlcoholica
     */
    public String getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    /**
     *
     * @return indentificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     *
     * @return nombreVino
     */
    public String getNombreVino() {
        return nombreVino;
    }

    /**
     *
     * @return botellasDisponibles
     */
    public int getBotellasDisponibles() {
        return botellasDisponibles;
    }

    /**
     *
     * @return PVD
     */
    public String getPVD() {
        return PVD;
    }

    /**
     *
     * @return PVP
     */
    public String getPVP() {
        return PVP;
    }

    /**
     *
     * @return tipoUva
     */
    public String getTipoUva() {
        return tipoUva;
    }
    
    
    
    
}

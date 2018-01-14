/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloAlmacen;

import java.util.Random;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class ProductoAleatorio {
    
    private final String[] deporte = {"Futbol", "Tenis", "Basket", "Caza", "Hockey", "Badminton", 
                                    "Atletismo", "Gimnasia"};
    private final String[] destinatario = {"Hombre", "Mujer", "Junior", "Niño"};
    private final String[] talla = {"XS", "S", "L", "M", "L", "XL", "XXL", "XXXL"};
    private final String[] color = {"Rojo", "Azul", "Verde", "Amarillo", "Beige", "Negro", "Blanco",
                                    "Naranja", "Ocre", "Morado"};
    private final String[] descripcion = {"Botas", "Tutu", "Camiseta Corta", "Camiseta Larga", "Pantalon Corto",
                                        "Bañador", "Casco", "Zapatillas", "Brazalete", "Rodilleras", "Coderas",
                                        "Mallas", "Guantes"};
    
    private final String[] palabraInutil = {"Cuel", "RealTy", "Confit", "Etyras", "Tirk", "Ike", "Gyro", "Creth",
                                            "UnderT", "Xrit"};
    
    
    /**
     * Retorna el una posicion aleatoria del vector que se le pasa por parametro
     * @param campo
     * @return vector[numero aleatorio basado en la longitud de ese vector]
     */
    public String campoAleatorio(String[] campo){
        
        Random rand = new Random(System.nanoTime());
        
        return(campo[rand.nextInt(campo.length)]);
    }
    
    /*METODOS SETTER AND GETTER*/

    /**
     *
     * @return vector de deportes
     */
    public String[] getDeporte() {
        return deporte;
    }

    /**
     *
     * @return vector de destinatarios
     */
    public String[] getDestinatario() {
        return destinatario;
    }

    /**
     *
     * @return vector de tallas
     */
    public String[] getTalla() {
        return talla;
    }

    /**
     *
     * @return vector de colores
     */
    public String[] getColor() {
        return color;
    }

    /**
     *
     * @return vector de prendas
     */
    public String[] getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @return vector de marcas
     */
    public String[] getPalabraInutil() {
        return palabraInutil;
    }
    
    
}

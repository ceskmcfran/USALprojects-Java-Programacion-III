/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloClientes;

import java.util.Random;

/**
 * Generador de clientes aleatorios.
 * @author Francisco Blázquez Matías (PB1)
 */
public class ClienteAleatorio {
    
    /**
     * Vector de nombres aleatorios.
     */
    private final String[] nombreA = {"Francisco", "Roberto", "Mario", "Eduardo",
                            "Alfonso", "Pedro", "Nacho", "Naiara",
                            "Laura", "Sara", "Diego", "Juan", "Antonio", "Manolo", "Montse", "Paula"};
    
    /**
     * Vector de apellidos aleatorios.
     */
    private final String[] apellidosA = {"Blazquez", "Martin", "Sanchez", "Arellano",
                                "Dominguez", "Barroso", "Tomas", "Mendez",
                                "Garcia", "Fernandez", "Muñoz", "Petisco", "Perez", "Carrasco", "Blanco"};
    
    /**
     * Vector de emails aleatorios.
     */
    private final String[] mailA = {"gmail.com", "hotmail.com", "yahoo.es", "hotmail.es", "yahoo.com"};
    
    /**
     * Abecedario.
     */
    private final String abecedario = "abcdefghijklmnopqrstuvwxyz";
    
    /**
     * Vector de provincias aleatorias.
     */
    private final String[] provinciaA = {"Salamanca", "Avila", "Leon", "Sevilla",
                                "Madrid", "Barcelona", "Cadiz", "Oviedo", "Caceres",
                                "Pontevedra", "Bilbao"};
    
    /**
     * Vector de direcciones aleatorias.
     */
    private final String[] direccionA = {"Alfareros", "Don Quijote", "Riaño", "Cristobal Colon",
                                "Toro", "Valle", "Pintores", "Guerrilleros", "Relojeros",
                                "Gran Capitan", "Almirante", "Orquidea"};
    
    
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
     * @return vector de nombres
     */
    public String[] getNombreA() {
        return nombreA;
    }

    /**
     *
     * @return vector de apellidos
     */
    public String[] getApellidosA() {
        return apellidosA;
    }

    /**
     *
     * @return vector de terminaciones de correo electronicos
     */
    public String[] getMailA() {
        return mailA;
    }

    /**
     *
     * @return vector con el abecedario
     */
    public String getAbecedario() {
        return abecedario;
    }

    /**
     *
     * @return vector con las provincias
     */
    public String[] getProvinciaA() {
        return provinciaA;
    }

    /**
     *
     * @return vector con calles
     */
    public String[] getDireccionA() {
        return direccionA;
    }

}

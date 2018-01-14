/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadefrecuencias;

/**
 *
 * @author Francisco Blazquez Matias
 */
class Palabra {
    private String clave;
    private int valor;

    public Palabra(String clave, int valor) {
        this.clave = clave;
        this.valor = valor;
    }

    
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Modelo;


public class Transicion {
    
    private String simbolo;
    private String transición;

    public Transicion() {
    }

    public Transicion(String simbolo, String transición) {
        this.simbolo = simbolo;
        this.transición = transición;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTransición() {
        return transición;
    }

    public void setTransición(String transición) {
        this.transición = transición;
    }
    
    
    
}

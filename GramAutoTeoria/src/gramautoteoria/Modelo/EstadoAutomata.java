/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Modelo;


public class EstadoAutomata {
    
    String Estado;
    String simbolo;
    String transicion;
    boolean aceptacion;

    public EstadoAutomata() {
    }

    public EstadoAutomata(String Estado, String simbolo, String transicion, boolean aceptacion) {
        this.Estado = Estado;
        this.simbolo = simbolo;
        this.transicion = transicion;
        this.aceptacion = aceptacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTransicion() {
        return transicion;
    }

    public void setTransicion(String transicion) {
        this.transicion = transicion;
    }

    public boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }
    
    
    
    
    
}

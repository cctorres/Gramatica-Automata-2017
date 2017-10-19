/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Modelo;

import java.util.ArrayList;


public class EstadoAutomata {
    
    private String Estado;
    private ArrayList<Transicion> transiciones;
    private boolean aceptacion;

    public EstadoAutomata() {
    }

    public EstadoAutomata(String Estado, ArrayList<Transicion> transiciones, boolean aceptacion) {
        this.Estado = Estado;
        this.transiciones = transiciones;
        this.aceptacion = aceptacion;
    }
    
    public EstadoAutomata(String Estado, boolean aceptacion) {
        ArrayList<Transicion> transicionesA = new ArrayList<Transicion>();
        this.Estado = Estado;
        this.transiciones = transicionesA;
        this.aceptacion = aceptacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(ArrayList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }
    
    public boolean estadoDeterministico(){
        for(int j =0; j < this.getTransiciones().size()-1;j++){
                for(int k =j+1; k < this.getTransiciones().size();k++){
                    Transicion transicionJ = (Transicion) this.getTransiciones().get(j);
                    Transicion transicionK = (Transicion) this.getTransiciones().get(k);
                    if(transicionJ.getSimbolo().equals(transicionK.getSimbolo())){
                        return false;
                    }
                }               
            }
        return true;
    }
    
    public String unirTransiciones(String simbolo){
        String resultado = "";
        for(int i = 0; i < transiciones.size(); i++){
            if(transiciones.get(i).getSimbolo().equals(simbolo)){
                resultado = resultado+transiciones.get(i).getTransición();
            }
        }
        return resultado;
    }
    
    public ArrayList<String> evaluarTransicion(String simbolo){
        ArrayList<String> salida = new ArrayList<>();
        for(int i = 0; i < this.transiciones.size();i++){
            Transicion transicionEvaluar = (Transicion) this.transiciones.get(i);
            if(transicionEvaluar.getSimbolo().equalsIgnoreCase(simbolo)){
                salida.add(transicionEvaluar.getTransición());
            }
        }
        return salida;
    }

    
    
    
    
    
    
}

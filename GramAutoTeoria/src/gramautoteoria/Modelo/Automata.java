
package gramautoteoria.Modelo;

import java.util.ArrayList;



public class Automata {
    
    ArrayList<EstadoAutomata> estados;
    ArrayList<String> simbolosEntrada;

    public Automata() {
        ArrayList<EstadoAutomata> estadosA = new ArrayList<EstadoAutomata>();
        ArrayList<String> simbolosA = new ArrayList<String>();
        this.estados = estadosA;
        this.simbolosEntrada = simbolosA;
    }

    public Automata(ArrayList<EstadoAutomata> estados, ArrayList<String> simbolosEntrada) {
        this.estados = estados;
        this.simbolosEntrada = simbolosEntrada;
    }

    public ArrayList<EstadoAutomata> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<EstadoAutomata> estados) {
        this.estados = estados;
    } 

    public ArrayList<String> getSimbolosEntrada() {
        return simbolosEntrada;
    }

    public void setSimbolosEntrada(ArrayList<String> simbolosEntrada) {
        this.simbolosEntrada = simbolosEntrada;
    }
    
    public void agregarSimboloEntrada(String simbolo){
        if(this.simbolosEntrada.contains(simbolo)){
            return;
        }
        this.simbolosEntrada.add(simbolo);
    }
    
    public int retonarPosSimbolo(String simbolo){
        return this.simbolosEntrada.indexOf(simbolo);        
    }
    
    public void agregarEstados(String estado, boolean aceptacion){
        EstadoAutomata estadoNuevo = new EstadoAutomata(estado, aceptacion);
        this.estados.add(estadoNuevo);
    }
    
    public int retonarPosEstado(String nombreEstado){
        for(int i =0; i < this.estados.size();i++){
            if(this.estados.get(i).getEstado().equals(nombreEstado)){
                return i;
            }
        }
        return -1;
    }
    
    public boolean esDeterministico(){
        for(int i =0; i < this.estados.size();i++){
            EstadoAutomata estadoActual = (EstadoAutomata) this.estados.get(i);
            for(int j =0; j < estadoActual.getTransiciones().size()-1;j++){
                for(int k =j+1; k < estadoActual.getTransiciones().size();k++){
                    Transicion transicionJ = (Transicion) estadoActual.getTransiciones().get(j);
                    Transicion transicionK = (Transicion) estadoActual.getTransiciones().get(k);
                    if(transicionJ.getSimbolo().equals(transicionK.getSimbolo())){
                        return false;
                    }
                }               
            }
        }
        return true;
    }
    
    public void agregarTransicionAutomata(String estadoActual, String simboloEntrada, String estadoSiguiente){
        int pos;
        this.agregarSimboloEntrada(simboloEntrada);
        pos = this.retonarPosEstado(estadoActual);
        EstadoAutomata estadoContenido = this.estados.get(pos);
        Transicion transicionNueva = new Transicion(simboloEntrada,estadoSiguiente);
        estadoContenido.getTransiciones().add(transicionNueva);
    }
    
    public String imprimirAutomata(){
        String resultado = "Simbolos de entrada: ";
        for(int i = 0; i < this.simbolosEntrada.size(); i++){
            resultado = resultado+this.simbolosEntrada.get(i)+",";
        }
        for(int i = 0; i < this.estados.size(); i++){
            resultado = resultado+"\n"+"Estado "+this.estados.get(i).getEstado()+" :";
            for(int j = 0; j < this.estados.get(i).getTransiciones().size(); j++){
                resultado = resultado+ this.estados.get(i).getTransiciones().get(j).getSimbolo()+">"+ this.estados.get(i).getTransiciones().get(j).getTransición()+" -- ";
            }
            resultado = resultado+"  :: "+this.estados.get(i).isAceptacion();
        }
        return resultado;
    }
    
    public boolean evaluarHilera(String hilera){
        return this.evaluarHileraEstado(hilera, this.estados.get(0),0);
    }
    
    public boolean evaluarHileraEstado(String hilera, EstadoAutomata estado, int i){
        if(i == hilera.length() ){
            if(estado.isAceptacion()){
                return true;
            }
            return false;
        }
        boolean b = false;
        if(i < hilera.length() ){            
            String simbolo = hilera.substring(i, i+1);
            for(int k = 0; k < estado.getTransiciones().size() & b == false;k++){
                if(estado.getTransiciones().get(k).getSimbolo().equals(simbolo)){
                    int posTransicion = this.retonarPosEstado(estado.getTransiciones().get(k).getTransición());
                    //String transicion = estado.getTransiciones().get(k).getTransición();
                    b = this.evaluarHileraEstado(hilera, this.estados.get(posTransicion),i+1);
                }
            }
        }        
        return b;
    }
    
    public Automata AFNDaAFD(){
       Automata AFD = new Automata();
       for(int i =0; i < this.simbolosEntrada.size();i++){
           AFD.agregarSimboloEntrada(this.simbolosEntrada.get(i));
       }
       for (int i = 0; i < this.estados.size(); i++) {
            AFD.agregarEstados(this.estados.get(i).getEstado(), this.estados.get(i).isAceptacion());
            for (int j = 0; j < this.simbolosEntrada.size(); j++) {
                String transicion = this.estados.get(i).unirTransiciones(this.simbolosEntrada.get(j));                
                AFD.agregarTransicionAutomata(this.estados.get(i).getEstado(), this.simbolosEntrada.get(j), transicion);
            }
        }
       int k = 2
;       while(k <= this.estados.size()){
           for (int i = 0; i < this.estados.size(); i++) {
               for (int j = 0; i < k; j++) {

               }
           }
           k++;
       }
           
       
       return AFD;
    }
    
    public Automata AFNDaAFDr(){
        Automata AFD = new Automata();
        EstadoAutomata estadoInicial = this.estados.get(0);
        
        
        
        
        return AFD;
    }
    
    
    
}


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
       Automata retorno = new Automata();
       ArrayList<String> estadosAceptacion = this.estadosAceptacion();
        retorno.clonarSimbolos(this);
        EstadoAutomata nuevoEstado = this.estados.get(0).clonar();
        retorno.estados.add(nuevoEstado);
        for (int i = 0; i < retorno.estados.size(); i++) {
            EstadoAutomata estadoEvaluado = retorno.estados.get(i);
            for (int j = 0; j < retorno.simbolosEntrada.size(); j++) {
                String simbolo = retorno.simbolosEntrada.get(j);
                ArrayList<String> salidas = estadoEvaluado.evaluarTransicion(simbolo);
                if (salidas.size() > 0) {
                    String estadoNuevo = "";
                    boolean aceptacion = false;
                    for (int k = 0; k < salidas.size(); k++) {
                            estadoNuevo = estadoNuevo+salidas.get(k);
                            if(!aceptacion){
                                aceptacion = estadosAceptacion.contains(salidas.get(k));
                            }
                        }                    
                        if (!retorno.existeEstado(estadoNuevo)) {
                            EstadoAutomata estadoAAgregar = new EstadoAutomata(estadoNuevo,aceptacion);
                            retorno.estados.add(estadoAAgregar);

                    }
                }
            }
        }
        
        
        
        return retorno;
    }
    
    
    
    public ArrayList<String> estadosAceptacion(){
        ArrayList<String> estadosA = new ArrayList<>();
        for(int i=0;i<this.estados.size();i++){
            if(this.estados.get(i).isAceptacion()){
                estadosA.add(this.estados.get(i).getEstado());
            }
        }
        return estadosA;
    }
    
    public Automata eliminarEstadosExtraños(){
        Automata retorno = new Automata();
        retorno.clonarSimbolos(this);
        EstadoAutomata nuevoEstado = this.estados.get(0).clonar();
        retorno.estados.add(nuevoEstado);
        for (int i = 0; i < retorno.estados.size(); i++) {
            EstadoAutomata estadoEvaluado = retorno.estados.get(i);
            for (int j = 0; j < retorno.simbolosEntrada.size(); j++) {
                String simbolo = retorno.simbolosEntrada.get(j);
                ArrayList<String> salidas = estadoEvaluado.evaluarTransicion(simbolo);
                if (salidas.size() > 0) {
                    for (int k = 0; k < salidas.size(); k++) {
                        String estadoNuevo = salidas.get(k);
                        if (!retorno.existeEstado(estadoNuevo)) {
                            int posEstado = this.retonarPosEstado(estadoNuevo);
                            EstadoAutomata estadoAClonar = this.estados.get(posEstado);
                            EstadoAutomata estadoAAgregar = estadoAClonar.clonar();
                            retorno.estados.add(estadoAAgregar);
                        }

                    }
                }
            }
        }
        
        
        
        return retorno;
    }
    
    public void clonarSimbolos(Automata automataAClonar){
        for(int i=0;i<automataAClonar.simbolosEntrada.size();i++){
            this.simbolosEntrada.add(automataAClonar.simbolosEntrada.get(i));
        }
    }
    
    public boolean existeEstado(String estado){
        for(int i=0;i<this.estados.size();i++){
            if(estado.equals(this.estados.get(i).getEstado())){
                return true;
            }
        }
        return false;
    }
    
}

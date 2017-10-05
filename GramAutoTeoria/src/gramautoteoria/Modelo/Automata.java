
package gramautoteoria.Modelo;

import java.util.ArrayList;



public class Automata {
    
    ArrayList<EstadoAutomata> estados;

    public Automata() {
    }

    public Automata(ArrayList<EstadoAutomata> estados) {
        this.estados = estados;
    }

    public ArrayList<EstadoAutomata> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<EstadoAutomata> estados) {
        this.estados = estados;
    }
    
    

    public String imprimirAutomata(){
        String automata = "";
        for(int i=0;i<this.getEstados().size();i++){
            EstadoAutomata estado = (EstadoAutomata) this.getEstados().get(i);
            String aceptacion;
            if(estado.getAceptacion()== true){
                aceptacion = "1";
            }
            else{
                aceptacion = "0";
            }
            automata = automata+estado.getEstado()+" = "+estado.getTransicion()+" - "+aceptacion;
        }
        return automata;
    }
    
}

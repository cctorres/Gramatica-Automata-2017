/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Controlador;

import gramautoteoria.Modelo.Automata;
import gramautoteoria.Modelo.EstadoAutomata;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class GramAutoTeoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        EstadoAutomata uno = new EstadoAutomata("A","a","B",false);
        ArrayList<EstadoAutomata> estados = new ArrayList();
        estados.add(uno);
        Automata automata = new Automata(estados);
        System.out.println(automata.imprimirAutomata());
    }
    
}

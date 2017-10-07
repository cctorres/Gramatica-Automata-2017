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
         
         Automata a = new Automata();
        a.agregarEstados("A", false);
        a.agregarEstados("B", false);
        a.agregarEstados("C", true);
        
        a.agregarTransicionAutomata("A", "0", "B");
        a.agregarTransicionAutomata("A", "1", "B");
        a.agregarTransicionAutomata("A", "1", "C");
        a.agregarTransicionAutomata("B", "1", "C");
        a.agregarTransicionAutomata("C", "1", "A");
        System.out.println(a.esDeterministico());
        System.out.println(a.imprimirAutomata());
        
        System.out.println(a.evaluarHilera("11"));
        
        
        
    }
    
}

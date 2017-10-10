/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Controlador;

import gramautoteoria.Modelo.Automata;
import gramautoteoria.Modelo.EstadoAutomata;
import gramautoteoria.Modelo.Gramatica;
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
        
        /*
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
        
        */
        Gramatica g = new Gramatica();
        
        g.agregarProduccion("<S>", "a<A><B><S>");
        g.agregarProduccion("<S>", "b<C><A><C>d");
        g.agregarProduccion("<A>", "b<A><B>");
        g.agregarProduccion("<A>", "c<S><A>");
        g.agregarProduccion("<A>", "c<C><C>");
        g.agregarProduccion("<B>", "b<A><B>");
        g.agregarProduccion("<B>", "c<S><B>");
        g.agregarProduccion("<C>", "c<S>");
        g.agregarProduccion("<C>", "c");
        System.out.println(g.imprimirGramatica());
        
        ArrayList<String> nTV = g.NoTerminalesVivos();
        System.out.println(nTV.toString());
        ArrayList<String> nTM = g.NoTerminalesMuertos();
        System.out.println(nTM.toString());
        
        Gramatica gg = g.eliminarNTM();
        System.out.println(gg.imprimirGramatica());
        
        
        
        
        
        
    }
    
}

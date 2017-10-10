/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramautoteoria.Controlador;

import gramautoteoria.Modelo.Automata;
import gramautoteoria.Modelo.EstadoAutomata;
import gramautoteoria.Modelo.Gramatica;
import gramautoteoria.Modelo.Produccion;
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
        
        g.agregarProduccion("<S>", "a<A><B>");
        g.agregarProduccion("<S>", "");
        g.agregarProduccion("<A>", "d<D><A>");
        g.agregarProduccion("<A>", "e");
        g.agregarProduccion("<B>", "b<E>");
        g.agregarProduccion("<B>", "f");
        g.agregarProduccion("<C>", "c<A><B>");
        g.agregarProduccion("<C>", "d<S><D>");
        g.agregarProduccion("<C>", "a");
        g.agregarProduccion("<D>", "e<A>");
        g.agregarProduccion("<E>", "f<A>");
        g.agregarProduccion("<E>", "g");
        System.out.println(g.imprimirGramatica());
        
        ArrayList<String> nTV = g.noTerminalesAlcanzables();
        System.out.println(nTV.toString());
        ArrayList<String> nTM = g.noTerminalesInalcanzables();
        System.out.println(nTM.toString());
        
        Gramatica gg = g.eliminarNTI();
        System.out.println(gg.imprimirGramatica());
        
        g.eliminarProduccion(1);
        System.out.println(g.imprimirGramatica());
        
        
        Produccion p = new Produccion("S","a");
        System.out.println(p.esLinealDerecha());
        
        
    }
    
}

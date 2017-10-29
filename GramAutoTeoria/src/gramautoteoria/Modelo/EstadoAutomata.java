package gramautoteoria.Modelo;

import java.util.ArrayList;

public class EstadoAutomata {

    private String Estado;
    private ArrayList<Transicion> transiciones;
    private boolean aceptacion;

    /**
     * Constructor vacio
     */
    public EstadoAutomata() {
        ArrayList<Transicion> transicionesA = new ArrayList<Transicion>();
        this.transiciones = transicionesA;
    }

    //Constructor
    public EstadoAutomata(String Estado, ArrayList<Transicion> transiciones, boolean aceptacion) {
        this.Estado = Estado;
        this.transiciones = transiciones;
        this.aceptacion = aceptacion;
    }

    //Constructor
    public EstadoAutomata(String Estado, ArrayList<Transicion> transiciones) {
        this.Estado = Estado;
        this.transiciones = transiciones;
        this.aceptacion = false;
    }

    //constructor
    public EstadoAutomata(String Estado, boolean aceptacion) {
        ArrayList<Transicion> transicionesA = new ArrayList<Transicion>();
        this.Estado = Estado;
        this.transiciones = transicionesA;
        this.aceptacion = aceptacion;
    }

    //Getter & Setter
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

    //Métodos
    /**
     * Agrega una transición al Estado
     *
     * @param simbolo Simbolo de la transición
     * @param estadoSiguiente Estado siguiente de la transición
     */
    public void agregarTransicion(String simbolo, String estadoSiguiente) {
        Transicion nuevaTransicion = new Transicion(simbolo, estadoSiguiente);
        this.transiciones.add(nuevaTransicion);
    }

    /**
     * Determina si un estado es deterministico
     *
     * @return Booleando con el resultado
     */
    public boolean estadoDeterministico() {
        for (int j = 0; j < this.getTransiciones().size() - 1; j++) {//Se recorren las transiciones
            for (int k = j + 1; k < this.getTransiciones().size(); k++) {//Se recorren las ddemás transiciones
                Transicion transicionJ = (Transicion) this.getTransiciones().get(j);
                Transicion transicionK = (Transicion) this.getTransiciones().get(k);
                if (transicionJ.getSimbolo().equals(transicionK.getSimbolo())) {
                    //Se pregunta si hay transiciones con el mismo simbolo de entrada
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retorna una Array con los estados siguientes dependiendo del simbolo de
     * entrada
     *
     * @param simbolo Símbolo de entrada de las transiciones
     * @return ArrayList String con los estados siguientes
     */
    public ArrayList<String> evaluarTransicion(String simbolo) {
        ArrayList<String> salida = new ArrayList<>();
        for (int i = 0; i < this.transiciones.size(); i++) {//Se recorren las transiciones
            Transicion transicionEvaluar = (Transicion) this.transiciones.get(i);
            if (transicionEvaluar.getSimbolo().equalsIgnoreCase(simbolo)) {
                //Si el simbolo de entrada de esa transición es igual al del parámetro
                //Se añexa el estado siguiente al Array
                salida.add(transicionEvaluar.getTransición());
            }
        }
        return salida;
    }

    /**
     * Genera una copia de un Estado
     *
     * @return EstadoAutomata que es la copia del this
     */
    public EstadoAutomata clonar() {
        EstadoAutomata clon = new EstadoAutomata();
        clon.setEstado(this.getEstado());
        clon.setAceptacion(this.aceptacion);
        for (int i = 0; i < this.getTransiciones().size(); i++) {
            Transicion nueva = new Transicion(this.getTransiciones().get(i).getSimbolo(), this.getTransiciones().get(i).getTransición());
            clon.transiciones.add(nueva);
        }
        return clon;
    }
}

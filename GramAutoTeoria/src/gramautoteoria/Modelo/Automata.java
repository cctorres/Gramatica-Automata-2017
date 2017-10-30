package gramautoteoria.Modelo;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;



public class Automata {
    ArrayList<EstadoAutomata> estados; //ArrayList que contiene todos los estados del autómata
    ArrayList<String> simbolosEntrada; //ArrayList que contiene todos los símbolos de entrada del autómata

    /**
     * Constructor vacio
     */
    public Automata() {
        ArrayList<EstadoAutomata> estadosA = new ArrayList<EstadoAutomata>();
        ArrayList<String> simbolosA = new ArrayList<String>();
        this.estados = estadosA;
        this.simbolosEntrada = simbolosA;
    }

    /**
     * Constructor
     * @param estados Array de estados que serán el Array del nuevo Automata
     * @param simbolosEntrada Array de String de símbolos que serán el Array del nuevo Automata
     */
    public Automata(ArrayList<EstadoAutomata> estados, ArrayList<String> simbolosEntrada) {
        this.estados = estados;
        this.simbolosEntrada = simbolosEntrada;
    }

    //Getter & Setter
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

    //Métodos
    
    /**
     * Agrega un símbolo de entrada al Autómata
     * @param simbolo 
     */
    public void agregarSimboloEntrada(String simbolo) {
        if (this.simbolosEntrada.contains(simbolo)) {
            return;
        }
        this.simbolosEntrada.add(simbolo);
    }

    /**
     * Retorna la posición del símbolo en el Array de símbolos
     * @param simbolo String con el símbolo que se desea buscar la posición
     * @return Entero posición del simbolo
     */
    public int retonarPosSimbolo(String simbolo) {
        return this.simbolosEntrada.indexOf(simbolo);
    }
    
    /**
     * Retorna la posición del estado en el Array de estados
     * @param nombreEstado String con el nombre del estado que se desea buscar la posición
     * @return Entero con la posición del estado, retorna -1 si el estado no existe en el autómata
     */
    public int retonarPosEstado(String nombreEstado) {
        for (int i = 0; i < this.estados.size(); i++) {
            if (this.estados.get(i).getEstado().equals(nombreEstado)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Agrega un estado nuevo sin transiciones al Autómata 
     * @param estado String con el nombre del estado
     * @param aceptacion Boolean con la aceptación del estado
     */
    public void agregarEstados(String estado, boolean aceptacion) {
        EstadoAutomata estadoNuevo = new EstadoAutomata(estado, aceptacion);
        this.estados.add(estadoNuevo);
    }   

    /**
     * Retorna si el autómata es determinístico
     * @return Boolean con el resultado
     */
    public boolean esDeterministico() {
        for (int i = 0; i < this.estados.size(); i++) {//Recorrido de los estados
            EstadoAutomata estadoActual = (EstadoAutomata) this.estados.get(i);
            for (int j = 0; j < estadoActual.getTransiciones().size() - 1; j++) {//Recorrido transiciones del estado
                for (int k = j + 1; k < estadoActual.getTransiciones().size(); k++) {//Recorre las demás transiciones
                    Transicion transicionJ = (Transicion) estadoActual.getTransiciones().get(j);
                    Transicion transicionK = (Transicion) estadoActual.getTransiciones().get(k);
                    if (transicionJ.getSimbolo().equals(transicionK.getSimbolo())) {
                        //Pregunta si hay transiciones con el mismo simbolo de entrada
                        //Si es así, manda false, pues, un automáta con dos transiciones en el mismo estado y simbolo no es AFD
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Agrega una nueva transición a un estado del Autómata
     * @param estadoActual String con el nombre del estado al que se le agregará la transición
     * @param simboloEntrada String con el simbolo de entrada de la transición
     * @param estadoSiguiente  String con el estado siguiente de la transición
     */
    public void agregarTransicionAutomata(String estadoActual, String simboloEntrada, String estadoSiguiente) {
        int pos;
        this.agregarSimboloEntrada(simboloEntrada);
        pos = this.retonarPosEstado(estadoActual);//Se busca el estado que se le agregará la transición
        if (pos == -1) { //Si el estado no existe se crea el estado
            this.agregarEstados(estadoActual, false);
        }
        pos = this.retonarPosEstado(estadoActual); //Se vuelve a buscar el estado ya que si se agregó un nuevo estado
        //las posiciones podrían haber variado respecto a la búsqueda anterior
        EstadoAutomata estadoContenido = this.estados.get(pos);
        Transicion transicionNueva = new Transicion(simboloEntrada, estadoSiguiente);
        estadoContenido.getTransiciones().add(transicionNueva);//Inserción de la transición en el Array de transiciones del estado
    }

    /**
     * Retorna un string con la representación del estado tipo .txt
     * @return String que representa al autómata
     */
    public String imprimirAutomata() {
        String resultado = "{Simbolos de entrada: ";
        for (int i = 0; i < this.simbolosEntrada.size(); i++) {
            //Se imprimen los símbolos del autómata
            resultado = resultado + this.simbolosEntrada.get(i) + ",";
        }
        resultado = resultado + "}";
        for (int i = 0; i < this.estados.size(); i++) {//Recorrido de los estados
            //Se separa cada estado del otro poniendo al principio un {
            resultado = resultado + "\n" + "{Estado " + this.estados.get(i).getEstado() + ":";
            for (int j = 0; j < this.estados.get(i).getTransiciones().size(); j++) {//Recorrido transiciones
                //Se imprime la transición de la forma simbolo>transición
                resultado = resultado + this.estados.get(i).getTransiciones().get(j).getSimbolo() + "=" + this.estados.get(i).getTransiciones().get(j).getTransición() + "-";
            }
            //Se cierra el estado con una } para separar cada estado
            resultado = resultado + "*" + this.estados.get(i).isAceptacion() + "}";
        }
        return resultado;
    }

    /**
     * Evalua una hilera tipo String y determina si es aceptada o no por el autómata
     * @param hilera String con la hilera que se desea evaluar
     * @return Booleano con el resultado de la operación
     */
    public boolean evaluarHilera(String hilera) {
        //Se empieza la la evaluación de la hilera con el primer estado del autómata y con el primer caracter de la hilera
        return this.evaluarHileraEstado(hilera, this.estados.get(0), 0);
    }

    /**
     * Evalua una hilera tipo String y determina si es aceptada o no por el autómata
     * @param hilera String con la hilera que se desea evaluar
     * @param estado EstadoAutomata con el estado actual en el que se encuentra la evaluación de la hilera
     * @param i Entero con la posición del caracter que se evalua en la hielra
     * @return Booleano con el resultado de la operación
     */
    public boolean evaluarHileraEstado(String hilera, EstadoAutomata estado, int i) {
        // Si ya el caracter evaluado es el último se pregunta por el estado actual
        if (i == hilera.length()) {
            //Si es de aceptación se retorna true, en caso contrario se retorna false
            if (estado.isAceptacion()) {
                return true;
            }
            return false;
        }
        //Si aún no es el último caracter de la hilera se sigue invocando el método recursivamente
        boolean b = false;
        if (i < hilera.length()) {
            String simbolo = hilera.substring(i, i + 1);//Saca el caracter de la hilera que se evalua
            for (int k = 0; k < estado.getTransiciones().size() & b == false; k++) {//Se recorren las transiciones
                if (estado.getTransiciones().get(k).getSimbolo().equals(simbolo)) {//Se pregunta si la transición tiene como simbolo de entrada el caracter
                    int posTransicion = this.retonarPosEstado(estado.getTransiciones().get(k).getTransición()); //Se busca el estado siguiente de la transición
                    String transicion = estado.getTransiciones().get(k).getTransición();
                    //Se invoca el método recursivamente con el estado siguiente de la transición y con la posición siguiente del caracter en la hilera
                    b = this.evaluarHileraEstado(hilera, this.estados.get(posTransicion), i + 1);
                }
            }
        }
        return b;
    }

    /**
     * Devuelve un Array de String con todos los estados de aceptación
     * @return Array de String con los nombre de todos los estados de aceptación
     */
    public ArrayList<String> estadosAceptacion() {
        ArrayList<String> estadosA = new ArrayList<>();
        for (int i = 0; i < this.estados.size(); i++) {//Se recorren los estados
            if (this.estados.get(i).isAceptacion()) {//Se pregunta si el estado es de aceptación
                estadosA.add(this.estados.get(i).getEstado());//Si es de aceptación se agrega su nombre al Array de EstadosAceptación
            }
        }
        return estadosA;
    }

    /**
     * Genera un automáta después del proceso de eliminar los estados extraños del autómata que invoca el método
     * @return 
     */
    public Automata eliminarEstadosExtraños() {
        Automata retorno = new Automata();
        retorno.clonarSimbolos(this);//Se clonan los símbolos  del autómata(this) en un nuevo autómata
        EstadoAutomata nuevoEstado = this.estados.get(0).clonar();
        retorno.estados.add(nuevoEstado); //Se agrega el primer estado por definición
        for (int i = 0; i < retorno.estados.size(); i++) {//Se recorren los estados de la copia
            EstadoAutomata estadoEvaluado = retorno.estados.get(i);
            for (int j = 0; j < retorno.simbolosEntrada.size(); j++) {//Se recorren los símbolos de entrada del autómata
                String simbolo = retorno.simbolosEntrada.get(j);
                ArrayList<String> salidas = estadoEvaluado.evaluarTransicion(simbolo); //Se reunen los estados siguientes de las transiciones del Estado que se evalua
                if (salidas.size() > 0) {
                    for (int k = 0; k < salidas.size(); k++) {//Se recorren los estados siguientes
                        //Se crea un nuevo estado en la copia por cada estado siguiente reunido anteriormente
                        String estadoNuevo = salidas.get(k);
                        //Se pregunta si el estado que se desea agregar ya existe para no agregarlo de nuevo
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
        //Se retorna la copia
        return retorno;
    }
    
    /**
     * Elimina los estados que tengan las mismas transiciones y aceptación
     */
    public void eliminarEstadosEquivalentes() {        
        for (int i = 0; i < this.estados.size(); i++) {//Recorre los estados
            EstadoAutomata estado1 = this.estados.get(i);
            for (int j = i + 1; j < this.estados.size(); j++) {//Recorre los demás estados
                EstadoAutomata estado2 = this.estados.get(j);
                boolean igualdad = true;
                if (estado1.getTransiciones().size() == estado2.getTransiciones().size() & estado1.isAceptacion() == estado2.isAceptacion()) {
                    //Se pregunta si los estados tienen la msma aceptación y número transiciones
                    for (int k = 0; k < estado1.getTransiciones().size(); k++) {//Se recorren las transiciones
                        Transicion transicion1 = estado1.getTransiciones().get(k);
                        Transicion transicion2 = estado2.getTransiciones().get(k);
                        //Se pregunta si las transiciones son iguales
                        if ((!transicion1.getSimbolo().equals(transicion2.getSimbolo()))
                                | (!transicion1.getTransición().equals(transicion2.getTransición()))) {
                            igualdad = false;
                            break;
                        }
                    }
                } else {
                    igualdad = false;
                }
                if (igualdad) {
                    //Si se determinó que los estados son iguales, se elimina el segundo
                    this.estados.remove(j);
                    j--;
                }
            }
        }
    }

    /**
     * Clona los simbolos de entrada de otro automata
     * @param automataAClonar Automata al cual se le desean clonar los símbolos de entrada
     */
    public void clonarSimbolos(Automata automataAClonar) {
        for (int i = 0; i < automataAClonar.simbolosEntrada.size(); i++) {
            this.simbolosEntrada.add(automataAClonar.simbolosEntrada.get(i));
        }
    }

    /**
     * Determina si un estado ya existe en el autómata
     * @param estado Nombre del estado el cual se quiere averigüar
     * @return Booleano con el resultado de la operación
     */
    public boolean existeEstado(String estado) {
        for (int i = 0; i < this.estados.size(); i++) {//Se recorren los estados
            if (estado.equals(this.estados.get(i).getEstado())) {//Se pregunta si el nombre del estado es el mismo que el del parámetro
                return true;
            }
        }
        return false;
    }

    /**
     * Imprime el automata en el model de un JTable
     * @param modelo DefaultTableModel del JTable al que se le imprimirá el autómata
     */
    public void imprimirAutomataTabla(DefaultTableModel modelo) {
        int numColumnas = this.simbolosEntrada.size() + 2;
        //Se crea un vector que se usará para imprimir cada fila en el Jtable
        Object[] fila = new Object[numColumnas];
        //Se añade una columna para el nombre de los estados
        modelo.addColumn("Estado");
        for (int j = 0; j < this.simbolosEntrada.size(); j++) {
            //Se añade una columna por cada símbolo de entrada del autómata
            modelo.addColumn(this.simbolosEntrada.get(j));
        }
        //Se añade una columna para la aceptación del estado
        modelo.addColumn("Aceptación");
        for (int i = 0; i < this.estados.size(); i++) {
            EstadoAutomata estado = this.estados.get(i);
            for (int j = 0; j < this.simbolosEntrada.size(); j++) {//Recorrido de los simbolos
                //Por casa símbolo se pregunta si tiene estados siguientes para las transiciones
                ArrayList<String> transicionesSimbolo = estado.evaluarTransicion(this.getSimbolosEntrada().get(j));
                String transicion = transicionesSimbolo.toString();
                //Se agregan los estados siguientes al vector en la posición correspondiente
                fila[j + 1] = transicion;
            }
            //Se agrega en la primera posición el nombre del estado
            fila[0] = estado.getEstado();
            //Se agrega en la última posición la aceptación del estado (1 para true y 0 para false)
            if (estado.isAceptacion()) {
                fila[numColumnas - 1] = "1";
            } else {
                fila[numColumnas - 1] = "0";
            }
            //Se añade el vector como una fila
            modelo.addRow(fila);
        }
    }

    /**
     * Genera un Autómata de un fichero tipo txt
     * @param fichero String con el contenido del fichero
     * @return Autómata que representa el texto
     */
    public Automata generarAutomataFichero(String fichero) {
        String[] vector = fichero.split("}");//Se separa cada línea de texto por el }
        Automata automata = new Automata();
        String simbolos = vector[0].substring(19);//Se ignora el texto que dice "Simbolos de entrada: "
        for (int i = 0; i < simbolos.length(); i++) {
            //Se agrega cada símbolo de entrada
            automata.agregarSimboloEntrada(simbolos.substring(i, i + 1));
            i++;
        }
        for (int i = 1; i < vector.length; i++) {//Se comienza a recorrer cada línea de texto
            String estadoCompleto = vector[i];
            estadoCompleto = estadoCompleto.replace("\n", "");
            estadoCompleto = estadoCompleto.replace("Estado", "");
            estadoCompleto = estadoCompleto.replace("{", "");
            estadoCompleto = estadoCompleto.replace("}", "");
            if (!estadoCompleto.equals("")) {//Asegurarse que no se lea una línea vacía por un salto de línea en el txt
                String estado = "";
                String auxiliar = "";
                String simboloEntrada = "";
                String transicion = "";
                EstadoAutomata estadoAutomata = new EstadoAutomata();
                for (int j = 0; j < estadoCompleto.length(); j++) {//Se recorre la línea de texto
                    if (estadoCompleto.substring(j, j + 1).equals(":")) {
                        //El Estado es de la forma NombreEstado: Simbolo>EstadoSiguiente -* Aceptación
                        //Si hay un : es porque se comenzarán a recorrer las transiciones del estado
                        //Por tanto, guardamos lo anterior al : como el nombre del estado
                        estado = auxiliar;
                        estadoAutomata.setEstado(estado);
                        auxiliar = "";
                        j++;

                    }
                    if (estadoCompleto.substring(j, j + 1).equals("=")) {
                        //Las transiciones son de la forma simbolo>estadoSiguiente
                        //Si hay un = es porque se leerá el estado siguiente después de un símbolo
                        simboloEntrada = auxiliar;
                        auxiliar = "";
                        j++;

                    }
                    if (estadoCompleto.substring(j, j + 1).equals("-")) {
                        //Si hay un - es porque se acabó de leer una transicón, débido a que se concordó que cada transición acaba con -
                        //Por tanto, se agrega la transición al estado
                        transicion = auxiliar;
                        estadoAutomata.agregarTransicion(simboloEntrada, transicion);
                        auxiliar = "";
                        j++;
                    }
                    if (estadoCompleto.substring(j, j + 1).equals("*")) {
                        //Si hay un * es pq se terminaron las transiciones y ya solo queda la aceptación del estado
                        //Si lo que sigue es una t es pq la palabra siguiente es true, por tanto, se pone true en la aceptación
                        if (estadoCompleto.substring(j + 1, j + 2).equals("t")) {
                            estadoAutomata.setAceptacion(true);
                        } else {//De lo contrario, se pone false en la aceptación
                            estadoAutomata.setAceptacion(false);
                        }
                        automata.estados.add(estadoAutomata);
                        j = 1000000;
                        break; //se deja de leer el resto del estring
                    }
                    auxiliar = auxiliar + estadoCompleto.substring(j, j + 1);

                }
            }
        }
        return automata;
    }

}

package gramautoteoria.Modelo;

import java.util.ArrayList;


public class Gramatica {
    
    private ArrayList<Produccion> producciones;

    public Gramatica() {
        ArrayList<Produccion> produccionesG = new ArrayList<Produccion>();
        this.producciones = produccionesG;
    }

    public Gramatica(ArrayList<Produccion> producciones) {
        this.producciones = producciones;
    }

    public ArrayList<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(ArrayList<Produccion> producciones) {
        this.producciones = producciones;
    }
    
    /**
     * Imprime un String con la gramática 
     * @return String que representa la gramática
     */
    public String imprimirGramatica(){
        String resultado = "";
        for(int i = 0; i < this.producciones.size();i++){//Recorro producciones
            Produccion produccion = (Produccion) this.producciones.get(i);
            if(i ==0){
                //A la primera producción no le genero salto de línea
                resultado = Integer.toString(i+1)+". "+produccion.imprimirProduccion();
            }
            else{
                resultado = resultado+"\n"+Integer.toString(i+1)+". "+produccion.imprimirProduccion();
            }
            
        }        
        return resultado;
    }
    
    /**
     * Agrega una producción a la gramática
     * @param izquierda String con el NT que representa la izquierda de la producción
     * @param derecha String con la parte derecha de la producción
     */
    public void agregarProduccion(String izquierda, String derecha){
        Produccion produccionNueva = new Produccion(izquierda,derecha);
        if(this.existeProduccion(produccionNueva)){
            return;
        }
        this.getProducciones().add(produccionNueva);
    }
    
    /**
     * Determina si ya existe una producción en la gramática
     * @param produccion Producción con la cual se desea comparar
     * @return Boolean con el resultado
     */
    public boolean existeProduccion(Produccion produccion){
        for(int i=0;i<this.producciones.size();i++){//Recorren las producciones
            if(this.producciones.get(i).getIzquierdo().equals(produccion.getIzquierdo())&//Se pregunta si son iguales
                    this.producciones.get(i).getDerecha().equals(produccion.getDerecha())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retorna un Array de String con los NT vivos
     * @return ArrayList de String con los NT vivos
     */
    public ArrayList<String> noTerminalesVivos(){
        ArrayList<String> NTV = new ArrayList<>();
        int m = 0;
        boolean cambio = true;
        while (cambio == true) {
            cambio = false;
            for (int i = 0; i < this.producciones.size(); i++) {
                Produccion prod = this.producciones.get(i);
                if (this.producciones.get(i).soloNoTerminales(NTV)) {
                    if(!NTV.contains(this.producciones.get(i).getIzquierdo())){
                        NTV.add(this.producciones.get(i).getIzquierdo());
                    }
                }
            }
            if (m != NTV.size()) {
                cambio = true;
            }
            m = NTV.size();
        }
        return NTV;
    }
    
    /**
     * Retorna un Array con los NT muertos
     * @return ArrayList String con los NT muertos
     */
    public ArrayList<String> noTerminalesMuertos(){
        ArrayList<String> NTV = this.noTerminalesVivos();
        ArrayList<String> NTM = new ArrayList<>();
        for(int i =0; i<this.producciones.size();i++){
            if(!NTV.contains(this.producciones.get(i).getIzquierdo()) & !NTM.contains(this.producciones.get(i).getIzquierdo())){
                NTM.add(this.producciones.get(i).getIzquierdo());
            }
        }
        return NTM;
    }
    
    /**
     * Genera una gramática copia del this después de haber eliminado los NTM
     * @return Gramática sin los NTM del this
     */
    public Gramatica eliminarNTM(){
        ArrayList<String> NTV = this.noTerminalesVivos();
        ArrayList<String> NTM = this.noTerminalesMuertos();
        if(NTM.size() == 0){
            return this;
        }
        Gramatica resultado = new Gramatica();
        for(int i =0; i<this.producciones.size();i++){
            if(NTV.contains(this.producciones.get(i).getIzquierdo())){
                Produccion copia = (Produccion) this.getProducciones().get(i);
                String derecho = copia.getDerecha();
                for(int j = 0; j < NTM.size(); j++){
                    if(!derecho.contains(NTM.get(j))){
                        resultado.agregarProduccion(copia.getIzquierdo(), derecho);
                    }                    
                }               
            }
        }
        return resultado;
    }
    
    /**
     * Retorna un Array con los NT alcanzables
     * @return ArrayList String con lso NT alcanzables
     */
    public ArrayList<String> noTerminalesAlcanzables(){
        ArrayList<String> NTA = new ArrayList<>();
        NTA.add(this.producciones.get(0).getIzquierdo());
        int m = 1;
        boolean cambio = true;
        while (cambio == true) {
            cambio = false;
            for (int i = 0; i < this.producciones.size(); i++) {
                Produccion prod = this.producciones.get(i);
                if (NTA.contains(prod.getIzquierdo())) {
                    prod.agregarNoTerminales(NTA);
                }
            }
            if (m != NTA.size()) {
                cambio = true;
            }
            m = NTA.size();
        }
        return NTA;
    }
    
    /**
     * Retorna Array con los NT inalcanzables
     * @return ArrayList String con los NT inalcanzables
     */
    public ArrayList<String> noTerminalesInalcanzables(){
        ArrayList<String> NTA = this.noTerminalesAlcanzables();
        ArrayList<String> NTI = new ArrayList<>();
        for(int i =0; i<this.producciones.size();i++){
            if(!NTA.contains(this.producciones.get(i).getIzquierdo()) & !NTI.contains(this.producciones.get(i).getIzquierdo())){
                NTI.add(this.producciones.get(i).getIzquierdo());
            }
        }
        return NTI;
    }
    
     /**
     * Genera una gramática copia del this después de haber eliminado los NTI
     * @return Gramática sin los NTI del this
     */
    public Gramatica eliminarNTI(){
        ArrayList<String> NTA = this.noTerminalesAlcanzables();
        ArrayList<String> NTI = this.noTerminalesInalcanzables();
        Gramatica resultado = new Gramatica();
        if(NTI.size() == 0){
            return this;
        }
        for(int i =0; i<this.producciones.size();i++){
            if(NTA.contains(this.producciones.get(i).getIzquierdo())){
                Produccion copia = (Produccion) this.getProducciones().get(i);
                String derecho = copia.getDerecha();
                for(int j = 0; j < NTI.size(); j++){
                    if(!derecho.contains(NTI.get(j))){
                        resultado.agregarProduccion(copia.getIzquierdo(), derecho);
                    }                    
                }               
            }
        }
        return resultado;
    }
    
    /**
     * Genera una gramática copia del this después de haber eliminado los NTM y los NTI
     * @return Gramática sin los NTM ni los NTI del this
     */
    public Gramatica simplificar(){
        Gramatica aux = this.eliminarNTM();
        Gramatica resultado = aux.eliminarNTI();
        return resultado;
    }
    
    /**
     * Determina si la gramática se puede simplificar
     * @return Boolean con la respuesta
     */
    public boolean esSimplificable(){
        ArrayList<String> NTM = this.noTerminalesMuertos();
        ArrayList<String> NTI = this.noTerminalesInalcanzables();
        return NTM.size()>0 | NTI.size()>0;
    }
    
    /**
     * Modifica una producción de la gramática
     * @param numero Entero con la posición de la producción que se desea modificar
     * @param lado String con el lado que se desea modificar de la gramática (derecho ó izquierdo)
     * @param cambio String con el cambio que se desea realizar
     */
    public void modificarGramatica(int numero, String lado, String cambio){
        Produccion nuevo = this.producciones.get(numero-1);
        if(lado.equalsIgnoreCase("derecho")){
            nuevo.setDerecha(cambio);
        }
        else{
            nuevo.setIzquierdo(cambio);
        }
        this.producciones.set(numero-1, nuevo);
    }
    
    /**
     * Elimina una producción de la gramática
     * @param numero Entero con la posición de la gramática que se desea eliminar
     */
    public void eliminarProduccion(int numero){
        this.producciones.remove(numero-1);
    }
    
    /**
     * Determina si una gramática es Regular
     * @return Booleano con la respuesta
     */
    public boolean esRegular(){
        for(int i=0;i<this.producciones.size();i++){
            if(!this.producciones.get(i).esLinealDerecha()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Reinicia las producciones de una gramática 
     */
    public void reiniciarGramatica(){
        ArrayList<Produccion> nuevo = new ArrayList<>();
        this.setProducciones(nuevo);
    }
    
    /**
     * Genera una gramática de un texto de un fichero
     * @param fichero String con el texto del fichero
     */
    public void generarGramaticaFichero(String fichero) {
        String[] lineas = fichero.split("\n"); //Se lee linea por línea con un vector 
        String izquierda = "";
        String derecha = "";
        for (int i = 0; i < lineas.length; i++) {//Se recorre cada producción
            String linea = lineas[i];
            for (int j = 0; j < linea.length(); j++) {//Se recorre cada caracter de la producción
                String sub = linea.substring(j, j + 1);
                if (linea.substring(j, j + 1).equals("<") & izquierda.equals("")) {
                    while (!linea.substring(j, j + 1).equals(">")) {
                        //Se va capturando el NT de la parte izquierda 
                        sub = linea.substring(j, j + 1);
                        izquierda = izquierda + linea.substring(j, j + 1);
                        j++;
                    }
                    sub = linea.substring(j, j + 1);
                    izquierda = izquierda + linea.substring(j, j + 1);
                    j++;
                }
                sub = linea.substring(j, j + 1);
                if (linea.substring(j, j + 1).equals(">") & !izquierda.equals("")) {
                    //Se agrega el la producción a la gramática
                    derecha = linea.substring(j + 1);
                    j = linea.length() + 1;
                    this.agregarProduccion(izquierda, derecha);
                    izquierda = "";
                    derecha = "";
                }

            }
        }
    }
    
    /**
     * Genera un autómata de la gramática
     * @return Automata que representa a la gramática
     */
    public Automata gramaticaAAutomata(){
        Automata automata = new Automata();
        for(int i=0; i<this.producciones.size();i++){
            Produccion prod = this.producciones.get(i);
            String estado = prod.getIzquierdo();
            if(prod.getDerecha().equals("")){
                //Si la derivada de un NT es vacio es pq es de aceptación en el automata el estado correspondiente
                int pos = automata.retonarPosEstado(estado);
                if(pos != -1){
                    
                    automata.estados.get(pos).setAceptacion(true);
                }
                else{
                    automata.agregarEstados(estado, true);
                }
            }
            else{
            String derecha = prod.getDerecha();
            String simbolo = derecha.substring(0,1);
            String transicion = derecha.substring(1);
            automata.agregarTransicionAutomata(estado, simbolo, transicion);
            }
            
        }
        return automata;
    }
    
    /**
     * Quita los espacios de todas las producciones
     */
    public void quitarEspacios(){
        for(int i=0;i<this.producciones.size();i++){
            String derecho = this.producciones.get(i).getDerecha();
            String izquierda = this.producciones.get(i).getIzquierdo();
            derecho = derecho.replace(" ", "");
            derecho = derecho.replace("\r", "");
            izquierda = izquierda.replace(" ", "");
            izquierda = izquierda.replace("\r", "");
            this.producciones.get(i).setDerecha(derecho);
            this.producciones.get(i).setIzquierdo(izquierda);
        }
    }
    
    /**
     * Convierte una gramatica a Lineal por la dereca
     * @return Una copia de la grmática después de la conversión
     */
    public Gramatica convertirLinealDerecha() {
        Gramatica LD = new Gramatica();
        if (!this.esRegular()) {
            //se pregunta si la gramatica ya no es LD
            return LD;
        }
        //Se genera una variable para saber si toca agregar la producción <nulo> al final
        boolean nulo = false;
        for (int i = 0; i < this.producciones.size(); i++) {//Se recorren las producciones
            Produccion produccion = this.producciones.get(i);
            if (produccion.esEspecial()) {//Si la Producción no necesita conversión se agrega a la nueva gramática
                LD.agregarProduccion(produccion.getIzquierdo(), produccion.getDerecha());
            } else {
                if (produccion.getDerecha().length() == 1) {//Si la derivada de la producción es solo un simbolo
                    LD.agregarProduccion(produccion.getIzquierdo(), produccion.getDerecha() + "<nulo>");
                    //Se modifica nulo para saber que hay que agregar el estado nulo después
                    nulo = true;

                } else {//Si después del primer simbolo sigue otro simbolo se realiza la conversión simbolo1<Simbolo2NT>
                    if (!produccion.getDerecha().substring(0, 1).equals("<")) {
                        String textoDerecha = produccion.getDerecha();
                        String simbolo = "";
                        String NT = "";
                        int k = 0;
                        for(int h = 0;h<produccion.getDerecha().length();h++){
                            k++;
                            if(produccion.getDerecha().substring(h, h+1).equals("<") ){
                            
                            break;
                        }
                        }
                        
                        textoDerecha = textoDerecha.replace("<", "");
                        textoDerecha = textoDerecha.replace(">", "");
                        int j = 1;
                        simbolo = textoDerecha.substring(0, 1);
                        NT = textoDerecha.substring(1);
                        LD.agregarProduccion(produccion.getIzquierdo(), simbolo+"<"+NT+">");
                        textoDerecha = textoDerecha.substring(1);
                        while(j<k-1){
                            simbolo = textoDerecha.substring(0, 1);
                            NT = textoDerecha.substring(1);
                            LD.agregarProduccion("<"+textoDerecha+">",simbolo+"<"+NT+">");
                            textoDerecha = textoDerecha.substring(1);
                            j++;
                        }
                    } 
                }

            }

        }
        //Se buscan producciones de la forma <NT1> --> <NT2>
        for(int i=0;i<this.producciones.size();i++){
            Produccion produccion = this.producciones.get(i);
            if(produccion.getDerecha().length() == 0){
                
            }
            else{
                if(produccion.getDerecha().substring(0, 1).equals("<")){
                //Cuando se encuentra, a la parte de la izquierda de esa producción se le adhieren todas las derivadas del NT de la derecha
                ArrayList<String> derechos = LD.retornarDerechos(produccion.getDerecha());//Se buscan todas las derivadas
                for(int j=0;j<derechos.size();j++){
                    LD.agregarProduccion(produccion.getIzquierdo(), derechos.get(j));
                }
            }
            }
            
        }
        if (nulo) {
            LD.agregarProduccion("<nulo>", "");
        }
        return LD;
    }
    
    /**
     * Retorna todas las derivaciones de un NT
     * @param NT String con el NT al que se le desea derivar
     * @return ArrayList String con todas las derivaciones del terminar
     */
    public ArrayList<String> retornarDerechos(String NT){
        ArrayList<String> derechos = new ArrayList<>();
        for(int i=0;i<this.producciones.size();i++){//Recorre las producciones
            Produccion produccion = this.producciones.get(i);
            if(produccion.getIzquierdo().equals(NT)){//Si la parte de la izquierda es igual a la del parametro se agrega
                derechos.add(produccion.getDerecha());
            }
        }
        return derechos;
    }
}

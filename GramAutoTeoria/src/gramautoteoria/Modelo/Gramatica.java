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
    
    public String imprimirGramatica(){
        String resultado = "";
        for(int i = 0; i < this.producciones.size();i++){
            Produccion produccion = (Produccion) this.producciones.get(i);
            if(i ==0){
                resultado = Integer.toString(i+1)+". "+produccion.imprimirProduccion();
            }
            else{
                resultado = resultado+"\n"+Integer.toString(i+1)+". "+produccion.imprimirProduccion();
            }
            
        }        
        return resultado;
    }
    
    public void agregarProduccion(String izquierda, String derecha){
        Produccion produccionNueva = new Produccion(izquierda,derecha);
        this.getProducciones().add(produccionNueva);
    }
    
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
    
    public Gramatica simplificar(){
        Gramatica aux = this.eliminarNTM();
        Gramatica resultado = aux.eliminarNTI();
        return resultado;
    }
    
    public boolean esSimplificable(){
        ArrayList<String> NTM = this.noTerminalesMuertos();
        ArrayList<String> NTI = this.noTerminalesInalcanzables();
        return NTM.size()>0 | NTI.size()>0;
    }
    
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
    
    public void eliminarProduccion(int numero){
        this.producciones.remove(numero-1);
    }
    
    public boolean esRegular(){
        for(int i=0;i<this.producciones.size();i++){
            if(!this.producciones.get(i).esLinealDerecha()){
                return false;
            }
        }
        return true;
    }
    
       
    public void reiniciarGramatica(){
        ArrayList<Produccion> nuevo = new ArrayList<>();
        this.setProducciones(nuevo);
    }
    
    public void generarGramaticaFichero(String fichero) {
        String[] lineas = fichero.split("\n");
        String izquierda = "";
        String derecha = "";
        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i];
            for (int j = 0; j < linea.length(); j++) {
                String sub = linea.substring(j, j + 1);
                if (linea.substring(j, j + 1).equals("<") & izquierda.equals("")) {
                    while (!linea.substring(j, j + 1).equals(">")) {
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
                    derecha = linea.substring(j + 1);
                    j = linea.length() + 1;
                    this.agregarProduccion(izquierda, derecha);
                    izquierda = "";
                    derecha = "";
                }

            }
        }
    }
    
    public Automata gramaticaAAutomata(){
        Automata automata = new Automata();
        for(int i=0; i<this.producciones.size();i++){
            Produccion prod = this.producciones.get(i);
            String estado = prod.getIzquierdo();
            if(prod.getDerecha().equals("")){
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
    
    public Gramatica convertirLinealDerecha() {
        Gramatica LD = new Gramatica();
        if (!this.esRegular()) {
            return LD;
        }
        boolean nulo = false;
        for (int i = 0; i < this.producciones.size(); i++) {
            Produccion produccion = this.producciones.get(i);
            if (produccion.esEspecial()) {
                LD.agregarProduccion(produccion.getIzquierdo(), produccion.getDerecha());
            } else {
                if (produccion.getDerecha().length() == 1) {
                    LD.agregarProduccion(produccion.getIzquierdo(), produccion.getDerecha() + "<nulo>");
                    nulo = true;

                } else {
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
        for(int i=0;i<this.producciones.size();i++){
            Produccion produccion = this.producciones.get(i);
            if(produccion.getDerecha().length() == 0){
                
            }
            else{
                if(produccion.getDerecha().substring(0, 1).equals("<")){
                ArrayList<String> derechos = LD.retornarDerechos(produccion.getDerecha());
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
    
    public ArrayList<String> retornarDerechos(String NT){
        ArrayList<String> derechos = new ArrayList<>();
        for(int i=0;i<this.producciones.size();i++){
            Produccion produccion = this.producciones.get(i);
            if(produccion.getIzquierdo().equals(NT)){
                derechos.add(produccion.getDerecha());
            }
        }
        return derechos;
    }
}

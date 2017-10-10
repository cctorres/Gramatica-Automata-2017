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
            resultado = resultado+"\n"+produccion.imprimirProduccion();
        }        
        return resultado;
    }
    
    public void agregarProduccion(String produccion, String destino){
        Produccion produccionNueva = new Produccion(produccion,destino);
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
}

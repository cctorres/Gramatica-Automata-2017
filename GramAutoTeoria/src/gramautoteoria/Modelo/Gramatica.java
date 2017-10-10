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
    
    public ArrayList<String> NoTerminalesVivos(){
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
    
    public ArrayList<String> NoTerminalesMuertos(){
        ArrayList<String> NTV = this.NoTerminalesVivos();
        ArrayList<String> NTM = new ArrayList<>();
        for(int i =0; i<this.producciones.size();i++){
            if(!NTV.contains(this.producciones.get(i).getIzquierdo()) & !NTM.contains(this.producciones.get(i).getIzquierdo())){
                NTM.add(this.producciones.get(i).getIzquierdo());
            }
        }
        return NTM;
    }
    
    public Gramatica eliminarNTM(){
        ArrayList<String> NTV = this.NoTerminalesVivos();
        ArrayList<String> NTM = this.NoTerminalesMuertos();
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
    
    
}

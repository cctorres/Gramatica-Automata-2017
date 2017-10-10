
package gramautoteoria.Modelo;

import java.util.ArrayList;


public class Produccion {
    
    private String izquierdo;
    private String derecha;

    public Produccion() {
    }

    public Produccion(String izquierdo, String derecha) {
        this.izquierdo = izquierdo;
        this.derecha = derecha;
    }

    public String getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(String izquierdo) {
        this.izquierdo = izquierdo;
    }

    public String getDerecha() {
        return derecha;
    }

    public void setDerecha(String derecha) {
        this.derecha = derecha;
    }    
    
    public String imprimirProduccion(){
        String resultado = this.getIzquierdo()+" --> "+this.getDerecha();
        return resultado;
    }
    
    public boolean soloNoTerminales(ArrayList<String> NTV){
        String derecho = this.derecha;
        for(int i = 0; i < derecho.length(); i++){
            if(derecho.substring(i, i+1).equals("<")){
                String noTerminal = "";
                while(!derecho.substring(i, i+1).equals(">")){
                    noTerminal = noTerminal+derecho.substring(i, i+1);
                    i++;
                }
                noTerminal = noTerminal+">";
                if(!NTV.contains(noTerminal)){
                    return false;
                }
            }
        }
        return true;
    }
}

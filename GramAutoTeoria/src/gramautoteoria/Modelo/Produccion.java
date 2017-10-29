
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
    
    /**
     * Imprime la producción en un String
     * @return String con la impresión de la producción
     */
    public String imprimirProduccion(){
        String resultado = this.getIzquierdo()+" --> "+this.getDerecha();
        return resultado;
    }
    
    /**
     * Determina si una producción solo posee los NT que están en el Array 
     * @param NTV Array con los NT que se quieren comparar
     * @return 
     */
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
    
    /**
     * Agrega los NT que tenga la producción al Array enviado por parametro  
     * @param NTA 
     */
    public void agregarNoTerminales(ArrayList<String> NTA){
        String derecho = this.derecha;
        for(int i = 0; i < derecho.length();i++){
            if(derecho.substring(i, i+1).equals("<")){
                String noTerminal = "";
                while(!derecho.substring(i, i+1).equals(">")){
                    noTerminal = noTerminal+derecho.substring(i, i+1);
                    i++;
                }
                noTerminal = noTerminal+">";
                //Agregar el NT sólo si no está en el Array
                if(!NTA.contains(noTerminal)){
                  NTA.add(noTerminal);  
                }
                
            }
        }
    }
    
    /**
     * Determina si una producción es lineal por la derecha
     * @return Booleano con la respuesta
     */
    public boolean esLinealDerecha(){
        String derecho = this.derecha;
        if(derecho.equals("")){//Si su derivada es el string vacio cumple
            return true;
        }
        for(int i = 0; i < derecho.length(); i++){
            if(derecho.substring(i, i+1).equals("<")){
                while(!derecho.substring(i, i+1).equals(">")){                    
                    i++;
                }                
                String aux = derecho.substring(i+1);
                //Si después de haber encontrado el primer NT hay más caracteres no es Lineal por la derecha
                if(aux.length()>0){
                    return false;
                }
            }
        }        
        return true;
    }

    /**
     * Determina si una producción es de la forma especial
     * @return Booleano con la respuesta
     */
    boolean esEspecial() {
        String derecho = this.derecha;
        if (derecho.equals("")) {
            //Si la parte de la derivada es vacia es Especial
            return true;
        }
        if(derecho.length()<2){
            //Si la parte de la derivada posee solamente un simbolo pero no un NT no es especial
            return false;
        }
        //Me salto el primer caracter de la parte derecha pues es un simbolo
        for (int i = 1; i < derecho.length(); i++) {
            if (!derecho.substring(i, i + 1).equals("<")) {
                //Si después de simbolo inicial no empieza un NT no es de la forma especial
                return false;
            }
            if (derecho.substring(i, i + 1).equals("<")) {
                while (!derecho.substring(i, i + 1).equals(">")) {
                    //Recorro la parte derecha hasta hallar un > que me indica que se acaba de leer un NT
                    i++;
                }
                //Si después de leer el NT hay más caracteres no es de la forma especial
                String aux = derecho.substring(i + 1);
                if (aux.length() > 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

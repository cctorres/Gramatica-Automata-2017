
package gramautoteoria.Modelo;


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
}

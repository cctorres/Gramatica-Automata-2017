package gramautoteoria.Modelo;


public class Transicion {
    
    private String simbolo;
    private String transición;

    public Transicion() {
    }

    public Transicion(String simbolo, String transición) {
        this.simbolo = simbolo;
        this.transición = transición;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTransición() {
        return transición;
    }

    public void setTransición(String transición) {
        this.transición = transición;
    }
    
    
    
}

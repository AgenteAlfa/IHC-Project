package com.grupo2.proteam.FStore;

public class Premio {
    private String nombre;
    private int puntaje;

    public Premio() {
    }

    public Premio(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }
}

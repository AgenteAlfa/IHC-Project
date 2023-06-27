package com.grupo2.proteam.FStore;

public class Puntaje {
    private int puntaje;
    private String nya;
    private String id;

    public Puntaje() {
    }

    public Puntaje(int puntaje, String nya, String id) {
        this.puntaje = puntaje;
        this.nya = nya;
        this.id = id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getId() {
        return id;
    }

    public String getNya() {
        return nya;
    }
}

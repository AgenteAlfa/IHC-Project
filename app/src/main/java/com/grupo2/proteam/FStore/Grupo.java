package com.grupo2.proteam.FStore;

import java.util.List;

public class Grupo {
    private String nombre;
    private String descripcion;
    private List<String> colaboradores;
    private List<String> supervisores;

    public Grupo(){}

    public Grupo(String nombre, String descripcion, List<String> colaboradores, List<String> supervisores) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colaboradores = colaboradores;
        this.supervisores = supervisores;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getColaboradores() {
        return colaboradores;
    }

    public List<String> getSupervisores() {
        return supervisores;
    }
}

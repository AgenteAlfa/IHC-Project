package com.grupo2.proteam.FStore;

import java.util.List;

public class Equipo {
    private String propietario;
    private String nombre;
    private String descripcion;
    private String normativa;
    private List<String> colaboradores;
    public Equipo(){}

    public Equipo(String propietario, String nombre, String descripcion, String normativa, List<String> colaboradores) {
        this.propietario = propietario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.normativa = normativa;
        this.colaboradores = colaboradores;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNormativa() {
        return normativa;
    }

    public List<String> getColaboradores() {
        return colaboradores;
    }
}

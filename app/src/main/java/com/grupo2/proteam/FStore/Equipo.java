package com.grupo2.proteam.FStore;

import java.util.List;

public class Equipo {
    private String Propietario;
    private String Nombre;
    private String Descripcion;
    private String Normativa;
    private List<String> Colaboradores;
    public Equipo(){}

    public Equipo(String propietario, String nombre, String descripcion, String normativa, List<String> colaboradores) {
        Propietario = propietario;
        Nombre = nombre;
        Descripcion = descripcion;
        Normativa = normativa;
        Colaboradores = colaboradores;
    }

    public String getPropietario() {
        return Propietario;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getNormativa() {
        return Normativa;
    }

    public List<String> getColaboradores() {
        return Colaboradores;
    }
}

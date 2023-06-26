package com.grupo2.proteam.FStore;

import java.util.Date;
import java.util.List;

public class Mision {
    private List<String> colaboradores;
    private Date completado;
    private String descripcion;
    private int puntaje;
    private boolean tipo;
    private String titulo;

    public Mision() {
    }

    public Mision(List<String> colaboradores, Date completado, String descripcion, int puntaje, boolean tipo, String titulo) {
        this.colaboradores = colaboradores;
        this.completado = completado;
        this.descripcion = descripcion;
        this.puntaje = puntaje;
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public List<String> getColaboradores() {
        return colaboradores;
    }

    public Date getCompletado() {
        return completado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean isTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setCompletado(Date completado) {
        this.completado = completado;
    }
}

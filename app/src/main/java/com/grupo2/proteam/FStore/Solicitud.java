package com.grupo2.proteam.FStore;

import java.util.Date;

public class Solicitud {
    private Date fecha;
    private String id;

    public Solicitud() {
    }

    public Solicitud(Date fecha, String id) {
        this.fecha = fecha;
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getID() {
        return id;
    }
}

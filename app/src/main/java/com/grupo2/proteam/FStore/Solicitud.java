package com.grupo2.proteam.FStore;

import java.util.Date;

public class Solicitud {
    private Date fecha;
    private String ID;

    public Solicitud() {
    }

    public Solicitud(Date fecha, String ID) {
        this.fecha = fecha;
        this.ID = ID;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getID() {
        return ID;
    }
}

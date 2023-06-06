package com.grupo2.proteam.FStore;


import java.util.Date;

public class PrivadoUsuario{
    private boolean admin;
    private Date fnacimiento;
    private String nyA;
    public PrivadoUsuario() {}
    public PrivadoUsuario(boolean isAdmin, Date fNacimiento, String nyA) {
        this.admin = isAdmin;
        this.fnacimiento = fNacimiento;
        this.nyA = nyA;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Date getfnacimiento() {
        return fnacimiento;
    }

    public String getNyA() {
        return nyA;
    }
}

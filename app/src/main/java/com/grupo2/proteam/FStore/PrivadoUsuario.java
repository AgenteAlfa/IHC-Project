package com.grupo2.proteam.FStore;


import java.util.Date;

public class PrivadoUsuario{
    private boolean isAdmin;
    private Date FNacimiento;
    private String NyA;
    public PrivadoUsuario() {}
    public PrivadoUsuario(boolean isAdmin, Date FNacimiento, String nyA) {
        this.isAdmin = isAdmin;
        this.FNacimiento = FNacimiento;
        NyA = nyA;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Date getFNacimiento() {
        return FNacimiento;
    }

    public String getNyA() {
        return NyA;
    }
}

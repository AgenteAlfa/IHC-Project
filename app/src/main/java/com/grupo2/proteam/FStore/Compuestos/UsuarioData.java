package com.grupo2.proteam.FStore.Compuestos;

import android.util.Log;

import androidx.annotation.Nullable;

import com.grupo2.proteam.FStore.PrivadoUsuario;

public class UsuarioData extends PrivadoUsuario {
    String ID;
    boolean isSupervisor;

    public UsuarioData(PrivadoUsuario usuario, String ID, boolean isSupervisor) {
        super(usuario.isAdmin(), usuario.getfnacimiento(), usuario.getNyA());
        this.ID = ID;
        this.isSupervisor = isSupervisor;
    }

    public String getID() {
        return ID;
    }

    public boolean isSupervisor() {
        return isSupervisor;
    }

    public void setSupervisor(boolean supervisor) {
        isSupervisor = supervisor;
    }

}

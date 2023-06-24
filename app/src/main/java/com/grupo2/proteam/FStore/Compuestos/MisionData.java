package com.grupo2.proteam.FStore.Compuestos;

import com.grupo2.proteam.FStore.Mision;

import java.util.Date;
import java.util.List;

public class MisionData extends Mision {
    String ID;
    String Status;
    List<UsuarioData> Integrantes;

    public MisionData(Mision mision, String ID, String status, List<UsuarioData> integrantes) {
        super(mision.getColaboradores(), mision.getCompletado(), mision.getDescripcion(), mision.getPuntaje(), mision.isTipo(), mision.getTitulo());
        this.ID = ID;
        this.Status = status;
        this.Integrantes = integrantes;
    }

    public String getID() {
        return ID;
    }

    public String getStatus() {
        return Status;
    }

    public List<UsuarioData> getIntegrantes() {
        return Integrantes;
    }
}

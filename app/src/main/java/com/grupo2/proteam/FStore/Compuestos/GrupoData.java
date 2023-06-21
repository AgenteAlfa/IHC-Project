package com.grupo2.proteam.FStore.Compuestos;

import com.grupo2.proteam.FStore.Grupo;

public class GrupoData extends Grupo {
    String ID;

    public GrupoData(Grupo G, String ID) {
        super(G.getNombre(), G.getDescripcion(), G.getColaboradores(), G.getSupervisores());
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}

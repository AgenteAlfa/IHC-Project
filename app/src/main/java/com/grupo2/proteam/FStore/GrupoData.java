package com.grupo2.proteam.FStore;

public class GrupoData extends Grupo{
    String ID;

    public GrupoData(Grupo G, String ID) {
        super(G.getNombre(), G.getDescripcion(), G.getColaboradores(), G.getSupervisores());
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}

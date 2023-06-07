package com.grupo2.proteam.FStore;

import java.util.List;

public class EquipoData extends Equipo{
    String ID;

    public EquipoData(Equipo E, String ID) {
        super(E.getPropietario(), E.getNombre(), E.getDescripcion(), E.getNormativa(), E.getColaboradores());
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}

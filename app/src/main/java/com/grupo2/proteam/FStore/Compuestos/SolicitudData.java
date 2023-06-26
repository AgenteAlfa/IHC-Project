package com.grupo2.proteam.FStore.Compuestos;

import com.grupo2.proteam.FStore.Solicitud;

public class SolicitudData extends Solicitud {
    private final MisionData Mision;

    public SolicitudData(Solicitud solicitud, MisionData mision) {
        super(solicitud.getFecha(), solicitud.getID());
        Mision = mision;
    }

    public MisionData getMision() {
        return Mision;
    }
}

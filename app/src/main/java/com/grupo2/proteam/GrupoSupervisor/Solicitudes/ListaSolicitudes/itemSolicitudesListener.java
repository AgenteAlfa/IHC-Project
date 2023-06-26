package com.grupo2.proteam.GrupoSupervisor.Solicitudes.ListaSolicitudes;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.SolicitudData;

public interface itemSolicitudesListener {
    void onClickAceptar(SolicitudData M);
    void onClickDescartar(SolicitudData M);
}

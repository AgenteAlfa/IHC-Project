package com.grupo2.proteam.ui.Sala.Equipos.ListaEquipos;

import com.grupo2.proteam.FStore.EquipoData;

public interface itemEquipoListener {
    void onClickInfo(EquipoData e);
    void onClickEntrar(EquipoData e);
    void onClickAgregar();
}
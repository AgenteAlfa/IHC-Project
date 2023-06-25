package com.grupo2.proteam.GrupoSupervisor.Misiones.ListaColaboradoresMision;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;

public interface itemColaboradoresMisionListener {
    void Check(UsuarioData colaborador, boolean isCheck);
}

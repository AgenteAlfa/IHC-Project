package com.grupo2.proteam.CrearGrupo.Listas.ColaboradoresSelectos;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.FStore.PrivadoUsuario;

public interface itemColaboradoresSelectosListener {
    void Supervisor(UsuarioData colaborador, boolean isSupervisor);
    void Eliminar(UsuarioData colaborador);
}

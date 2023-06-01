package com.grupo2.proteam;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo2.proteam.FStore.PrivadoUsuario;

public class EquiposViewModel extends ViewModel {
    private PrivadoUsuario UsuarioInfo = null;

    public PrivadoUsuario getUsuarioInfo() {
        return UsuarioInfo;
    }

    public void setUsuarioInfo(PrivadoUsuario usuarioInfo) {
        UsuarioInfo = usuarioInfo;
    }
}

package com.grupo2.proteam.ui.Equipos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo2.proteam.FStore.Equipo;

import java.util.List;

public class EquiposFrgViewModel extends ViewModel {


    private final MutableLiveData<List<Equipo>> lstEquipos;


    public EquiposFrgViewModel() {
        lstEquipos = new MutableLiveData<>(null);
    }

    public MutableLiveData<List<Equipo>> getLstEquipos() {
        return lstEquipos;
    }
}
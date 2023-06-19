package com.grupo2.proteam.Registro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Date;

public class RegistroViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isAdmin;
    private final MutableLiveData<String> Nombre;
    private final MutableLiveData<Date> Fecha;

    public RegistroViewModel()
    {
        isAdmin = new MutableLiveData<>(Boolean.FALSE);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        Nombre = new MutableLiveData<>(user.getDisplayName() != null? user.getDisplayName() : "");
        Fecha = new MutableLiveData<>(new Date());
    }

    public MutableLiveData<Boolean> getIsAdmin() {
        return isAdmin;
    }

    public MutableLiveData<String> getNombre() {
        return Nombre;
    }

    public MutableLiveData<Date> getFecha() {
        return Fecha;
    }
}

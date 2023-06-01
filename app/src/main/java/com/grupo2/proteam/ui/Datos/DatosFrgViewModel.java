package com.grupo2.proteam.ui.Datos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DatosFrgViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DatosFrgViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
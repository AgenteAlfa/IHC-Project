package com.grupo2.proteam.ui.Configuracion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfiguracionFrgViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConfiguracionFrgViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
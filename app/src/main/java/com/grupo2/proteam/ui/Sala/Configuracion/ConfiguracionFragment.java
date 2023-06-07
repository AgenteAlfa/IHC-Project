package com.grupo2.proteam.ui.Sala.Configuracion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.grupo2.proteam.R;
import com.grupo2.proteam.databinding.FragmentConfiguracionBinding;


public class ConfiguracionFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_configuracion, container, false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
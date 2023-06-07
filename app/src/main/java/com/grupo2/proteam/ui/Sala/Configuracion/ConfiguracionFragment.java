package com.grupo2.proteam.ui.Sala.Configuracion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.AuthUI;
import com.grupo2.proteam.LoginActivity;
import com.grupo2.proteam.R;
import com.grupo2.proteam.databinding.FragmentConfiguracionBinding;


public class ConfiguracionFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_configuracion, container, false);
        Button Cerrar = root.findViewById(R.id.frgConfiguracion_btnCerrarSesion);
        Cerrar.setOnClickListener(view -> AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(task -> getActivity().finish()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
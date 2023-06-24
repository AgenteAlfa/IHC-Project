package com.grupo2.proteam.GrupoSupervisor.Historial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.grupo2.proteam.GrupoSupervisor.GrupoSupervisorViewModel;
import com.grupo2.proteam.R;


public class SHistorialFragment extends Fragment {
    private GrupoSupervisorViewModel pageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_grupo_supervisor_historial, container, false);

        return root;
    }
}
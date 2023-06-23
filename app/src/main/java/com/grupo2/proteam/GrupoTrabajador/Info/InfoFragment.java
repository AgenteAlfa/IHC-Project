package com.grupo2.proteam.GrupoTrabajador.Info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.grupo2.proteam.GrupoTrabajador.PageViewModel;
import com.grupo2.proteam.R;


public class InfoFragment extends Fragment {
    private PageViewModel pageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_grupo_trabajador_info, container, false);

        return root;
    }


}
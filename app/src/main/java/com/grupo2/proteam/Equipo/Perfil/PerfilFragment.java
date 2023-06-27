package com.grupo2.proteam.Equipo.Perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grupo2.proteam.Equipo.EquipoViewModel;
import com.grupo2.proteam.Equipo.Perfil.ListaPuntajes.PuntajesAdapter;
import com.grupo2.proteam.FStore.Puntaje;
import com.grupo2.proteam.R;

import java.util.List;


public class PerfilFragment extends Fragment {


    private EquipoViewModel DataVM;
    private Button btnPremios;
    private RecyclerView rvListaPuntajes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        btnPremios = root.findViewById(R.id.frgPerfil_btnPremios);
        rvListaPuntajes = root.findViewById(R.id.frgPerfil_rvListaPuntajes);

        DataVM = new ViewModelProvider(getActivity()).get(EquipoViewModel.class);
        DataVM.get_Puntajes().observe(getViewLifecycleOwner(), new Observer<List<Puntaje>>() {
            @Override
            public void onChanged(List<Puntaje> puntajes) {
                PuntajesAdapter adapter = new PuntajesAdapter(puntajes);
                rvListaPuntajes.setAdapter(adapter);
                rvListaPuntajes.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        DataVM.ActualizarListaPuntajes();
    }
}
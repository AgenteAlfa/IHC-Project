package com.grupo2.proteam.GrupoTrabajador.Historial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.GrupoTrabajador.GrupoTrabajadorViewModel;
import com.grupo2.proteam.GrupoTrabajador.Historial.ListaHistorial.HistorialTrabajadorAdapter;
import com.grupo2.proteam.GrupoTrabajador.Historial.ListaHistorial.itemHistoriaTrabajadorlListener;
import com.grupo2.proteam.R;

import java.util.List;


public class HistorialFragment extends Fragment {
    private GrupoTrabajadorViewModel DataVM;
    private RecyclerView ListaHistorial;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_grupo_trabajador_historial, container, false);
        ListaHistorial = root.findViewById(R.id.frgHistorialTrabajador_rvListaHistorial);
        DataVM = new ViewModelProvider(getActivity()).get(GrupoTrabajadorViewModel.class);

        DataVM.get_Historial().observe(getViewLifecycleOwner(), new Observer<List<MisionData>>() {
            @Override
            public void onChanged(List<MisionData> misionData) {
                HistorialTrabajadorAdapter adapter = new HistorialTrabajadorAdapter(misionData, new itemHistoriaTrabajadorlListener() {
                });
                ListaHistorial.setAdapter(adapter);
                ListaHistorial.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        return root;
    }


}
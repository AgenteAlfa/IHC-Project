package com.grupo2.proteam.GrupoSupervisor.Historial;

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
import com.grupo2.proteam.GrupoSupervisor.GrupoSupervisorViewModel;
import com.grupo2.proteam.GrupoSupervisor.Historial.ListaHistorial.HistorialSupervisorAdapter;
import com.grupo2.proteam.GrupoSupervisor.Historial.ListaHistorial.itemHistorialSupervisorListener;
import com.grupo2.proteam.R;

import java.util.List;


public class SHistorialFragment extends Fragment {
    private GrupoSupervisorViewModel DataVM;
    private RecyclerView ListaHistorial;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_grupo_supervisor_historial, container, false);
        ListaHistorial = root.findViewById(R.id.frgHistorialSupervisor_rvListaHistorial);
        DataVM = new ViewModelProvider(getActivity()).get(GrupoSupervisorViewModel.class);

        DataVM.get_Historial().observe(getViewLifecycleOwner(), new Observer<List<MisionData>>() {
            @Override
            public void onChanged(List<MisionData> misionData) {
                HistorialSupervisorAdapter adapter = new HistorialSupervisorAdapter(misionData, new itemHistorialSupervisorListener() {
                });
                ListaHistorial.setAdapter(adapter);
                ListaHistorial.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        return root;
    }
}
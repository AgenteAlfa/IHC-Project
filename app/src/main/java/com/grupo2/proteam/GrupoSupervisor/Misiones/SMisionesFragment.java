package com.grupo2.proteam.GrupoSupervisor.Misiones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.CrearGrupo.DFColaboradores;
import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.GrupoSupervisor.GrupoSupervisorViewModel;
import com.grupo2.proteam.GrupoSupervisor.Misiones.ListaMisiones.SMisionesAdapter;
import com.grupo2.proteam.GrupoSupervisor.Misiones.ListaMisiones.itemSMisionesListener;
import com.grupo2.proteam.GrupoTrabajador.GrupoTrabajadorViewModel;
import com.grupo2.proteam.GrupoTrabajador.Misiones.ListaMisiones.MisionesAdapter;
import com.grupo2.proteam.GrupoTrabajador.Misiones.ListaMisiones.itemMisionesListener;
import com.grupo2.proteam.R;

import java.util.List;


public class SMisionesFragment extends Fragment {
    private GrupoSupervisorViewModel DataVM;
    private RecyclerView ListaMisiones;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_grupo_supervisor_misiones, container, false);
        ListaMisiones = root.findViewById(R.id.frgGrupoSupervisor_ListaMisiones);
        DataVM = new ViewModelProvider(getActivity()).get(GrupoSupervisorViewModel.class);
        DataVM.get_Misiones().observe(getViewLifecycleOwner(), this::SetLista);

        return root;
    }
    private void SetLista(List<MisionData> misionData)
    {
        SMisionesAdapter MAdapter = new SMisionesAdapter(misionData, new itemSMisionesListener() {
            @Override
            public void onClickEliminar(MisionData M) {
                Toast.makeText(getContext(), "Mision Eliminada", Toast.LENGTH_SHORT).show();
                DataVM.EliminarMision(M, new GrupoSupervisorViewModel.PostListener() {
                    @Override
                    public void post() {
                        DataVM.ActualizarMisiones();
                    }
                });
            }

            @Override
            public void onClickAgregar() {
                new DFCrearMision().show(getActivity().getSupportFragmentManager(),"");
            }
        });
        ListaMisiones.setAdapter(MAdapter);
        ListaMisiones.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
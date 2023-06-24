package com.grupo2.proteam.GrupoTrabajador.Misiones;

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

import com.grupo2.proteam.Equipo.EquipoViewModel;
import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.GrupoTrabajador.GrupoTrabajadorViewModel;
import com.grupo2.proteam.GrupoTrabajador.Misiones.ListaMisiones.MisionesAdapter;
import com.grupo2.proteam.GrupoTrabajador.Misiones.ListaMisiones.itemMisionesListener;
import com.grupo2.proteam.R;

import java.util.List;


public class MisionesFragment extends Fragment {
    private GrupoTrabajadorViewModel DataVM;
    private RecyclerView ListaMisiones;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_grupo_trabajador_misiones, container, false);
        ListaMisiones = root.findViewById(R.id.frgGTMisiones_rvListaMisiones);
        DataVM = new ViewModelProvider(getActivity()).get(GrupoTrabajadorViewModel.class);
        DataVM.get_Misiones().observe(getViewLifecycleOwner(), this::SetLista);


        return root;
    }
    private void SetLista(List<MisionData> misionData)
    {
        MisionesAdapter MAdapter = new MisionesAdapter(misionData, new itemMisionesListener() {
            @Override
            public void onClickTerminar(MisionData M) {
                Toast.makeText(getContext(), "Terminar mision", Toast.LENGTH_SHORT).show();
            }
        });
        ListaMisiones.setAdapter(MAdapter);
        ListaMisiones.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
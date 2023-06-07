package com.grupo2.proteam.ui.Equipo.Grupos;

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

import com.grupo2.proteam.FStore.EquipoData;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.FStore.GrupoData;
import com.grupo2.proteam.R;
import com.grupo2.proteam.ui.Equipo.EquipoViewModel;
import com.grupo2.proteam.ui.Equipo.Grupos.ListaGrupos.GruposAdapter;
import com.grupo2.proteam.ui.Equipo.Grupos.ListaGrupos.itemGruposListener;

import java.util.List;

public class GruposFragment extends Fragment {

    EquipoViewModel DataVM;
    RecyclerView rvGrupos;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_grupos, container, false);
        rvGrupos = root.findViewById(R.id.frgGrupos_rvGrupos);
        DataVM = new ViewModelProvider(getActivity()).get(EquipoViewModel.class);
        DataVM.get_lstGrupos().observe(getViewLifecycleOwner(), grupos -> {
            if (grupos != null)
                setLista(grupos);
        });



        return root;
    }
    private void setLista(List<GrupoData> lstGrupos)
    {
        GruposAdapter gruposAdapter = new GruposAdapter(lstGrupos, DataVM.get_isAdmin().getValue(), new itemGruposListener() {
            @Override
            public void onClickAdministrar(GrupoData G) {

            }

            @Override
            public void onClickEntrar(GrupoData G) {

            }

            @Override
            public void onClickAgregar() {

            }
        });
        rvGrupos.setAdapter(gruposAdapter);
        rvGrupos.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
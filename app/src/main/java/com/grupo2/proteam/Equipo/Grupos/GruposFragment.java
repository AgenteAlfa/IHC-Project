package com.grupo2.proteam.Equipo.Grupos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.grupo2.proteam.CrearGrupo.CrearGrupoActivity;
import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.GruposAdapter;
import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.itemGruposListener;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.GrupoSupervisor.GrupoSupervisorActivity;
import com.grupo2.proteam.GrupoTrabajador.GrupoTrabajadorActivity;
import com.grupo2.proteam.R;
import com.grupo2.proteam.Equipo.EquipoViewModel;

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

    @Override
    public void onStart() {
        super.onStart();
        DataVM.ActualizarListaGrupos();
    }

    private void setLista(List<GrupoData> lstGrupos)
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String uuid = mAuth.getCurrentUser().getUid();

        GruposAdapter gruposAdapter = new GruposAdapter(lstGrupos, DataVM.get_isAdmin().getValue(), uuid,  new itemGruposListener() {
            @Override
            public void onClickAdministrar(GrupoData G) {
                Intent I = new Intent(getActivity(), GrupoSupervisorActivity.class);
                I.putExtra("IDEquipo",DataVM.get_EquipoData().getValue().getID());
                I.putExtra("IDGrupo",G.getID());
                startActivity(I);
            }

            @Override
            public void onClickEntrar(GrupoData G) {
                Intent I = new Intent(getActivity(), GrupoTrabajadorActivity.class);
                I.putExtra("IDEquipo",DataVM.get_EquipoData().getValue().getID());
                I.putExtra("IDGrupo",G.getID());
                startActivity(I);
            }

            @Override
            public void onClickAgregar() {
                Intent I = new Intent(getActivity(), CrearGrupoActivity.class);
                I.putExtra("IDEquipo",DataVM.get_EquipoData().getValue().getID());
                startActivity(I);
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
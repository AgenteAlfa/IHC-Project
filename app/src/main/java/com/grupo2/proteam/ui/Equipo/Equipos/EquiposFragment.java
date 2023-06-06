package com.grupo2.proteam.ui.Equipo.Equipos;

import android.content.Intent;
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

import com.grupo2.proteam.CrearEquipoActivity;
import com.grupo2.proteam.ui.Equipo.EquiposViewModel;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.ui.Grupo.GruposActivity;
import com.grupo2.proteam.R;
import com.grupo2.proteam.ui.Equipo.Equipos.ListaEquipos.EquiposAdapter;
import com.grupo2.proteam.ui.Equipo.Equipos.ListaEquipos.itemEquipoListener;

import java.util.List;

public class EquiposFragment extends Fragment {

    RecyclerView rvEquipos;
    EquiposViewModel DataVM;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataVM =
                new ViewModelProvider(getActivity()).get(EquiposViewModel.class);
        View root = inflater.inflate(R.layout.fragment_equipos, container, false);
        rvEquipos = root.findViewById(R.id.frgEquipos_rvEquipos);

        DataVM.get_lstEquipos().observe(getViewLifecycleOwner(), new Observer<List<Equipo>>() {
            @Override
            public void onChanged(List<Equipo> equipos) {
                if (equipos != null)
                    setLista(equipos);
            }
        });


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        DataVM.ActualizarListaEquipos();
    }

    private void setLista(List<Equipo> lstEquipos)
    {
        EquiposAdapter equiposAdaptador = new EquiposAdapter(lstEquipos, new itemEquipoListener() {
            @Override
            public void onClickInfo(Equipo e) {
                Toast.makeText(getContext(), "Mostrar Info", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickEntrar(Equipo e) {
                Toast.makeText(getContext(), "Entrar al grupo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), GruposActivity.class);
                //intent.putExtra("IDEquipo", e.)
                startActivity(intent);
            }

            @Override
            public void onClickAgregar() {
                Toast.makeText(getContext(), "Agregando equipo", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), CrearEquipoActivity.class));
            }
        });
        rvEquipos.setAdapter(equiposAdaptador);
        rvEquipos.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
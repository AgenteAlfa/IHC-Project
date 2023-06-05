package com.grupo2.proteam.ui.Equipos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.EquiposViewModel;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.R;
import com.grupo2.proteam.ui.Equipos.ListaEquipos.EquiposAdapter;
import com.grupo2.proteam.ui.Equipos.ListaEquipos.itemEquipoListener;

import java.util.List;

public class EquiposFragment extends Fragment {

    RecyclerView rvEquipos;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EquiposViewModel DataVM =
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
            }

            @Override
            public void onClickAgregar() {
                Toast.makeText(getContext(), "Agregando equipo", Toast.LENGTH_SHORT).show();
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
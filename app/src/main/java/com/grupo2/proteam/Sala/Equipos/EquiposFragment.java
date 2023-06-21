package com.grupo2.proteam.Sala.Equipos;

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

import com.grupo2.proteam.Sala.Equipos.ListaEquipos.EquiposAdapter;
import com.grupo2.proteam.Sala.SalaViewModel;
import com.grupo2.proteam.Sala.CrearEquipoActivity;
import com.grupo2.proteam.FStore.Compuestos.EquipoData;
import com.grupo2.proteam.Sala.UnirseEquipoActivity;
import com.grupo2.proteam.Equipo.EquipoActivity;
import com.grupo2.proteam.R;
import com.grupo2.proteam.Sala.Equipos.ListaEquipos.itemEquipoListener;

import java.util.List;

public class EquiposFragment extends Fragment {

    RecyclerView rvEquipos;
    SalaViewModel DataVM;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataVM = new ViewModelProvider(getActivity()).get(SalaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_equipos, container, false);
        rvEquipos = root.findViewById(R.id.frgEquipos_rvEquipos);

        DataVM.get_lstEquipos().observe(getViewLifecycleOwner(), new Observer<List<EquipoData>>() {
            @Override
            public void onChanged(List<EquipoData> equipos) {
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

    private void setLista(List<EquipoData> lstEquipos)
    {
        EquiposAdapter equiposAdaptador = new EquiposAdapter(lstEquipos, new itemEquipoListener() {
            @Override
            public void onClickInfo(EquipoData e) {
                Toast.makeText(getContext(), "Mostrar Info", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickEntrar(EquipoData e) {
                Toast.makeText(getContext(), "Entrar al grupo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), EquipoActivity.class);
                intent.putExtra("IDEquipo", e.getID());
                intent.putExtra("isAdmin",DataVM.get_UsuarioInfo().getValue().isAdmin());
                startActivity(intent);
            }

            @Override
            public void onClickAgregar() {

                //Ver si es admin
                if (DataVM.get_UsuarioInfo().getValue().isAdmin())
                {
                    Toast.makeText(getContext(), "Agregando equipo", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), CrearEquipoActivity.class));
                }
                else
                {
                    Toast.makeText(getContext(), "Uniendose a un equipo", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), UnirseEquipoActivity.class));
                }
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
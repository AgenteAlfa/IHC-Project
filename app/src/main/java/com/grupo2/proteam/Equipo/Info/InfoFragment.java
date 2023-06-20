package com.grupo2.proteam.Equipo.Info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupo2.proteam.Equipo.Info.Lista.ColaboradoresAdapter;
import com.grupo2.proteam.FStore.Codigo;
import com.grupo2.proteam.FStore.EquipoData;
import com.grupo2.proteam.FStore.PrivadoUsuario;
import com.grupo2.proteam.R;
import com.grupo2.proteam.Equipo.EquipoViewModel;

import java.util.List;

public class InfoFragment extends Fragment {

    EquipoViewModel DataVM;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ExpandableListView lstColaboradores;

        View root = inflater.inflate(R.layout.fragment_info, container, false);
        DataVM = new ViewModelProvider(getActivity()).get(EquipoViewModel.class);
        TextView ID = root.findViewById(R.id.frgInfo_txtID),
                Nombre = root.findViewById(R.id.frgInfo_txtNombre),
                Descripcion = root.findViewById(R.id.frgInfo_txtDescripcion),
                Codigo = root.findViewById(R.id.frgInfo_txtCodigo),
                Normativa = root.findViewById(R.id.frgInfo_txtNormativa);
        DataVM.get_EquipoData().observe(getViewLifecycleOwner(), new Observer<EquipoData>() {
            @Override
            public void onChanged(EquipoData equipoData) {
                ID.setText(equipoData.getID());
                Nombre.setText(equipoData.getNombre());
                Descripcion.setText(equipoData.getDescripcion());
                Normativa.setText(equipoData.getNormativa().trim());
            }
        });
        DataVM.get_Codigo().observe(getViewLifecycleOwner(), new Observer<Codigo>() {
            @Override
            public void onChanged(Codigo codigo) {
                if (codigo != null)
                    Codigo.setText(codigo.getUuidEquipo());
            }
        });

        lstColaboradores = root.findViewById(R.id.frgInfo_lstColaboradores);
        LinearLayout ContenedorColaboradores = root.findViewById(R.id.frgInfo_lyContenedorLst);
        DataVM.get_Colaboradores().observe(getViewLifecycleOwner(), new Observer<List<PrivadoUsuario>>() {
            @Override
            public void onChanged(List<PrivadoUsuario> privadoUsuarios) {
                ColaboradoresAdapter Adapter = new ColaboradoresAdapter(privadoUsuarios);
                lstColaboradores.setAdapter(Adapter);
                lstColaboradores.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int i) {
                        int h = lstColaboradores.getHeight();
                        ViewGroup.LayoutParams params = ContenedorColaboradores.getLayoutParams();
                        params.height = h;
                        ContenedorColaboradores.setLayoutParams(params);
                    }
                });
            }
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        DataVM.ActualizarListaColaboradores();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
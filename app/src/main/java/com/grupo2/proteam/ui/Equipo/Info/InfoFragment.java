package com.grupo2.proteam.ui.Equipo.Info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupo2.proteam.FStore.Codigo;
import com.grupo2.proteam.FStore.EquipoData;
import com.grupo2.proteam.R;
import com.grupo2.proteam.ui.Equipo.EquipoViewModel;
import com.grupo2.proteam.ui.Sala.SalaViewModel;

public class InfoFragment extends Fragment {

    EquipoViewModel DataVM;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_info, container, false);
        DataVM = new ViewModelProvider(getActivity()).get(EquipoViewModel.class);
        TextView ID = root.findViewById(R.id.frgInfo_txtID),
                Nombre = root.findViewById(R.id.frgInfo_txtNombre),
                Descripcion = root.findViewById(R.id.frgInfo_txtDescripcion),
                Codigo = root.findViewById(R.id.frgInfo_txtCodigo);
        DataVM.get_EquipoData().observe(getViewLifecycleOwner(), new Observer<EquipoData>() {
            @Override
            public void onChanged(EquipoData equipoData) {
                ID.setText(equipoData.getID());
                Nombre.setText(equipoData.getNombre());
                Descripcion.setText(equipoData.getDescripcion());
            }
        });
        DataVM.get_Codigo().observe(getViewLifecycleOwner(), new Observer<Codigo>() {
            @Override
            public void onChanged(Codigo codigo) {
                if (codigo != null)
                Codigo.setText(codigo.getUuidEquipo());
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
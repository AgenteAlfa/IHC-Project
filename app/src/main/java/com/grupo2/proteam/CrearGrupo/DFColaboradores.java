package com.grupo2.proteam.CrearGrupo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.CrearGrupo.Listas.Colaboradores.AddColaboradoresAdapter;
import com.grupo2.proteam.CrearGrupo.Listas.Colaboradores.itemAddColaboradoresListener;
import com.grupo2.proteam.Equipo.EquipoViewModel;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.util.List;

public class DFColaboradores extends DialogFragment {
    CrearGrupoViewModel DataVM;
    RecyclerView ListaAddColaborador;
    Button Agregar;
    EditText Nombre;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog D = new AlertDialog.Builder(requireContext()).create();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_agregar_colaboradores,null);
        ListaAddColaborador = v.findViewById(R.id.DialogAgregarColaboradores_rvColaboradores);
        Agregar = v.findViewById(R.id.DialogAgregarColaboradores_btnAgregar);
        Nombre = v.findViewById(R.id.DialogAgregarColaboradores_edtNombre);


        DataVM = new ViewModelProvider(getActivity()).get(CrearGrupoViewModel.class);
        Nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DataVM.get_PatronBusqueda().setValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        D.setView(v);
        DataVM.get_PatronBusqueda().observe(this,s -> DataVM.Buscar());
        DataVM.get_ResultadosBusqueda().observe(this, usuariosData -> {
            AddColaboradoresAdapter adapter = new AddColaboradoresAdapter(usuariosData, (colaborador, isCheck) -> {
                if (isCheck)
                {
                    DataVM.get_ColaboradoresMarcados().getValue().add(colaborador);
                }
                else
                {
                    DataVM.get_ColaboradoresMarcados().getValue().remove(colaborador);
                }

            });
            ListaAddColaborador.setAdapter(adapter);
            ListaAddColaborador.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        Agregar.setOnClickListener(view -> {
            DataVM.GuardarColaboradoresMarcados();
            D.dismiss();
        });

    return D;
    }


}

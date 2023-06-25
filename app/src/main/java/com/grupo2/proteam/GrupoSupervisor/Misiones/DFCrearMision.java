package com.grupo2.proteam.GrupoSupervisor.Misiones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.grupo2.proteam.CrearGrupo.Listas.Colaboradores.AddColaboradoresAdapter;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.FStore.Mision;
import com.grupo2.proteam.GrupoSupervisor.GrupoSupervisorViewModel;
import com.grupo2.proteam.GrupoSupervisor.Misiones.ListaColaboradoresMision.ColaboradoresMisionAdapter;
import com.grupo2.proteam.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DFCrearMision extends DialogFragment {

    RecyclerView rvColaboradoresMision;
    GrupoSupervisorViewModel DataVM;
    List<UsuarioData> Marcados;

    CardView cvTipo;
    TextView Tipo;
    Button CrearMision;
    TextInputLayout Titulo, Descripcion, Puntaje;
    
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog D = new AlertDialog.Builder(requireContext()).create();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_crear_mision,null);
        rvColaboradoresMision = v.findViewById(R.id.DialogCrearMision_rvColaboradores);
        cvTipo = v.findViewById(R.id.DialogCrearMision_cv3);
        Tipo = v.findViewById(R.id.DialogCrearMision_txtTipo);
        CrearMision = v.findViewById(R.id.DialogCrearMision_btnCrear);
        Titulo = v.findViewById(R.id.DialogCrearMision_tilNombre);
        Descripcion = v.findViewById(R.id.DialogCrearMision_tilDescripcion);
        Puntaje = v.findViewById(R.id.DialogCrearMision_tilPuntaje);
        D.setView(v);
        DataVM = new ViewModelProvider(getActivity()).get(GrupoSupervisorViewModel.class);
        Marcados = new ArrayList<>();

        List<UsuarioData> lst = new ArrayList<>();
        HashMap<String,UsuarioData> lstColaboradores = DataVM.get_Colaboradores().getValue();
        List<String> trabajador = DataVM.get_GrupoData().getValue().getColaboradores();
        List<String> supervisor = DataVM.get_GrupoData().getValue().getSupervisores();
        for (String s : trabajador) {
            lst.add(lstColaboradores.get(s));
        }
        for (String s : supervisor) {
            lst.add(lstColaboradores.get(s));
        }

        MostrarColaboradores(lst);

        CrearMision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Marcados.size() == 0)
                {
                    Toast.makeText(getContext(), "Agrege un integrante a la mision", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Titulo.getEditText().getText().toString().equals(""))
                    Toast.makeText(getContext(), "El titulo no puede estar vacio", Toast.LENGTH_SHORT).show();
                ArrayList<String> colabs = new ArrayList<>();
                for (UsuarioData marcado : Marcados) {
                    colabs.add(marcado.getID());
                }

                String strDescripcion = Descripcion.getEditText().getText().toString(),
                        strTitulo = Titulo.getEditText().getText().toString(),
                        strPuntaje = Puntaje.getEditText().getText().toString();

                Mision M = new Mision(colabs, null, strDescripcion, Integer.parseInt(strPuntaje), Marcados.size() > 1, strTitulo);
                DataVM.CrearMision(M, new GrupoSupervisorViewModel.PostListener() {
                    @Override
                    public void post() {
                        Toast.makeText(getContext(), "Mision creada", Toast.LENGTH_SHORT).show();
                        DataVM.ActualizarMisiones();
                        D.dismiss();
                    }
                });

            }
        });
        
        

        return D;
    }

    private void SetTipo(boolean b)
    {
        Tipo.setText(b? "Grupal" : "Individual");
        cvTipo.setCardBackgroundColor(
                ContextCompat.getColor(cvTipo.getContext(),b? R.color.amarillo_1 : R.color.verde_1));
    }


    private void MostrarColaboradores(List<UsuarioData> lst)
    {


        ColaboradoresMisionAdapter adapter = new ColaboradoresMisionAdapter(lst, (colaborador, isCheck) -> {
            if (isCheck) {
                Marcados.add(colaborador);
            } else {
                Marcados.remove(colaborador);
            }
            SetTipo(Marcados.size() > 1);



        });
        rvColaboradoresMision.setAdapter(adapter);
        rvColaboradoresMision.setLayoutManager(new LinearLayoutManager(getContext()));
    }



}

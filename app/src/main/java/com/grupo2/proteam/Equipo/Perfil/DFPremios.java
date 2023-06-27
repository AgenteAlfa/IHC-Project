package com.grupo2.proteam.Equipo.Perfil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.Equipo.EquipoViewModel;
import com.grupo2.proteam.Equipo.Perfil.ListaPremios.PremiosAdapter;
import com.grupo2.proteam.FStore.Premio;
import com.grupo2.proteam.FStore.Puntaje;
import com.grupo2.proteam.R;

import java.util.List;

public class DFPremios extends DialogFragment {

    private EquipoViewModel DataVM;
    RecyclerView ListaPremios;
    Button Agregar;
    View viewAgregar;
    EditText edtPremio, edtPuntaje;
    private final boolean isAdmin;

    public DFPremios(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog D = new AlertDialog.Builder(requireContext()).create();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_premios,null);


        DataVM = new ViewModelProvider(getActivity()).get(EquipoViewModel.class);
        ListaPremios = v.findViewById(R.id.DialogPremios_rvListaPuntajes);
        Agregar = v.findViewById(R.id.DialogPremios_btnAgregar);
        viewAgregar = v.findViewById(R.id.DialogPremios_lyAgregar);
        edtPremio = v.findViewById(R.id.DialogPremios_edtPremio);
        edtPuntaje = v.findViewById(R.id.DialogPremios_edtPuntos);
        viewAgregar.setVisibility(isAdmin? View.VISIBLE : View.GONE);

        D.setView(v);
        DataVM.get_Premios().observe(this, new Observer<List<com.grupo2.proteam.FStore.Premio>>() {
            @Override
            public void onChanged(List<com.grupo2.proteam.FStore.Premio> premios) {
                Log.d("Premios", "onChanged: Premios actualizados");
                PremiosAdapter adapter = new PremiosAdapter(premios);
                ListaPremios.setAdapter(adapter);
                ListaPremios.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPremio = edtPremio.getText().toString(),
                        strPuntaje = edtPuntaje.getText().toString();
                if (strPremio.equals("") ) Toast.makeText(getContext(), "Es necesario un nombre para el premio", Toast.LENGTH_SHORT).show();
                if (strPuntaje.equals("") ) Toast.makeText(getContext(), "Es necesario un puntaje para el premio", Toast.LENGTH_SHORT).show();
                if ( (!strPremio.equals("")) && (!strPuntaje.equals("")) )
                {
                    Premio p = new Premio(strPremio, Integer.parseInt(strPuntaje) );
                    DataVM.AgregarPremio(p);
                    Toast.makeText(getContext(), "Premio Agregado", Toast.LENGTH_SHORT).show();
                    edtPremio.setText("");
                    edtPuntaje.setText("");
                }
            }
        });


        return D;
    }
}

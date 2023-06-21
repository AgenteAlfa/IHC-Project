package com.grupo2.proteam.CrearGrupo.Listas.Colaboradores;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;

public class AddColaboradoresViewHolder extends RecyclerView.ViewHolder{
    private final TextView NyA;
    private final CheckBox Agregar;
    private final SoftReference<itemAddColaboradoresListener> listenerSelectos;
    public AddColaboradoresViewHolder(@NonNull View view, SoftReference<itemAddColaboradoresListener> listener) {
        super(view);
        NyA = view.findViewById(R.id.ListaAddColaboradores_txtNyA);
        Agregar = view.findViewById(R.id.ListaAddColaboradores_cboxAdd);
        listenerSelectos = listener;

    }
    public void Setear(UsuarioData usuarioData)
    {
        NyA.setText(usuarioData.getNyA());
        Agregar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listenerSelectos.get().Check(usuarioData, b);
            }
        });
    }
}

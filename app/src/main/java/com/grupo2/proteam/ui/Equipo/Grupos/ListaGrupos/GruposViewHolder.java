package com.grupo2.proteam.ui.Equipo.Grupos.ListaGrupos;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo2.proteam.FStore.EquipoData;
import com.grupo2.proteam.FStore.GrupoData;
import com.grupo2.proteam.R;

import org.checkerframework.checker.units.qual.A;

import java.lang.ref.SoftReference;

public class GruposViewHolder extends RecyclerView.ViewHolder{
    private TextView Nombre,Descripcion;
    private Button Accion;
    private FloatingActionButton Agregar;
    private CardView Item;
    private SoftReference<itemGruposListener> listenerGrupo;
    public GruposViewHolder(@NonNull View view, SoftReference<itemGruposListener> listener) {
        super(view);
        Nombre = view.findViewById(R.id.ListaGrupos_txtNombre);
        Descripcion = view.findViewById(R.id.ListaGrupos_txtDescripcion);
        Accion = view.findViewById(R.id.ListaGrupos_btnAccion);
        Agregar = view.findViewById(R.id.ListaGrupos_fabAgregar);
        Agregar.setOnClickListener(view1 -> listenerGrupo.get().onClickAgregar());
        Item = view.findViewById(R.id.ListaGrupos_cvItem);
        listenerGrupo = listener;
    }

    public void Setear(GrupoData GrupoIesimo, boolean isAdmin)
    {

        if (GrupoIesimo == null)
        {
            Item.setVisibility(View.GONE);
            Agregar.setVisibility(View.VISIBLE);
        }
        else
        {
            Item.setVisibility(View.VISIBLE);
            Agregar.setVisibility(View.GONE);

            Nombre.setText(GrupoIesimo.getNombre());
            Descripcion.setText(GrupoIesimo.getDescripcion());

            Accion.setText(isAdmin? "Administrar" : "Entrar");
            if (isAdmin)
                Accion.setOnClickListener(view -> listenerGrupo.get().onClickAdministrar(GrupoIesimo));
            else
                Accion.setOnClickListener(view -> listenerGrupo.get().onClickEntrar(GrupoIesimo));
        }


    }
}

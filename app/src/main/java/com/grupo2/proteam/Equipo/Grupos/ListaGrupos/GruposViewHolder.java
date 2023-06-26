package com.grupo2.proteam.Equipo.Grupos.ListaGrupos;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;

public class GruposViewHolder extends RecyclerView.ViewHolder{
    private TextView Nombre,Descripcion;
    private Button Administrar, Entrar;
    private FloatingActionButton Agregar;
    private CardView Item;
    private SoftReference<itemGruposListener> listenerGrupo;
    public GruposViewHolder(@NonNull View view, SoftReference<itemGruposListener> listener) {
        super(view);
        Nombre = view.findViewById(R.id.ListaGrupos_txtNombre);
        Descripcion = view.findViewById(R.id.ListaGrupos_txtDescripcion);
        Administrar = view.findViewById(R.id.ListaGrupos_btnAdministrar);
        Entrar = view.findViewById(R.id.ListaGrupos_btnEntrar);
        Agregar = view.findViewById(R.id.ListaGrupos_fabAgregar);
        Agregar.setOnClickListener(view1 -> listenerGrupo.get().onClickAgregar());
        Item = view.findViewById(R.id.ListaGrupos_cvItem);
        listenerGrupo = listener;
    }

    public void Setear(GrupoData GrupoIesimo, boolean isSupervisor)
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

            Administrar.setVisibility(isSupervisor? View.VISIBLE : View.INVISIBLE);
            Administrar.setOnClickListener(view -> listenerGrupo.get().onClickAdministrar(GrupoIesimo));
            Entrar.setOnClickListener(view -> listenerGrupo.get().onClickEntrar(GrupoIesimo));
        }


    }
}

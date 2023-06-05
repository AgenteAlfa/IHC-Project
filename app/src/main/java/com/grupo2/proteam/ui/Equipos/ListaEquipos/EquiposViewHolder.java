package com.grupo2.proteam.ui.Equipos.ListaEquipos;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class EquiposViewHolder extends RecyclerView.ViewHolder {
    private final TextView Nombre,Descripcion;
    private final ImageView Fondo;
    private final FloatingActionButton Agregar;
    private final Button Info, Entrar;
    private final CardView Item;
    private final SoftReference<itemEquipoListener> listenerEquipo;
    public static final String TAG = "EquiposViewHolder";

    public EquiposViewHolder(View view, SoftReference<itemEquipoListener> listener) {
        super(view);
        // Define click listener for the ViewHolder's View

        Nombre = view.findViewById(R.id.ListaEquipos_txtNombre);
        Descripcion = view.findViewById(R.id.ListaEquipos_txtDescripcion);
        Fondo = view.findViewById(R.id.ListaEquipos_imgFondo);
        Agregar = view.findViewById(R.id.ListaEquipos_fabAgregar);
        Info = view.findViewById(R.id.ListaEquipos_btnInfo);
        Entrar = view.findViewById(R.id.ListaEquipos_btnEntrar);
        Item = view.findViewById(R.id.ListaEquipos_cvItem);
        listenerEquipo = listener;
        Agregar.setOnClickListener(mview -> listenerEquipo.get().onClickAgregar());
    }
    public void Setear(Equipo EquipoIesimo)
    {

        if (EquipoIesimo == null)
        {
            Item.setVisibility(View.GONE);
            Agregar.setVisibility(View.VISIBLE);
        }
        else
        {
            Item.setVisibility(View.VISIBLE);
            Agregar.setVisibility(View.GONE);
            Nombre.setText(EquipoIesimo.getNombre());
            Descripcion.setText(EquipoIesimo.getDescripcion());

            Info.setOnClickListener(view -> listenerEquipo.get().onClickInfo(EquipoIesimo));
            Entrar.setOnClickListener(view -> listenerEquipo.get().onClickEntrar(EquipoIesimo));
        }


    }

}

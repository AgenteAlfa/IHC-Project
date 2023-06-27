package com.grupo2.proteam.Equipo.Perfil.ListaPuntajes;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.itemGruposListener;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Puntaje;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;

public class PuntajesViewHolder extends RecyclerView.ViewHolder{
    private TextView Nombre,Puntaje;
    public PuntajesViewHolder(@NonNull View view) {
        super(view);
        Nombre = view.findViewById(R.id.ListaPuntaje_txtNyA);
        Puntaje = view.findViewById(R.id.ListaPuntaje_txtPuntaje);
    }

    public void Setear(Puntaje puntaje)
    {
        Nombre.setText(puntaje.getNya());
        String ptj = puntaje.getPuntaje() + "p";
        Puntaje.setText(ptj);


    }
}

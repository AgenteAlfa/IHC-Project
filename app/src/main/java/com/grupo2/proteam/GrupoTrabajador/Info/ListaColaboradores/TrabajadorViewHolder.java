package com.grupo2.proteam.GrupoTrabajador.Info.ListaColaboradores;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.CrearGrupo.Listas.ColaboradoresSelectos.itemColaboradoresSelectosListener;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;

public class TrabajadorViewHolder extends RecyclerView.ViewHolder{
    private final TextView  NyA, Tipo;
    private final CardView cvTipo;
    private final ImageButton Elimiar;
    private final SoftReference<itemTrabajadorListener> listenerTrabajadores;
    public TrabajadorViewHolder(@NonNull View view, SoftReference<itemTrabajadorListener> listener) {
        super(view);
        NyA = view.findViewById(R.id.ListaSimpleColaboradores_txtNyA);
        Elimiar = view.findViewById(R.id.ListaSimpleColaboradores_ibtnEliminar);
        Tipo = view.findViewById(R.id.ListaSimpleClaboradores_txtTipo);
        cvTipo = view.findViewById(R.id.ListaSimpleClaboradores_cvTipo);

        listenerTrabajadores = listener;

    }

    private void SetTipo(boolean b)
    {
        Tipo.setText(b? "Supervisor" : "Colaborador");
        cvTipo.setCardBackgroundColor(
                ContextCompat.getColor(cvTipo.getContext(),b? R.color.azul_1: R.color.verde_1));

    }
    public void Setear(UsuarioData usuarioData, boolean canDelete)
    {
        NyA.setText(usuarioData.getNyA());
        SetTipo(usuarioData.isSupervisor());
        Elimiar.setVisibility(canDelete? View.VISIBLE : View.GONE);
        Elimiar.setOnClickListener(view -> listenerTrabajadores.get().Eliminar(usuarioData));
    }
}

package com.grupo2.proteam.GrupoSupervisor.Colaboradores.ListaColaboradoresSupervisor;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;

public class TrabajadoresSupervisorViewHolder extends RecyclerView.ViewHolder{
    private final TextView  NyA, Tipo;
    private final CardView cvTipo;
    private final ImageButton Elimiar;
    private final SoftReference<itemTrabajadoresSupervisorListener> listenerTrabajadores;
    public TrabajadoresSupervisorViewHolder(@NonNull View view, SoftReference<itemTrabajadoresSupervisorListener> listener) {
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

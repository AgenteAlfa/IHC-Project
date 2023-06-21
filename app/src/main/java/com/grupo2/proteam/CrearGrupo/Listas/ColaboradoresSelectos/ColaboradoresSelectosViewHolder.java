package com.grupo2.proteam.CrearGrupo.Listas.ColaboradoresSelectos;

import android.view.View;
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

public class ColaboradoresSelectosViewHolder extends RecyclerView.ViewHolder{
    private final TextView Instruccion, NyA;
    private final SwitchCompat isSupervisor;
    private final ImageButton Elimiar;
    private final LinearLayout ColaboradorItem;
    private final SoftReference<itemColaboradoresSelectosListener> listenerSelectos;
    public ColaboradoresSelectosViewHolder(@NonNull View view, SoftReference<itemColaboradoresSelectosListener> listener) {
        super(view);
        Instruccion = view.findViewById(R.id.ListaColaboradores_txtInstruccion);
        NyA = view.findViewById(R.id.ListaColaboradores_txtNyA);
        isSupervisor = view.findViewById(R.id.ListaColaboradores_swcSupervisor);
        Elimiar = view.findViewById(R.id.ListaColaboradores_ibtnEliminar);
        ColaboradorItem = view.findViewById(R.id.ListaColaboradores_lyItem);
        listenerSelectos = listener;

    }
    public void Setear(UsuarioData usuarioData)
    {

        if (usuarioData == null)
        {
            ColaboradorItem.setVisibility(View.GONE);
            Instruccion.setVisibility(View.VISIBLE);
        }
        else
        {
            ColaboradorItem.setVisibility(View.VISIBLE);
            Instruccion.setVisibility(View.GONE);
            NyA.setText(usuarioData.getNyA());
            isSupervisor.setChecked(usuarioData.isSupervisor());
            isSupervisor.setOnCheckedChangeListener((compoundButton, b) -> listenerSelectos.get().Supervisor(usuarioData, b));
            Elimiar.setOnClickListener(view -> listenerSelectos.get().Eliminar(usuarioData));
        }
    }
}

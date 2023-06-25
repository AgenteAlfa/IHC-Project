package com.grupo2.proteam.GrupoTrabajador.Misiones.ListaMisiones;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.itemGruposListener;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class MisionesViewHolder extends RecyclerView.ViewHolder{
    private TextView Tipo, Titulo, Puntaje, Descripcion, Integrantes, Status;
    private Button Terminar;
    private CardView cvTipo;
    private View Divider;

    private SoftReference<itemMisionesListener> listenerGrupo;
    public MisionesViewHolder(@NonNull View view, SoftReference<itemMisionesListener> listener) {
        super(view);
        Tipo = view.findViewById(R.id.ListaMision_txtTipo);
        Titulo = view.findViewById(R.id.ListaMision_txtTitulo);
        Puntaje = view.findViewById(R.id.ListaMision_txtPuntaje);
        Descripcion = view.findViewById(R.id.ListaMision_txtDescripcion);
        Integrantes = view.findViewById(R.id.ListaMision_txtIntegrantes);
        Status = view.findViewById(R.id.ListaMision_txtStatus);

        Terminar = view.findViewById(R.id.ListaMision_btnTerminar);
        cvTipo = view.findViewById(R.id.ListaMision_cvTipo);
        Divider = view.findViewById(R.id.ListaMision_Divider);



        listenerGrupo = listener;
    }

    private void SetTipo(boolean b)
    {
        //Divider.setVisibility(b? View.VISIBLE : View.GONE);
        //Integrantes.setVisibility(b? View.VISIBLE : View.GONE);
        Tipo.setText(b? "G" : "I");
        cvTipo.setCardBackgroundColor(
                ContextCompat.getColor(cvTipo.getContext(),b? R.color.amarillo_1 : R.color.verde_1));

    }
    private void SetIntegrantes(List<UsuarioData> colaboradores)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < colaboradores.size(); i++) {
            UsuarioData colaborador = colaboradores.get(i);
            builder.append(colaborador.getNyA());
            if (i < colaboradores.size() - 2)
                builder.append(", ");
            else if (i < colaboradores.size() - 1)
                builder.append(" y ");

        }
        Integrantes.setText(builder.toString());
    }


    public void Setear(MisionData MisionIesimo)
    {
        Terminar.setOnClickListener(view1 -> listenerGrupo.get().onClickTerminar(MisionIesimo));
        SetTipo(MisionIesimo.isTipo());
        Titulo.setText(MisionIesimo.getTitulo());
        String strPuntaje = MisionIesimo.getPuntaje() + "p";
        Puntaje.setText(strPuntaje);
        Descripcion.setText(MisionIesimo.getDescripcion());
        SetIntegrantes(MisionIesimo.getIntegrantes());
        Status.setText(MisionIesimo.getStatus());
    }
}

package com.grupo2.proteam.GrupoSupervisor.Misiones.ListaMisiones;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;

public class SMisionesViewHolder extends RecyclerView.ViewHolder{
    private TextView Tipo, Titulo, Puntaje, Descripcion, Integrantes;
    private ImageButton Eliminar;
    private CardView cvTipo;
    private View Divider;
    private FloatingActionButton Agregar;
    private View Item;

    private SoftReference<itemSMisionesListener> listenerGrupo;
    public SMisionesViewHolder(@NonNull View view, SoftReference<itemSMisionesListener> listener) {
        super(view);
        Tipo = view.findViewById(R.id.ListaSupervisorMision_txtTipo);
        Titulo = view.findViewById(R.id.ListaSupervisorMision_txtTitulo);
        Puntaje = view.findViewById(R.id.ListaSupervisorMision_txtPuntaje);
        Descripcion = view.findViewById(R.id.ListaSupervisorMision_txtDescripcion);
        Integrantes = view.findViewById(R.id.ListaSupervisorMision_txtIntegrantes);

        Eliminar = view.findViewById(R.id.ListaSupervisorMision_ibtnEliminar);
        cvTipo = view.findViewById(R.id.ListaSupervisorMision_cvTipo);
        Divider = view.findViewById(R.id.ListaSupervisorMision_Divider);

        Agregar = view.findViewById(R.id.ListaSupervisorMision_fabAgregar);
        Item = view.findViewById(R.id.ListaSupervisorMision_lyItem);



        listenerGrupo = listener;
    }

    private void SetTipo(boolean b)
    {
        Divider.setVisibility(b? View.VISIBLE : View.GONE);
        Integrantes.setVisibility(b? View.VISIBLE : View.GONE);
        Tipo.setText(b? "G" : "I");
        cvTipo.setCardBackgroundColor(
                ContextCompat.getColor(cvTipo.getContext(),b? R.color.amarillo_1 : R.color.verde_1));

    }
    private void SetIntegrantes(List<UsuarioData> colaboradores)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < colaboradores.size() - 1; i++) {
            UsuarioData colaborador = colaboradores.get(i);
            builder.append(colaborador.getNyA());
            if (i < colaboradores.size() - 2)
                builder.append(", ");
            else if (i < colaboradores.size() - 1)
                builder.append(" y ");

        }
        Integrantes.setText("Varios integrantes");
    }


    public void Setear(MisionData MisionIesimo)
    {
        if (MisionIesimo == null)
        {
            Item.setVisibility(View.GONE);
            Agregar.setVisibility(View.VISIBLE);
        }
        else
        {
            Item.setVisibility(View.VISIBLE);
            Agregar.setVisibility(View.GONE);

            Eliminar.setOnClickListener(view1 -> listenerGrupo.get().onClickEliminar(MisionIesimo));
            SetTipo(MisionIesimo.isTipo());
            Titulo.setText(MisionIesimo.getTitulo());
            String strPuntaje = MisionIesimo.getPuntaje() + "p";
            Puntaje.setText(strPuntaje);
            Descripcion.setText(MisionIesimo.getDescripcion());
            SetIntegrantes(MisionIesimo.getIntegrantes());
        }

    }
}

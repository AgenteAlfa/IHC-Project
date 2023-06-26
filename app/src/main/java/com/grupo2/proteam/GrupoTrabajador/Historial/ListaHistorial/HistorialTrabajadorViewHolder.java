package com.grupo2.proteam.GrupoTrabajador.Historial.ListaHistorial;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistorialTrabajadorViewHolder extends RecyclerView.ViewHolder{
    private TextView Tipo, Titulo, Puntaje, Descripcion, Integrantes, Fecha;
    private CardView cvTipo;
    private View Divider;

    private SoftReference<itemHistoriaTrabajadorlListener> listenerGrupo;
    public HistorialTrabajadorViewHolder(@NonNull View view, SoftReference<itemHistoriaTrabajadorlListener> listener) {
        super(view);
        Tipo = view.findViewById(R.id.ListaHistorial_txtTipo);
        Titulo = view.findViewById(R.id.ListaHistorial_txtTitulo);
        Puntaje = view.findViewById(R.id.ListaHistorial_txtPuntaje);
        Descripcion = view.findViewById(R.id.ListaHistorial_txtDescripcion);
        Integrantes = view.findViewById(R.id.ListaHistorial_txtIntegrantes);
        Fecha = view.findViewById(R.id.ListaHistorial_txtFecha);


        cvTipo = view.findViewById(R.id.ListaHistorial_cvTipo);
        Divider = view.findViewById(R.id.ListaHistorial_Divider);



        listenerGrupo = listener;
    }

    private void SetTipo(boolean b)
    {
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
        SetTipo(MisionIesimo.isTipo());
        Titulo.setText(MisionIesimo.getTitulo());
        String strPuntaje = MisionIesimo.getPuntaje() + "p";
        Puntaje.setText(strPuntaje);
        Descripcion.setText(MisionIesimo.getDescripcion());
        SetIntegrantes(MisionIesimo.getIntegrantes());

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(MisionIesimo.getCompletado());

        Fecha.setText(strDate);
    }
}

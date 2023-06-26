package com.grupo2.proteam.GrupoSupervisor.Solicitudes.ListaSolicitudes;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.SolicitudData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class SolicitudesViewHolder extends RecyclerView.ViewHolder{
    private TextView Tipo, Titulo, Puntaje, Descripcion, Integrantes, Fecha;
    private Button Aceptar, Descartar;
    private CardView cvTipo;
    private View Divider;

    private SoftReference<itemSolicitudesListener> listenerGrupo;
    public SolicitudesViewHolder(@NonNull View view, SoftReference<itemSolicitudesListener> listener) {
        super(view);
        Tipo = view.findViewById(R.id.ListaSolicitud_txtTipo);
        Titulo = view.findViewById(R.id.ListaSolicitud_txtTitulo);
        Puntaje = view.findViewById(R.id.ListaSolicitud_txtPuntaje);
        Descripcion = view.findViewById(R.id.ListaSolicitud_txtDescripcion);
        Integrantes = view.findViewById(R.id.ListaSolicitud_txtIntegrantes);
        Fecha = view.findViewById(R.id.ListaSolicitud_txtFecha);

        Aceptar = view.findViewById(R.id.ListaSolicitud_btnAceptar);
        Descartar = view.findViewById(R.id.ListaSolicitud_btnDescartar);

        cvTipo = view.findViewById(R.id.ListaSolicitud_cvTipo);
        Divider = view.findViewById(R.id.ListaSolicitud_Divider);



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


    public void Setear(SolicitudData solicitudData)

    {
        MisionData MisionIesimo = solicitudData.getMision();
        //Terminar.setOnClickListener(view1 -> listenerGrupo.get().onClickTerminar(MisionIesimo));
        Aceptar.setOnClickListener(view -> listenerGrupo.get().onClickAceptar(solicitudData));
        Descartar.setOnClickListener(view -> listenerGrupo.get().onClickDescartar(solicitudData));
        SetTipo(MisionIesimo.isTipo());
        Titulo.setText(MisionIesimo.getTitulo());
        String strPuntaje = MisionIesimo.getPuntaje() + "p";
        Puntaje.setText(strPuntaje);
        Descripcion.setText(MisionIesimo.getDescripcion());
        SetIntegrantes(MisionIesimo.getIntegrantes());

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(solicitudData.getFecha());


        Fecha.setText(strDate);
    }
}

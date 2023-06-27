package com.grupo2.proteam.Equipo.Perfil.ListaPremios;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Premio;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;

public class PremiosViewHolder extends RecyclerView.ViewHolder{
    private final TextView txtPremio,txtPuntaje,NoPremio;
    private final View Item;
    public PremiosViewHolder(@NonNull View view) {
        super(view);
        txtPremio = view.findViewById(R.id.ListaPremios_txtPremio);
        txtPuntaje = view.findViewById(R.id.ListaPremios_txtPuntaje);
        NoPremio = view.findViewById(R.id.ListaPremios_txtNoPremio);
        Item = view.findViewById(R.id.ListaPremios_lyItem);
    }

    public void Setear(@Nullable Premio premio)
    {
        if (premio == null)
        {
            Item.setVisibility(View.INVISIBLE);
            NoPremio.setVisibility(View.VISIBLE);
        }
        else
        {
            Item.setVisibility(View.VISIBLE);
            NoPremio.setVisibility(View.INVISIBLE);
            txtPremio.setText(premio.getNombre());
            String strPuntaje = premio.getPuntaje() + "p";
            txtPuntaje.setText(strPuntaje);
        }

    }
}

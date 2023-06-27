package com.grupo2.proteam.Equipo.Perfil.ListaPuntajes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.itemGruposListener;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Puntaje;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;


public class PuntajesAdapter extends RecyclerView.Adapter<PuntajesViewHolder>{
    private final List<Puntaje> localDataSet;

    public PuntajesAdapter(List<Puntaje> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public PuntajesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_puntajes, viewGroup, false);
        return new PuntajesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PuntajesViewHolder viewHolder, int position) {
        viewHolder.Setear(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

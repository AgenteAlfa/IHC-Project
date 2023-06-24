package com.grupo2.proteam.GrupoTrabajador.Misiones.ListaMisiones;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.itemGruposListener;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;


public class MisionesAdapter extends RecyclerView.Adapter<MisionesViewHolder>{
    private final List<MisionData> localDataSet;
    private final SoftReference<itemMisionesListener> listenerGrupo;
    public static final String TAG = "MisionesAdapter";

    public MisionesAdapter(List<MisionData> dataSet, itemMisionesListener listener) {
        listenerGrupo = new SoftReference<>(listener);
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public MisionesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_misiones, viewGroup, false);
        return new MisionesViewHolder(view,listenerGrupo);
    }

    @Override
    public void onBindViewHolder(@NonNull MisionesViewHolder viewHolder, int position) {
        viewHolder.Setear(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

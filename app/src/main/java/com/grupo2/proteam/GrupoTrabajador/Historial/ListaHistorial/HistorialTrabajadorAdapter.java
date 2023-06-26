package com.grupo2.proteam.GrupoTrabajador.Historial.ListaHistorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;


public class HistorialTrabajadorAdapter extends RecyclerView.Adapter<HistorialTrabajadorViewHolder>{
    private final List<MisionData> localDataSet;
    private final SoftReference<itemHistoriaTrabajadorlListener> listenerGrupo;
    public static final String TAG = "HistorialTrabajadorAdapter";

    public HistorialTrabajadorAdapter(List<MisionData> dataSet, itemHistoriaTrabajadorlListener listener) {
        listenerGrupo = new SoftReference<>(listener);
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public HistorialTrabajadorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_historial, viewGroup, false);
        return new HistorialTrabajadorViewHolder(view,listenerGrupo);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialTrabajadorViewHolder viewHolder, int position) {
        viewHolder.Setear(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

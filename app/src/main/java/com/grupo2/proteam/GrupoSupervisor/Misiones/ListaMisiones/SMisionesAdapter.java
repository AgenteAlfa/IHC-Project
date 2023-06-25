package com.grupo2.proteam.GrupoSupervisor.Misiones.ListaMisiones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;


public class SMisionesAdapter extends RecyclerView.Adapter<SMisionesViewHolder>{
    private final List<MisionData> localDataSet;
    private final SoftReference<itemSMisionesListener> listenerGrupo;
    public static final String TAG = "SMisionesAdapter";

    public SMisionesAdapter(List<MisionData> dataSet, itemSMisionesListener listener) {
        listenerGrupo = new SoftReference<>(listener);
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public SMisionesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_supervisor_misiones, viewGroup, false);
        return new SMisionesViewHolder(view,listenerGrupo);
    }

    @Override
    public void onBindViewHolder(@NonNull SMisionesViewHolder viewHolder, int position) {
        viewHolder.Setear(localDataSet.size() <= position? null : localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size() + 1;
    }
}

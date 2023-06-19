package com.grupo2.proteam.Equipo.Grupos.ListaGrupos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.GrupoData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;


public class GruposAdapter extends RecyclerView.Adapter<GruposViewHolder>{
    private final List<GrupoData> localDataSet;
    private SoftReference<itemGruposListener> listenerGrupo;
    public static final String TAG = "GruposAdapter";
    private boolean Admin;

    public GruposAdapter(List<GrupoData> dataSet, boolean isAdmin, itemGruposListener listener) {
        listenerGrupo = new SoftReference<>(listener);
        localDataSet = dataSet;
        Admin = isAdmin;
    }

    @NonNull
    @Override
    public GruposViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_grupos, viewGroup, false);
        Log.d(TAG, "onCreateViewHolder: " + (listenerGrupo.get() == null? "ES NULL" : "NO  ES NULL"));
        return new GruposViewHolder(view,listenerGrupo);
    }

    @Override
    public void onBindViewHolder(@NonNull GruposViewHolder viewHolder, int position) {
        viewHolder.Setear(localDataSet.size() <= position? null : localDataSet.get(position) , Admin);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size() + 1;
    }
}

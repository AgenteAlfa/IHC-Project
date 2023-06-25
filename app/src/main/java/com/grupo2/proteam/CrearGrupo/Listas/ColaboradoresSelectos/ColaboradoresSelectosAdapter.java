package com.grupo2.proteam.CrearGrupo.Listas.ColaboradoresSelectos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.GruposViewHolder;
import com.grupo2.proteam.Equipo.Grupos.ListaGrupos.itemGruposListener;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;

public class ColaboradoresSelectosAdapter extends RecyclerView.Adapter<ColaboradoresSelectosViewHolder>{
    private final List<UsuarioData> localDataSet;
    private SoftReference<itemColaboradoresSelectosListener> listenerSelectos;
    public static final String TAG = "SMisionesAdapter";

    public ColaboradoresSelectosAdapter(List<UsuarioData> dataSet, itemColaboradoresSelectosListener listener) {
        localDataSet = dataSet;
        listenerSelectos = new SoftReference<>(listener);
    }

    @NonNull
    @Override
    public ColaboradoresSelectosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_colaboradores, viewGroup, false);
        //Log.d(TAG, "onCreateViewHolder: " + (listenerGrupo.get() == null? "ES NULL" : "NO  ES NULL"));
        return new ColaboradoresSelectosViewHolder(view,listenerSelectos);
    }

    @Override
    public void onBindViewHolder(@NonNull ColaboradoresSelectosViewHolder holder, int position) {
        holder.Setear(localDataSet.size() <= position? null : localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return Math.max(localDataSet.size(),1);
    }
}

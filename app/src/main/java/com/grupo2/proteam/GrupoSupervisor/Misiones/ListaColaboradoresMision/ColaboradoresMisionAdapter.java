package com.grupo2.proteam.GrupoSupervisor.Misiones.ListaColaboradoresMision;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;

public class ColaboradoresMisionAdapter extends RecyclerView.Adapter<ColaboradoresMisionViewHolder>{
    private final List<UsuarioData> localDataSet;
    private SoftReference<itemColaboradoresMisionListener> listenerSelectos;
    public static final String TAG = "SMisionesAdapter";

    public ColaboradoresMisionAdapter(List<UsuarioData> dataSet, itemColaboradoresMisionListener listener) {
        localDataSet = dataSet;
        listenerSelectos = new SoftReference<>(listener);
    }

    @NonNull
    @Override
    public ColaboradoresMisionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_addcolaboradores, viewGroup, false);
        //Log.d(TAG, "onCreateViewHolder: " + (listenerGrupo.get() == null? "ES NULL" : "NO  ES NULL"));
        return new ColaboradoresMisionViewHolder(view,listenerSelectos);
    }

    @Override
    public void onBindViewHolder(@NonNull ColaboradoresMisionViewHolder holder, int position) {
        holder.Setear(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + localDataSet.size());
        return localDataSet.size();
    }
}

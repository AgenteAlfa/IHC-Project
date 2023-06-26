package com.grupo2.proteam.GrupoTrabajador.Info.ListaColaboradores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;

public class TrabajadorAdapter extends RecyclerView.Adapter<TrabajadorViewHolder>{
    private final List<UsuarioData> localDataSet;
    private SoftReference<itemTrabajadorListener> Listener;
    private boolean IsAdmin;

    public TrabajadorAdapter(List<UsuarioData> dataSet,  boolean isAdmin,itemTrabajadorListener listener) {
        localDataSet = dataSet;
        Listener = new SoftReference<>(listener);
        IsAdmin = isAdmin;
    }

    @NonNull
    @Override
    public TrabajadorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_simple_colaboradores, viewGroup, false);
        return new TrabajadorViewHolder(view,Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajadorViewHolder holder, int position) {
        holder.Setear(localDataSet.get(position), IsAdmin);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

package com.grupo2.proteam.GrupoSupervisor.Colaboradores.ListaColaboradoresSupervisor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;

public class TrabajadoresSupervisorAdapter extends RecyclerView.Adapter<TrabajadoresSupervisorViewHolder>{
    private final List<UsuarioData> localDataSet;
    private SoftReference<itemTrabajadoresSupervisorListener> Listener;
    private boolean IsAdmin;

    public TrabajadoresSupervisorAdapter(List<UsuarioData> dataSet, boolean isAdmin, itemTrabajadoresSupervisorListener listener) {
        localDataSet = dataSet;
        Listener = new SoftReference<>(listener);
        IsAdmin = isAdmin;
    }

    @NonNull
    @Override
    public TrabajadoresSupervisorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_simple_colaboradores, viewGroup, false);
        return new TrabajadoresSupervisorViewHolder(view,Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajadoresSupervisorViewHolder holder, int position) {
        holder.Setear(localDataSet.get(position), IsAdmin);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

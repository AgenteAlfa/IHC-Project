package com.grupo2.proteam.CrearGrupo.Listas.Colaboradores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;

public class AddColaboradoresAdapter extends RecyclerView.Adapter<AddColaboradoresViewHolder>{
    private final List<UsuarioData> localDataSet;
    private SoftReference<itemAddColaboradoresListener> listenerSelectos;
    public static final String TAG = "GruposAdapter";

    public AddColaboradoresAdapter(List<UsuarioData> dataSet, itemAddColaboradoresListener listener) {
        localDataSet = dataSet;
        listenerSelectos = new SoftReference<>(listener);
    }

    @NonNull
    @Override
    public AddColaboradoresViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_addcolaboradores, viewGroup, false);
        //Log.d(TAG, "onCreateViewHolder: " + (listenerGrupo.get() == null? "ES NULL" : "NO  ES NULL"));
        return new AddColaboradoresViewHolder(view,listenerSelectos);
    }

    @Override
    public void onBindViewHolder(@NonNull AddColaboradoresViewHolder holder, int position) {
        holder.Setear(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

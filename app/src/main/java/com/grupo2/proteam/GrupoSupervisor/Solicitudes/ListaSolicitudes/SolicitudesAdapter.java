package com.grupo2.proteam.GrupoSupervisor.Solicitudes.ListaSolicitudes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.SolicitudData;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.List;


public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesViewHolder>{
    private final List<SolicitudData> localDataSet;
    private final SoftReference<itemSolicitudesListener> listenerGrupo;
    public static final String TAG = "SMisionesAdapter";

    public SolicitudesAdapter(List<SolicitudData> dataSet, itemSolicitudesListener listener) {
        listenerGrupo = new SoftReference<>(listener);
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public SolicitudesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_solicitud, viewGroup, false);
        return new SolicitudesViewHolder(view,listenerGrupo);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudesViewHolder viewHolder, int position) {
        viewHolder.Setear(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

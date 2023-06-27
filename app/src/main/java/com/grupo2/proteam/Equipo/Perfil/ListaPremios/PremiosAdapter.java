package com.grupo2.proteam.Equipo.Perfil.ListaPremios;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Premio;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.util.Comparator;
import java.util.List;


public class PremiosAdapter extends RecyclerView.Adapter<PremiosViewHolder>{
    private final List<Premio> localDataSet;


    public PremiosAdapter(List<Premio> dataSet) {
        dataSet.sort(new Comparator<Premio>() {
            @Override
            public int compare(Premio premio, Premio t1) {
                return premio.getPuntaje() - t1.getPuntaje();
            }
        });
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public PremiosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_premios, viewGroup, false);
        return new PremiosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PremiosViewHolder viewHolder, int position) {
        viewHolder.Setear(localDataSet.size() <= position? null : localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return Math.max(localDataSet.size(),1);
    }
}

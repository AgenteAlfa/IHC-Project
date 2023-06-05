package com.grupo2.proteam.ui.Equipos.ListaEquipos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.R;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposViewHolder> {

    private final List<Equipo> localDataSet;
    private final SoftReference<itemEquipoListener> wlistener;
    public static final String TAG = "EquiposAdapter";

    public EquiposAdapter(List<Equipo> dataSet, itemEquipoListener listener) {
        wlistener = new SoftReference<>(listener);
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EquiposViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rvitem_equipos, viewGroup, false);
        Log.d(TAG, "onCreateViewHolder: " + (wlistener.get() == null? "ES NULL" : "NO  ES NULL"));
        return new EquiposViewHolder(view,wlistener);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EquiposViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.Setear(localDataSet.size() <= position? null : localDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size() + 1;
    }
}
package com.grupo2.proteam.GrupoSupervisor.Solicitudes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.SolicitudData;
import com.grupo2.proteam.GrupoSupervisor.GrupoSupervisorViewModel;
import com.grupo2.proteam.GrupoSupervisor.Solicitudes.ListaSolicitudes.SolicitudesAdapter;
import com.grupo2.proteam.GrupoSupervisor.Solicitudes.ListaSolicitudes.itemSolicitudesListener;
import com.grupo2.proteam.R;

import java.util.List;


public class SSolicitudesFragment extends Fragment {

    private GrupoSupervisorViewModel DataVM;
    private RecyclerView ListaSolicitudes;
    public static final String TAG = "Fragment Solicitudes";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_grupo_supervisor_solicitudes, container, false);
        ListaSolicitudes = root.findViewById(R.id.frgSolicitudes_rvListaSolicitudes);
        DataVM = new ViewModelProvider(getActivity()).get(GrupoSupervisorViewModel.class);
        DataVM.get_Solicitudes().observe(getViewLifecycleOwner(), new Observer<List<SolicitudData>>() {
            @Override
            public void onChanged(List<SolicitudData> solicitudData) {
                SetLista(solicitudData);
                Log.d(TAG, "onChanged: ");
                for (SolicitudData solicitudDatum : solicitudData) {
                    Log.d(TAG, "onChanged: " + solicitudDatum.getID());
                }
            }
        });

        return root;
    }


    private void SetLista(List<SolicitudData> solicitudData)
    {
        SolicitudesAdapter MAdapter = new SolicitudesAdapter(solicitudData, new itemSolicitudesListener() {
            @Override
            public void onClickAceptar(SolicitudData M) {
                DataVM.AceptarSolicitud(M, () -> {
                    DataVM.ActualizarMisiones();
                    Toast.makeText(getContext(), "Solicitud Aceptada", Toast.LENGTH_SHORT).show();
                });


            }

            @Override
            public void onClickDescartar(SolicitudData M) {
                DataVM.EliminarSolicitud(M, () -> {
                    DataVM.ActualizarMisiones();
                    Toast.makeText(getContext(), "Solicitud Descartada", Toast.LENGTH_SHORT).show();
                });

            }
        });

        ListaSolicitudes.setAdapter(MAdapter);
        ListaSolicitudes.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
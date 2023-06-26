package com.grupo2.proteam.GrupoTrabajador.Info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.GrupoTrabajador.GrupoTrabajadorViewModel;
import com.grupo2.proteam.GrupoTrabajador.Info.ListaColaboradores.TrabajadorAdapter;
import com.grupo2.proteam.GrupoTrabajador.Info.ListaColaboradores.itemTrabajadorListener;
import com.grupo2.proteam.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InfoFragment extends Fragment {
    private GrupoTrabajadorViewModel DataVM;

    private TextView Nombre, Descripcion;
    private RecyclerView ListaTrabajadore;
    private Button Puntajes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_grupo_trabajador_info, container, false);
        DataVM = new ViewModelProvider(getActivity()).get(GrupoTrabajadorViewModel.class);
        Nombre = root.findViewById(R.id.frgGrupoTrabajadorInfo_txtNombre);
        Descripcion = root.findViewById(R.id.frgGrupoTrabajadorInfo_txtDescripcion);
        ListaTrabajadore = root.findViewById(R.id.frgGrupoTrabajadorInfo_rvListaTrabajadores);
        Puntajes = root.findViewById(R.id.frgGrupoTrabajadorInfo_btnPuntajes);

        DataVM.get_GrupoData().observe(getViewLifecycleOwner(), new Observer<GrupoData>() {
            @Override
            public void onChanged(GrupoData grupoData) {
                Nombre.setText(grupoData.getNombre());
                Descripcion.setText(grupoData.getDescripcion());
                List<UsuarioData> lstTrabajadores = new ArrayList<>();
                HashMap<String,UsuarioData> HashColaboradores = DataVM.get_Colaboradores().getValue();
                for (String colaborador : grupoData.getColaboradores()) {
                    UsuarioData User = HashColaboradores.get(colaborador);
                    User.setSupervisor(false);
                    lstTrabajadores.add(User);

                }
                for (String supervisor : grupoData.getSupervisores()) {
                    UsuarioData User = HashColaboradores.get(supervisor);
                    User.setSupervisor(true);
                    lstTrabajadores.add(HashColaboradores.get(supervisor));
                }

                TrabajadorAdapter Adapter = new TrabajadorAdapter(lstTrabajadores, false, usuarioData -> {});
                ListaTrabajadore.setAdapter(Adapter);
                ListaTrabajadore.setLayoutManager(new LinearLayoutManager(getContext()));



            }
        });

        return root;
    }


}
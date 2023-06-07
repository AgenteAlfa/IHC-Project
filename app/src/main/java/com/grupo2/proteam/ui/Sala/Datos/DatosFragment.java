package com.grupo2.proteam.ui.Sala.Datos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.grupo2.proteam.FStore.PrivadoUsuario;
import com.grupo2.proteam.R;
import com.grupo2.proteam.databinding.FragmentDatosBinding;
import com.grupo2.proteam.ui.Sala.SalaViewModel;

public class DatosFragment extends Fragment {
    SalaViewModel DataVM;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DataVM = new ViewModelProvider(getActivity()).get(SalaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_datos, container, false);
        TextView NyA = root.findViewById(R.id.frgDatos_txtNyA),
                Email = root.findViewById(R.id.frgDatos_txtEmail),
                Admin = root.findViewById(R.id.frgDatos_txtAdmin);

        DataVM.get_UsuarioInfo().observe(getViewLifecycleOwner(), new Observer<PrivadoUsuario>() {
            @Override
            public void onChanged(PrivadoUsuario privadoUsuario) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                if (privadoUsuario != null)
                {
                    NyA.setText(privadoUsuario.getNyA());
                    Email.setText(mAuth.getCurrentUser().getEmail());
                    Admin.setText(privadoUsuario.isAdmin()? "Administrador" : "Usuario");
                }

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
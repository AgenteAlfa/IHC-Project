package com.grupo2.proteam;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.PrivadoUsuario;

import java.util.ArrayList;
import java.util.List;

public class EquiposViewModel extends ViewModel {
    private PrivadoUsuario UsuarioInfo;
    private final MutableLiveData<List<Equipo>> _lstEquipos;

    public EquiposViewModel() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        assert mAuth.getCurrentUser() != null;
        String uuid = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Usuarios").document(uuid).get().addOnSuccessListener(documentSnapshot -> {
            UsuarioInfo = documentSnapshot.toObject(PrivadoUsuario.class);
        });

        _lstEquipos = new MutableLiveData<>(new ArrayList<>());
        ActualizarListaEquipos();
    }

    public void ActualizarListaEquipos()
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        assert mAuth.getCurrentUser() != null;
        String uuid = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query consulta = db.collection("Equipos").whereEqualTo("propietario", uuid);
        consulta.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Equipo> lstEquipos = queryDocumentSnapshots.toObjects(Equipo.class);
                _lstEquipos.setValue(lstEquipos);
            }
        });
    }

    public PrivadoUsuario getUsuarioInfo() {
        return UsuarioInfo;
    }

    public MutableLiveData<List<Equipo>> get_lstEquipos() {
        return _lstEquipos;
    }
}

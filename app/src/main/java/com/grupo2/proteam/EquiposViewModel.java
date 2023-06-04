package com.grupo2.proteam;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grupo2.proteam.FStore.PrivadoUsuario;

public class EquiposViewModel extends ViewModel {
    private PrivadoUsuario UsuarioInfo;

    public EquiposViewModel() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        assert mAuth.getCurrentUser() != null;
        String uuid = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Usuarios").document(uuid).get().addOnSuccessListener(documentSnapshot -> {
            UsuarioInfo = documentSnapshot.toObject(PrivadoUsuario.class);
        });
    }


    public PrivadoUsuario getUsuarioInfo() {
        return UsuarioInfo;
    }

    
}

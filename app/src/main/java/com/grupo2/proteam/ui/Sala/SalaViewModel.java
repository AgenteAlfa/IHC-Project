package com.grupo2.proteam.ui.Sala;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.EquipoData;
import com.grupo2.proteam.FStore.PrivadoUsuario;

import java.util.ArrayList;
import java.util.List;

public class SalaViewModel extends ViewModel {
    private PrivadoUsuario UsuarioInfo;
    private final MutableLiveData<List<EquipoData>> _lstEquipos;

    public SalaViewModel() {
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
                ArrayList<EquipoData> Arr =  new ArrayList<>();
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                    EquipoData equipoData = null;
                    Equipo E = document.toObject(Equipo.class);
                    if (E != null)
                        equipoData = new EquipoData(E,document.getId()) ;
                    Arr.add(equipoData);

                }
                _lstEquipos.setValue(Arr);
            }
        });
    }

    public PrivadoUsuario getUsuarioInfo() {
        return UsuarioInfo;
    }

    public MutableLiveData<List<EquipoData>> get_lstEquipos() {
        return _lstEquipos;
    }
}

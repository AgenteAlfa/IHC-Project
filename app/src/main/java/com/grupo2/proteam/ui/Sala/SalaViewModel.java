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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SalaViewModel extends ViewModel {
    private final MutableLiveData<PrivadoUsuario> _UsuarioInfo;
    private final MutableLiveData<List<EquipoData>> _lstEquipos;

    public SalaViewModel() {
        _lstEquipos = new MutableLiveData<>(new ArrayList<>());
        _UsuarioInfo = new MutableLiveData<>(new PrivadoUsuario(false, new Date(), ""));

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        assert mAuth.getCurrentUser() != null;
        String uuid = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Usuarios").document(uuid).get().addOnSuccessListener(documentSnapshot -> {
            PrivadoUsuario UsuarioInfo = documentSnapshot.toObject(PrivadoUsuario.class);
            _UsuarioInfo.setValue(UsuarioInfo);
            ActualizarListaEquipos();
        });



    }

    public void ActualizarListaEquipos()
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        assert mAuth.getCurrentUser() != null;
        String uuid = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query consulta;
        if (_UsuarioInfo.getValue().isAdmin())
        {
            consulta = db.collection("Equipos").whereEqualTo("propietario", uuid);
        }
        else
        {
            consulta = db.collection("Equipos").whereArrayContains("colaboradores", uuid);
        }

        consulta.get().addOnSuccessListener(OSL_ListaEquipos);

    }
    OnSuccessListener<QuerySnapshot> OSL_ListaEquipos = new OnSuccessListener<QuerySnapshot>() {
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
    };


    public MutableLiveData<PrivadoUsuario> get_UsuarioInfo() {
        return _UsuarioInfo;
    }

    public MutableLiveData<List<EquipoData>> get_lstEquipos() {
        return _lstEquipos;
    }
}

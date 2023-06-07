package com.grupo2.proteam.ui.Equipo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.EquipoData;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.FStore.GrupoData;
import java.util.ArrayList;
import java.util.List;

public class EquipoViewModel extends ViewModel {
    private final MutableLiveData<EquipoData> _EquipoData;
    private final MutableLiveData<List<GrupoData>> _lstGrupos;
    private DocumentReference DREquipo;

    public EquipoViewModel() {
        _EquipoData = new MutableLiveData<>();
        _lstGrupos = new MutableLiveData<>(new ArrayList<>());
    }
    public void SetEquipoID(String ID)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DREquipo =  db.collection("Equipos").document(ID);
        DREquipo.get().addOnSuccessListener(documentSnapshot -> {
            Equipo E = documentSnapshot.toObject(Equipo.class);
            _EquipoData.setValue(E != null? new EquipoData(E,ID) :  null);
        });
        ActualizarListaGrupos();
    }
    public void ActualizarListaGrupos()
    {
        //Grupos
        DREquipo.collection("Grupos").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                ArrayList<GrupoData> Arr =  new ArrayList<>();
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                    GrupoData grupoData = null;
                    Grupo G = document.toObject(Grupo.class);
                    if (G != null)
                        grupoData = new GrupoData(G, document.getId());
                    Arr.add(grupoData);

                    _lstGrupos.setValue(Arr);
                }
            }
        });
    }

    public MutableLiveData<EquipoData> get_EquipoData() {
        return _EquipoData;
    }

    public MutableLiveData<List<GrupoData>> get_lstGrupos() {
        return _lstGrupos;
    }
}

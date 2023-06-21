package com.grupo2.proteam.Equipo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.grupo2.proteam.FStore.Codigo;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.Compuestos.EquipoData;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.PrivadoUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EquipoViewModel extends ViewModel {
    private final MutableLiveData<EquipoData> _EquipoData;
    private final MutableLiveData<List<GrupoData>> _lstGrupos;
    private final MutableLiveData<Codigo> _Codigo;
    private final MutableLiveData<Boolean> _isAdmin;
    private final MutableLiveData<List<PrivadoUsuario>> _Colaboradores;
    private DocumentReference DREquipo;
    public static final String TAG = "EquipoViewModel";



    public EquipoViewModel() {
        _EquipoData = new MutableLiveData<>();
        _lstGrupos = new MutableLiveData<>(new ArrayList<>());
        _Codigo = new MutableLiveData<>();
        _isAdmin = new MutableLiveData<>();
        _Colaboradores = new MutableLiveData<>(new ArrayList<>());

    }
    public void BuscarCodigo()
    {
        Log.d(TAG, "BuscarCodigo: Inicia");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EquipoData E = _EquipoData.getValue();
        assert E != null;
        db.collection("CodigosJoin").document(E.getID()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Codigo c = documentSnapshot.toObject(Codigo.class);
                if (c == null)
                {
                    GenerarCodigo();
                }
                else
                {

                    _Codigo.setValue(c);
                }
            }
        });
    }
    private void GenerarCodigo()
    {
        Log.d(TAG, "GenerarCodigo: Inicia");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EquipoData E = _EquipoData.getValue();
        Codigo C = new Codigo(UUID.randomUUID().toString().substring(0,6));
        db.collection("CodigosJoin").whereEqualTo("uuidEquipo", C).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.size() == 0)
            {
                db.collection("CodigosJoin").document(E.getID()).set(C).addOnSuccessListener(unused -> _Codigo.setValue(C));
            }
            else
            {
                GenerarCodigo();
            }
        });
    }

    public void SetEquipoID(String ID)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DREquipo =  db.collection("Equipos").document(ID);
        DREquipo.get().addOnSuccessListener(documentSnapshot -> {
            Equipo E = documentSnapshot.toObject(Equipo.class);
            _EquipoData.setValue(E != null? new EquipoData(E,ID) :  null);
            BuscarCodigo();
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
    public void ActualizarListaColaboradores()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Usuarios").whereIn(FieldPath.documentId(), _EquipoData.getValue().
                getColaboradores()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d(TAG, "onSuccess: Actualizando lista de colaboradores");
                for (PrivadoUsuario usuario : queryDocumentSnapshots.toObjects(PrivadoUsuario.class)) {
                    Log.d(TAG, "onSuccess: usuario : " + usuario.getNyA());
                }

                _Colaboradores.setValue(queryDocumentSnapshots.toObjects(PrivadoUsuario.class));
            }
        });
    }

    public MutableLiveData<EquipoData> get_EquipoData() {
        return _EquipoData;
    }

    public MutableLiveData<List<GrupoData>> get_lstGrupos() {
        return _lstGrupos;
    }

    public MutableLiveData<Codigo> get_Codigo() {
        return _Codigo;
    }

    public MutableLiveData<Boolean> get_isAdmin() {
        return _isAdmin;
    }

    public MutableLiveData<List<PrivadoUsuario>> get_Colaboradores() {
        return _Colaboradores;
    }
}

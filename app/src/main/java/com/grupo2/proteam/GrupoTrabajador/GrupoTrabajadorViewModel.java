package com.grupo2.proteam.GrupoTrabajador;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.grupo2.proteam.FStore.Compuestos.EquipoData;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Compuestos.MisionData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.FStore.Mision;
import com.grupo2.proteam.FStore.PrivadoUsuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrupoTrabajadorViewModel extends ViewModel {
    private final MutableLiveData<GrupoData> _GrupoData;
    private final MutableLiveData<EquipoData> _EquipoData;
    private final MutableLiveData<HashMap<String,UsuarioData>> _Colaboradores;
    private final MutableLiveData<List<MisionData>> _Misiones;
    private DocumentReference DRGrupo;
    private CollectionReference CRMisiones, CRHistorial, CRSolicitudes;

    public GrupoTrabajadorViewModel() {
        _GrupoData = new MutableLiveData<>();
        _EquipoData = new MutableLiveData<>();
        _Colaboradores = new MutableLiveData<>();
        _Misiones = new MutableLiveData<>();
    }

    public void Inicializar(String idEquipo, String idGrupo)
    {
        SetEquipoID(idEquipo, () ->
                ActualizarListaColaboradores(() ->
                        SetGrupoID(idEquipo, idGrupo, this::ActualizarMisiones)));;
    }

    public void SetGrupoID(String idEquipo, String idGrupo, PostListener listener)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DRGrupo = db.collection("Equipos").document(idEquipo).collection("Grupos").document(idGrupo);
        CRMisiones = DRGrupo.collection("Misiones");
        CRHistorial = DRGrupo.collection("Historial");
        CRSolicitudes = DRGrupo.collection("Solicitudes");

        DRGrupo.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                GrupoData G = new GrupoData(documentSnapshot.toObject(Grupo.class), documentSnapshot.getId());
                _GrupoData.setValue(G);
                listener.post();
            }
        });

    }
    public void ActualizarMisiones()
    {
        DRGrupo.collection("Misiones").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<MisionData> lstMisiones = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    Mision M =  document.toObject(Mision.class);
                    List<UsuarioData> arr = new ArrayList<>();
                    for (String colaboradorID : M.getColaboradores()) {
                        arr.add(_Colaboradores.getValue().get(colaboradorID));
                    }
                    MisionData obj = new MisionData(document.toObject(Mision.class), document.getId(), "Mision en curso", arr);
                    lstMisiones.add(obj);
                }
                _Misiones.setValue(lstMisiones);
            }
        });
    }

    public void SetEquipoID(String ID, PostListener listener)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Equipos").document(ID).get().addOnSuccessListener(documentSnapshot -> {
            Equipo E = documentSnapshot.toObject(Equipo.class);
            _EquipoData.setValue(E != null? new EquipoData(E,ID) :  null);
            listener.post();
        });
    }


    public void ActualizarListaColaboradores(PostListener postListener)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Usuarios").whereIn(FieldPath.documentId(), _EquipoData.getValue().
                getColaboradores()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                HashMap<String,UsuarioData> hash = new HashMap<>();
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                    PrivadoUsuario p = document.toObject(PrivadoUsuario.class);
                    UsuarioData user = new UsuarioData(p, document.getId(),false);
                    hash.put(user.getID(), user);
                }
                _Colaboradores.setValue(hash);
                postListener.post();

            }
        });
    }
    private interface PostListener
    {
        void post();
    }

    public MutableLiveData<GrupoData> get_GrupoData() {
        return _GrupoData;
    }

    public MutableLiveData<EquipoData> get_EquipoData() {
        return _EquipoData;
    }

    public MutableLiveData<HashMap<String, UsuarioData>> get_Colaboradores() {
        return _Colaboradores;
    }

    public MutableLiveData<List<MisionData>> get_Misiones() {
        return _Misiones;
    }
}
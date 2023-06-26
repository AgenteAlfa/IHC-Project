package com.grupo2.proteam.GrupoSupervisor;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
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
import com.grupo2.proteam.FStore.Compuestos.SolicitudData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.FStore.Mision;
import com.grupo2.proteam.FStore.PrivadoUsuario;
import com.grupo2.proteam.FStore.Solicitud;
import com.grupo2.proteam.GrupoTrabajador.GrupoTrabajadorViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrupoSupervisorViewModel extends ViewModel {
    private final MutableLiveData<GrupoData> _GrupoData;
    private final MutableLiveData<EquipoData> _EquipoData;
    private final MutableLiveData<HashMap<String, UsuarioData>> _Colaboradores;
    private final MutableLiveData<List<MisionData>> _Misiones;
    private final MutableLiveData<List<MisionData>> _Historial;
    private final MutableLiveData<List<SolicitudData>> _Solicitudes;
    private DocumentReference DRGrupo;
    private CollectionReference CRMisiones, CRHistorial, CRSolicitudes;

    public static final String TAG  = "GrupoSupervisorViewModel";



    public GrupoSupervisorViewModel() {
        _GrupoData = new MutableLiveData<>();
        _EquipoData = new MutableLiveData<>();
        _Colaboradores = new MutableLiveData<>();
        _Misiones = new MutableLiveData<>();
        _Solicitudes = new MutableLiveData<>();
        _Historial = new MutableLiveData<>();;
    }

    public void Inicializar(String idEquipo, String idGrupo)
    {
        SetEquipoID(idEquipo, () ->
                ActualizarListaColaboradores(() ->
                        SetGrupoID(idEquipo, idGrupo, () -> ActualizarMisiones())));;
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
    public void CrearMision(Mision M, PostListener postListener)
    {
        CRMisiones.add(M).addOnSuccessListener(documentReference -> postListener.post());
    }
    public void EliminarMision(MisionData M, PostListener postListener)
    {
        CRMisiones.document(M.getID()).delete().addOnSuccessListener(unused -> postListener.post());
    }
    public void ActualizarMisiones()
    {
        CRMisiones.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                ActualizarSolicitudes();
                ActualizarHistorial();
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

    public void ActualizarSolicitudes()
    {
        CRSolicitudes.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                 List<Solicitud> arrSolicitudes = queryDocumentSnapshots.toObjects(Solicitud.class);

                 List<MisionData> arrMisiones = _Misiones.getValue();
                 List<SolicitudData> arrMisionesSolicitadas = new ArrayList<>();
                 for (Solicitud solicitud : arrSolicitudes) {
                     Log.d(TAG, "onSuccess: Solicitud ID = " + solicitud.getID());
                    for (MisionData mision : arrMisiones) {
                        if (mision.getID().equals(solicitud.getID()))
                        {
                            SolicitudData SD = new SolicitudData(solicitud, mision);
                            arrMisionesSolicitadas.add(SD);
                        }
                    }
                }

                 _Solicitudes.setValue(arrMisionesSolicitadas);

            }
        });
    }
    public void ActualizarHistorial()
    {
        CRHistorial.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<MisionData> lstHistorial = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    Mision M =  document.toObject(Mision.class);
                    List<UsuarioData> arr = new ArrayList<>();
                    for (String colaboradorID : M.getColaboradores()) {
                        arr.add(_Colaboradores.getValue().get(colaboradorID));
                    }
                    MisionData obj = new MisionData(document.toObject(Mision.class), document.getId(), "Mision Completada", arr);
                    lstHistorial.add(obj);
                }
                _Historial.setValue(lstHistorial);
            }
        });
    }
    public void EliminarSolicitud(SolicitudData solicitudData, PostListener listener)
    {
        CRSolicitudes.document(solicitudData.getID()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                listener.post();
            }
        });
    }

    public void AceptarSolicitud(SolicitudData solicitudData, PostListener listener)
    {
        Mision Mision = solicitudData.getMision();
        Mision.setCompletado(solicitudData.getFecha());
        CRHistorial.add(Mision).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                CRMisiones.document(solicitudData.getID()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        listener.post();
                    }
                });
            }
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
    public interface PostListener
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

    public MutableLiveData<List<SolicitudData>> get_Solicitudes() {
        return _Solicitudes;
    }

    public MutableLiveData<List<MisionData>> get_Historial() {
        return _Historial;
    }
}
package com.grupo2.proteam.Equipo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.grupo2.proteam.FStore.Codigo;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.Compuestos.EquipoData;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.FStore.Compuestos.GrupoData;
import com.grupo2.proteam.FStore.Premio;
import com.grupo2.proteam.FStore.PrivadoUsuario;
import com.grupo2.proteam.FStore.Puntaje;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EquipoViewModel extends ViewModel {
    private final MutableLiveData<EquipoData> _EquipoData;
    private final MutableLiveData<List<GrupoData>> _lstGrupos;
    private final MutableLiveData<Codigo> _Codigo;
    private final MutableLiveData<Boolean> _isAdmin;
    private final MutableLiveData<List<UsuarioData>> _Colaboradores;
    private final MutableLiveData<List<Puntaje>> _Puntajes;
    private final MutableLiveData<List<Premio>> _Premios;
    private DocumentReference DREquipo;
    public static final String TAG = "EquipoViewModel";



    public EquipoViewModel() {
        _EquipoData = new MutableLiveData<>();
        _lstGrupos = new MutableLiveData<>(new ArrayList<>());
        _Codigo = new MutableLiveData<>();
        _isAdmin = new MutableLiveData<>();
        _Colaboradores = new MutableLiveData<>(new ArrayList<>());
        _Puntajes = new MutableLiveData<>();
        _Premios = new MutableLiveData<>();
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
        ActualizarPremios();

    }
    public void AgregarPremio(Premio p)
    {
        DREquipo.collection("Premios").add(p).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                ActualizarPremios();
            }
        });

    }


    public void ActualizarPremios()
    {
        DREquipo.collection("Premios").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                _Premios.setValue(queryDocumentSnapshots.toObjects(Premio.class));
            }
        });
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
        List<String> Str_colaboradores = _EquipoData.getValue().getColaboradores();
        List<UsuarioData> lst = new ArrayList<>();
        if (Str_colaboradores.size() > 0)
        {
            db.collection("Usuarios").whereIn(FieldPath.documentId(), Str_colaboradores).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    Log.d(TAG, "onSuccess: Actualizando lista de colaboradores");
                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                        UsuarioData user = new UsuarioData(queryDocumentSnapshot.toObject(PrivadoUsuario.class), queryDocumentSnapshot.getId(), false);
                        lst.add(user);
                    }
                    Log.d(TAG, "onSuccess: hay colaboradores "  + lst.size());
                    _Colaboradores.setValue(lst);
                    ActualizarListaPuntajes();
                }
            });
        }
        else
        {
            _Colaboradores.setValue(lst);
            ActualizarListaPuntajes();
        }

    }
    public void ActualizarListaPuntajes()
    {
        DREquipo.collection("Puntaje").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Puntaje> puntajes = queryDocumentSnapshots.toObjects(Puntaje.class);
                _Puntajes.setValue(puntajes);
                Log.d(TAG, "onSuccess: HAY " + puntajes.size() + " puntajes");
                Log.d(TAG, "onSuccess: HAY " + _Colaboradores.getValue().size() + " colaboradores");
                if (puntajes.size() < _Colaboradores.getValue().size())
                    RellenarPuntajes();
            }
        });
    }
    public void RellenarPuntajes(){
        Log.d(TAG, "RellenarPuntajes: Rellenando");
        List<Puntaje> puntajeList = new ArrayList<>();
        for (UsuarioData colaborador : _Colaboradores.getValue()) {
            boolean presente = false;
            for (Puntaje puntaje : _Puntajes.getValue()) {
                if (puntaje.getId().equals(colaborador.getID()))
                {
                    puntajeList.add(puntaje);
                    presente = true;
                }
            }
            //Si no esta presente agregar con 0 puntos;
            if (!presente)
            {
                Puntaje P = new Puntaje(0,colaborador.getNyA(), colaborador.getID());
                puntajeList.add(P);
                DREquipo.collection("Puntaje").document(P.getId()).set(P);
            }
        }
        _Puntajes.setValue(puntajeList);
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

    public MutableLiveData<List<UsuarioData>> get_Colaboradores() {
        return _Colaboradores;
    }

    public MutableLiveData<List<Puntaje>> get_Puntajes() {
        return _Puntajes;
    }

    public MutableLiveData<List<Premio>> get_Premios() {
        return _Premios;
    }
}

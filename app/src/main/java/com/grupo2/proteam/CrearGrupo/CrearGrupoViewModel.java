package com.grupo2.proteam.CrearGrupo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.FStore.Equipo;
import com.grupo2.proteam.FStore.Compuestos.EquipoData;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.FStore.PrivadoUsuario;

import java.util.ArrayList;
import java.util.List;

public class CrearGrupoViewModel extends ViewModel {
    private final MutableLiveData<EquipoData> _EquipoData;
    private final MutableLiveData<List<UsuarioData>> _Colaboradores;
    private final MutableLiveData<List<UsuarioData>> _ResultadosBusqueda;
    private final MutableLiveData<List<UsuarioData>> _ColaboradoresMarcados;
    private final MutableLiveData<List<UsuarioData>> _ColaboradoresSelectos;
    private final MutableLiveData<String> _PatronBusqueda;



    private DocumentReference DREquipo;

    public CrearGrupoViewModel() {
        _EquipoData = new MutableLiveData<>();
        _Colaboradores = new MutableLiveData<>();
        _ResultadosBusqueda = new MutableLiveData<>();
        _PatronBusqueda = new MutableLiveData<>("");
        _ColaboradoresMarcados = new MutableLiveData<>(new ArrayList<>());
        _ColaboradoresSelectos = new MutableLiveData<>(new ArrayList<>());
    }
    public void SetEquipoID(String ID)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DREquipo =  db.collection("Equipos").document(ID);
        DREquipo.get().addOnSuccessListener(documentSnapshot -> {
            Equipo E = documentSnapshot.toObject(Equipo.class);
            _EquipoData.setValue(E != null? new EquipoData(E,ID) :  null);
            ActualizarListaColaboradores();

        });

    }
    public void ActualizarListaColaboradores()
    {
        assert  _EquipoData.getValue() != null;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<String> Str_colaboradores = _EquipoData.getValue().getColaboradores();
        List<UsuarioData> arr = new ArrayList<>();
        if (Str_colaboradores.size() > 0)
        {
            db.collection("Usuarios").whereIn(FieldPath.documentId(),Str_colaboradores).get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            PrivadoUsuario p = document.toObject(PrivadoUsuario.class);
                            assert p != null;
                            arr.add(new UsuarioData(p, document.getId(), false));
                        }
                        _Colaboradores.setValue(arr);
                        //_ResultadosBusqueda.setValue(arr);
                    });
        }
        else
        {
            _Colaboradores.setValue(arr);
        }


    }

    public void Buscar()
    {
        String patron = _PatronBusqueda.getValue();
        List<UsuarioData> colabs = _Colaboradores.getValue();
        List<UsuarioData> res = new ArrayList<>();
        assert colabs != null;
        assert patron != null;
        for (UsuarioData usuario : colabs) {
            if (usuario.getNyA().toLowerCase().contains(patron.toLowerCase()))
                if (!_ColaboradoresSelectos.getValue().contains(usuario))
                    res.add(usuario);
        }
        _ResultadosBusqueda.setValue(res);
    }

    public void GuardarColaboradoresMarcados()
    {
        List<UsuarioData> marcados = _ColaboradoresMarcados.getValue();
        List<UsuarioData> selectos = _ColaboradoresSelectos.getValue();
        for (UsuarioData marcado : marcados) {
            if(!selectos.contains(marcado))
                selectos.add(marcado);
        }

        _ColaboradoresSelectos.setValue(selectos);
        _ColaboradoresMarcados.setValue(new ArrayList<>());
    }

    public Grupo CrearGrupo(String Nombre, String Descripcion)
    {
        List<UsuarioData> selectos = _ColaboradoresSelectos.getValue();
        List<String> supervisores = new ArrayList<>(),
                trabajadores = new ArrayList<>();
        for (UsuarioData selecto : selectos) {
            if (selecto.isSupervisor())
                supervisores.add(selecto.getID());
            else
                trabajadores.add(selecto.getID());
        }
        Grupo G = new Grupo(Nombre, Descripcion, trabajadores, supervisores);
        return G;
    }



    public MutableLiveData<EquipoData> get_EquipoData() {
        return _EquipoData;
    }

    public MutableLiveData<List<UsuarioData>> get_Colaboradores() {
        return _Colaboradores;
    }

    public MutableLiveData<List<UsuarioData>> get_ResultadosBusqueda() {
        return _ResultadosBusqueda;
    }

    public MutableLiveData<String> get_PatronBusqueda() {
        return _PatronBusqueda;
    }

    public MutableLiveData<List<UsuarioData>> get_ColaboradoresSelectos() {
        return _ColaboradoresSelectos;
    }

    public MutableLiveData<List<UsuarioData>> get_ColaboradoresMarcados() {
        return _ColaboradoresMarcados;
    }
}

package com.grupo2.proteam.CrearGrupo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grupo2.proteam.CrearGrupo.Listas.ColaboradoresSelectos.ColaboradoresSelectosAdapter;
import com.grupo2.proteam.CrearGrupo.Listas.ColaboradoresSelectos.itemColaboradoresSelectosListener;
import com.grupo2.proteam.FStore.Compuestos.EquipoData;
import com.grupo2.proteam.FStore.Compuestos.UsuarioData;
import com.grupo2.proteam.FStore.Grupo;
import com.grupo2.proteam.R;

import java.util.List;

public class CrearGrupoActivity extends AppCompatActivity {

    private CrearGrupoViewModel DataVM;
    private RecyclerView ListaColaboradoresSelectos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        Button Agregar = findViewById(R.id.CrearGrupo_btnAgregar);
        Button Crear = findViewById(R.id.CrearGrupo_btnCrear);
        TextInputLayout Nombre = findViewById(R.id.CrearGrupo_tilNombre),
                Descripcion = findViewById(R.id.CrearGrupo_tilDescripcion);

        ListaColaboradoresSelectos = findViewById(R.id.CrearGrupo_rvColaboradores);


        String equipoID = getIntent().getStringExtra("IDEquipo");
        DataVM = new ViewModelProvider(this).get(CrearGrupoViewModel.class);
        DataVM.SetEquipoID(equipoID);



        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DFColaboradores().show(getSupportFragmentManager(),"");
                DataVM.get_PatronBusqueda().setValue("");
            }
        });

        Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Grupo G = DataVM.CrearGrupo(Nombre.getEditText().getText().toString(), Descripcion.getEditText().getText().toString());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                EquipoData E = DataVM.get_EquipoData().getValue();
                db.collection("Equipos").document(E.getID()).collection("Grupos").add(G)
                        .addOnSuccessListener(documentReference ->
                        {
                            Toast.makeText(CrearGrupoActivity.this, "Grupo creado", Toast.LENGTH_SHORT).show();

                            finish();
                        });

            }
        });

        DataVM.get_ColaboradoresSelectos().observe(this, usuarioData -> {
            ColaboradoresSelectosAdapter adapter = new ColaboradoresSelectosAdapter(usuarioData, new itemColaboradoresSelectosListener() {
                @Override
                public void Supervisor(UsuarioData colaborador, boolean isSupervisor) {
                    colaborador.setSupervisor(isSupervisor);
                }

                @Override
                public void Eliminar(UsuarioData colaborador) {
                    List<UsuarioData> lst = DataVM.get_ColaboradoresSelectos().getValue();
                    lst.remove(colaborador);
                    colaborador.setSupervisor(false);
                    DataVM.get_ColaboradoresSelectos().setValue(lst);
                }
            });
            ListaColaboradoresSelectos.setAdapter(adapter);
            ListaColaboradoresSelectos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        });

    }
}
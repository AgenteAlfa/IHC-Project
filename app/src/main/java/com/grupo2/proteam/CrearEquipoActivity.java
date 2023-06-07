package com.grupo2.proteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grupo2.proteam.FStore.Equipo;

import java.util.ArrayList;

public class CrearEquipoActivity extends AppCompatActivity {
    boolean valido = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);

        TextInputLayout Nombre = findViewById(R.id.CrearEquipo_tilNombre),
                Descripcion  = findViewById(R.id.CrearEquipo_tilDescripcion);
        EditText Normativa = findViewById(R.id.CrearEquipo_edtNormativa);
        Button Crear = findViewById(R.id.CrearEquipo_btnCrear);


        Crear.setOnClickListener(view -> {
            valido = ( !Nombre.getEditText().getText().toString().equals("") ) && ( !Descripcion.getEditText().getText().toString().equals("") );

            if (Nombre.getEditText().getText().toString().equals(""))
            {
                Nombre.setError("Campo obligatorio");
            }
            if (Descripcion.getEditText().getText().toString().equals(""))
            {
                Descripcion.setError("Campo obligatorio");
            }
            if (valido)
            {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                assert mAuth.getCurrentUser() != null;
                String uuid = mAuth.getCurrentUser().getUid();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Equipo E = new Equipo(uuid, Nombre.getEditText().getText().toString(),  Descripcion.getEditText().getText().toString(), Normativa.getText().toString(), new ArrayList<>());
                db.collection("Equipos").document().set(E).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CrearEquipoActivity.this, "Equipo " + Nombre.getEditText().getText() + " creado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


            }

        });
    }
}
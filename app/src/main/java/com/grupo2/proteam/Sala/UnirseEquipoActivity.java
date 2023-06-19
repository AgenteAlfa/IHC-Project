package com.grupo2.proteam.Sala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.grupo2.proteam.R;

public class UnirseEquipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_equipo);
        TextInputLayout tilCodigo = findViewById(R.id.UnirseEquipo_tilCodigo);
        Button Unirse = findViewById(R.id.UnirseEquipo_btnUnirse);
        Unirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                assert mAuth.getCurrentUser() != null;
                String uuid = mAuth.getCurrentUser().getUid();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("CodigosJoin").whereEqualTo("uuidEquipo", tilCodigo.getEditText().getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.getDocuments().size() == 0)
                        {
                            Toast.makeText(UnirseEquipoActivity.this, "Codigo invalido", Toast.LENGTH_SHORT).show();                            
                        }
                        else
                        {
                            String idEquipo = queryDocumentSnapshots.getDocuments().get(0).getId();
                            db.collection("Equipos").document(idEquipo).update("colaboradores", FieldValue.arrayUnion(uuid)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    finish();
                                }
                            });
                        }
                        
                    }
                });
            }
        });
    }
}
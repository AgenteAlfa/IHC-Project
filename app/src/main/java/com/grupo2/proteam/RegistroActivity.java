package com.grupo2.proteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.grupo2.proteam.FStore.PrivadoUsuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private RegistroViewModel DataVM;
    private TextInputLayout Fecha;
    public static final String TAG = "RegistroActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        DataVM = new ViewModelProvider(this).get(RegistroViewModel.class);
        TextInputLayout NyA = findViewById(R.id.Registro_tilNyA);
        Fecha = findViewById(R.id.Registro_tilFN);
        Button Guardar = findViewById(R.id.Registro_btnGuardar);
        TextView txtIsAdmin = findViewById(R.id.Registro_txtModo);

        NyA.getEditText().setText((CharSequence) DataVM.getNombre().getValue());

        SimpleDateFormat spf= new SimpleDateFormat("dd/MM/yyyy");

        Date date = DataVM.getFecha().getValue();
        assert date != null;
        //Fecha.getEditText().setText((CharSequence) spf.format(date));
        Fecha.getEditText().setText("DD/MM/YYYY");
        SwitchMaterial isAdmin = findViewById(R.id.Registro_swcActivarAdmin);
        isAdmin.setActivated(DataVM.getIsAdmin().getValue());
        isAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DataVM.getIsAdmin().setValue(b);
                txtIsAdmin.setText(b? "Modo administrador ACTIVADO" : "Modo administrador DESACTIVADO");
            }
        });

        NyA.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DataVM.getNombre().setValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Fecha.getEditText().addTextChangedListener(twDate);

        DataVM.getNombre().observe(this, s -> Log.d(TAG, "onChanged: " + s));

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Colocar un mensaje de error
                Pattern pattern = Pattern.compile("[0-9]+/[0-9]+/[0-9]+");
                Log.d(TAG, "afterTextChanged: " + Fecha.getEditText().getText());
                if (!pattern.matcher(Fecha.getEditText().getText()).matches())
                {
                    Fecha.setError("Fecha no valida");
                }
                else
                {
                    Fecha.setError(null);
                    GuardarFecha(Fecha.getEditText().getText().toString()); // Por si acaso

                    //Empaquetar los datos y guardar en la db
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    assert mAuth.getCurrentUser() != null;
                    String uuid = mAuth.getCurrentUser().getUid();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    PrivadoUsuario user = new PrivadoUsuario(DataVM.getIsAdmin().getValue(), DataVM.getFecha().getValue(), DataVM.getNombre().getValue());
                    db.collection("Usuarios").document(uuid).set(user).addOnSuccessListener(unused -> {
                        Toast.makeText(RegistroActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(RegistroActivity.this, "A ocurrido un error, reintente de un momento", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                }

            }
        });

    }

    private void GuardarFecha(String str)
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(str);
            DataVM.getFecha().setValue(date);
            Log.d(TAG, "onClick: Fecha guardada");
        } catch (ParseException e) {
            Log.d(TAG, "onClick: Problema al guardar la fecha");
            e.printStackTrace();
        }
    }

    TextWatcher twDate = new TextWatcher() {
        private String current = "";
        private String ddmmyyyy = "DDMMYYYY";
        private Calendar cal = Calendar.getInstance();
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().equals(current)) {
                return;
            }
            Fecha.setError(null);
            String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
            String cleanC = current.replaceAll("[^\\d.]|\\.", "");

            int cl = clean.length();
            int sel = cl;
            for (int i = 2; i <= cl && i < 6; i += 2) {
                sel++;
            }
            //Fix for pressing delete next to a forward slash
            if (clean.equals(cleanC)) sel--;

            if (clean.length() < 8){
                clean = clean + ddmmyyyy.substring(clean.length());
            }else{
                //This part makes sure that when we finish entering numbers
                //the date is correct, fixing it otherwise
                int day  = Integer.parseInt(clean.substring(0,2));
                int mon  = Integer.parseInt(clean.substring(2,4));
                int year = Integer.parseInt(clean.substring(4,8));

                mon = mon < 1 ? 1 : Math.min(mon, 12);
                cal.set(Calendar.MONTH, mon-1);
                year = (year<1900)?1900:(year>2100)?2100:year;
                cal.set(Calendar.YEAR, year);
                // ^ first set year for the line below to work correctly
                //with leap years - otherwise, date e.g. 29/02/2012
                //would be automatically corrected to 28/02/2012

                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                clean = String.format("%02d%02d%02d",day, mon, year);
            }

            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                    clean.substring(2, 4),
                    clean.substring(4, 8));

            sel = sel < 0 ? 0 : sel;
            current = clean;
            Fecha.getEditText().setText(current);
            GuardarFecha(current);
            Fecha.getEditText().setSelection(sel < current.length() ? sel : current.length());


        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };

}
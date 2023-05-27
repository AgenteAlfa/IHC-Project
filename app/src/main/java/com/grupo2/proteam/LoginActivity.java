package com.grupo2.proteam;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> {
                onSignInResult(result);
                RevisarCuenta();
            }
    );
    private String TAG = "LoginActivity";

    TextView txtCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Iniciar = findViewById(R.id.Login_btnIniciar),
                Cerrar = findViewById(R.id.Login_btnCerrarSesion);
        Iniciar.setOnClickListener(view -> createSignInIntent());
        txtCuenta = findViewById(R.id.Login_txtCuenta);
        Cerrar.setOnClickListener(view -> AuthUI.getInstance()
                .signOut(LoginActivity.this)
                .addOnCompleteListener(task -> RevisarCuenta()));


    }
    private void RevisarCuenta()
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        txtCuenta.setText(mAuth.getCurrentUser() == null ? "Sin usuario registrado" : "Email : " + mAuth.getCurrentUser().getEmail());
    }


    @Override
    protected void onStart() {
        super.onStart();
        RevisarCuenta();
    }

    public void createSignInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.icono)
                .setTheme(R.style.FirebaseUIProTeam)
                .build();

        signInLauncher.launch(signInIntent);
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // ...
            Log.d(TAG, "onSignInResult: inicio de sesion desde : " + user.getEmail());
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
            Log.d(TAG, "onSignInResult: Inicio fallido");
        }
    }

}
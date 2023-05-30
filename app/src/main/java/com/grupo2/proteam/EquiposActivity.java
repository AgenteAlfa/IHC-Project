package com.grupo2.proteam;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class EquiposActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);
        View AppBar = findViewById(R.id.Equipos_AppBar);
        Toolbar tbar = AppBar.findViewById(R.id.abEquipos_toolbar);
        setSupportActionBar(tbar);
        DrawerLayout drawer = findViewById(R.id.Equipos_DrawerLayout);
        NavigationView navigationView = findViewById(R.id.Equipos_nvLateral);
        navigationView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                //R.id.navEquipos_itmEquipos, R.id.navEquipos_itmNotificaciones, R.id.navEquipos_itmConfiguracion)
                R.id.navEquipos_itmEquipos, R.id.navEquipos_itmDatos, R.id.navEquipos_itmConfiguracion)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.contenedorEquipos_frgContenedor);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        FloatingActionButton FabAyuda = AppBar.findViewById(R.id.abEquipos_fabAyuda);
        FabAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        //Revisar si se tiene la informacion personal en firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.contenedorEquipos_frgContenedor);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
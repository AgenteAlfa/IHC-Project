package com.grupo2.proteam.Equipo;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.grupo2.proteam.R;

public class EquipoActivity extends AppCompatActivity {

    EquipoViewModel DataVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);

        String equipoID = getIntent().getStringExtra("IDEquipo");
        boolean isAdmin = getIntent().getBooleanExtra("isAdmin",false);
        DataVM = new ViewModelProvider(this).get(EquipoViewModel.class);
        DataVM.get_isAdmin().setValue(isAdmin);
        //Agregar observador de esquipo

        DataVM.SetEquipoID(equipoID);



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_grupos);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
package com.grupo2.proteam.GrupoTrabajador;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;

import com.google.android.material.tabs.TabLayoutMediator;
import com.grupo2.proteam.Equipo.EquipoViewModel;
import com.grupo2.proteam.R;
import com.grupo2.proteam.databinding.ActivityGrupoTrabajadorBinding;

public class GrupoTrabajadorActivity extends AppCompatActivity {


    GrupoTrabajadorViewModel DataVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_trabajador);

        DataVM = new ViewModelProvider(this).get(GrupoTrabajadorViewModel.class);
        DataVM.Inicializar(getIntent().getStringExtra("IDEquipo"),
                getIntent().getStringExtra("IDGrupo"));

        AdaptadorTabs adapter = new AdaptadorTabs(getSupportFragmentManager(),getLifecycle());
        ViewPager2 viewPager = findViewById(R.id.GrupoTrabajador_ViewPager);
        viewPager.setAdapter(adapter);
        TabLayout tabs = findViewById(R.id.GrupoTrabajador_TabLayout);
        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            switch (position)
            {
                case 0:
                    tab.setText("Misiones");
                    break;

                case 1:
                    tab.setText("Historial");
                    break;

                case 2:
                    tab.setText("Info");
                    break;

                default:
                    break;
            }
        }).attach();
        FloatingActionButton fab = findViewById(R.id.GrupoTrabajador_fabAyuda);



    }
}
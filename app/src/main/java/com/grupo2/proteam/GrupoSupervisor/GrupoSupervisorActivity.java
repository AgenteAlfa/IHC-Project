package com.grupo2.proteam.GrupoSupervisor;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.tabs.TabLayoutMediator;
import com.grupo2.proteam.R;

public class GrupoSupervisorActivity extends AppCompatActivity {

    private GrupoSupervisorViewModel DataVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_supervisor);
        DataVM = new ViewModelProvider(this).get(GrupoSupervisorViewModel.class);
        DataVM.Inicializar(getIntent().getStringExtra("IDEquipo"),
                getIntent().getStringExtra("IDGrupo"));

        SAdaptadorTabs adapter = new SAdaptadorTabs(getSupportFragmentManager(),getLifecycle());
        ViewPager2 viewPager = findViewById(R.id.GrupoSupervisor_ViewPager);
        viewPager.setAdapter(adapter);
        TabLayout tabs = findViewById(R.id.GrupoSupervisor_TabLayout);
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
                    tab.setText("Solicitudes");
                    break;

                case 3:
                    tab.setText("Colaboradores");
                    break;

                case 4:
                    tab.setText("Configuracion");
                    break;

                default:
                    break;
            }
        }).attach();
        FloatingActionButton fab = findViewById(R.id.GrupoSupervisor_fabAyuda);
    }
}
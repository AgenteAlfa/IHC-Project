package com.grupo2.proteam.GrupoSupervisor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.grupo2.proteam.GrupoSupervisor.Colaboradores.SColaboradoresFragment;
import com.grupo2.proteam.GrupoSupervisor.Configuracion.SConfiguracionFragment;
import com.grupo2.proteam.GrupoSupervisor.Historial.SHistorialFragment;
import com.grupo2.proteam.GrupoSupervisor.Misiones.SMisionesFragment;
import com.grupo2.proteam.GrupoSupervisor.Solicitudes.SSolicitudesFragment;


public class SAdaptadorTabs extends FragmentStateAdapter {

    public SAdaptadorTabs(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment res;
        switch (position)
        {
            case 0:
                res = new SMisionesFragment();
                break;

            case 1:
                res = new SHistorialFragment();
                break;
            case 2:
                res = new SSolicitudesFragment();
                break;

            case 3:
                res = new SColaboradoresFragment();
                break;

            default:
                res = new SConfiguracionFragment();
                break;
        }
        return res;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

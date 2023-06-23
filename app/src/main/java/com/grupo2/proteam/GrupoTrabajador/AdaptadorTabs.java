package com.grupo2.proteam.GrupoTrabajador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.grupo2.proteam.GrupoTrabajador.Historial.HistorialFragment;
import com.grupo2.proteam.GrupoTrabajador.Info.InfoFragment;
import com.grupo2.proteam.GrupoTrabajador.Misiones.MisionesFragment;

public class AdaptadorTabs extends FragmentStateAdapter {

    public AdaptadorTabs(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment res;
        switch (position)
        {
            case 0:
                res = new MisionesFragment();
                break;

            case 1:
                res = new HistorialFragment();
                break;

            default:
                res = new InfoFragment();
                break;
        }
        return res;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

package com.grupo2.proteam.ui.Equipos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.grupo2.proteam.databinding.FragmentEquiposBinding;

public class EquiposFragment extends Fragment {

    private FragmentEquiposBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EquiposFrgViewModel homeViewModel =
                new ViewModelProvider(this).get(EquiposFrgViewModel.class);

        binding = FragmentEquiposBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.GadsMobileEdu22.School365.admin.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.GadsMobileEdu22.School365.admin.R;
import com.GadsMobileEdu22.School365.admin.databinding.FragmentRegisterUserBinding;

import org.jetbrains.annotations.NotNull;

public class RegisterUserFragment extends Fragment {

    private FragmentRegisterUserBinding binding;
    private RegisterViewModel viewModel;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRegisterUserBinding.inflate(inflater);
        viewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);

        return binding.getRoot();

    }
}
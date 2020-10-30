package com.GadsMobileEdu22.Schoolbox365.students.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.GadsMobileEdu22.Schoolbox365.students.R;
import com.GadsMobileEdu22.Schoolbox365.students.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSearchBinding binding = FragmentSearchBinding.inflate(inflater);
        return binding.getRoot();
    }
}
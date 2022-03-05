package com.llopez.pruebapractica1.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.llopez.pruebapractica1.R;

public class MenuFragment extends Fragment {
    private Button btnExcerciseOne;
    private Button btnExcerciseTwo;
    private Button btnLogout;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_menu, container, false);

        btnExcerciseOne = layout.findViewById(R.id.btnToExcerciseOne);
        btnExcerciseTwo = layout.findViewById(R.id.btnToExcerciseTwo);
        btnLogout = layout.findViewById(R.id.btnLogout);



        return layout;
    }
}
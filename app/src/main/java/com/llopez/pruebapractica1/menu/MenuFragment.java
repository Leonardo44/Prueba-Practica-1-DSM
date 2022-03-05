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
import android.widget.TextView;

import com.llopez.pruebapractica1.R;

public class MenuFragment extends Fragment {
    private Button btnExcerciseOne;
    private Button btnExcerciseTwo;
    private Button btnLogout;
    private TextView tvUser;

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
        tvUser = layout.findViewById(R.id.tvUser);
        
        btnExcerciseOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toExcerciseOne();
            }
        });

        btnExcerciseTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toExcerciseTwo();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("credential", Context.MODE_PRIVATE);
        String email = preferences.getString("userEmail", "usuarioprueba2022@gmail.com");
        
        return layout;
    }
    
    private void toExcerciseOne() {
        NavHostFragment.findNavController(this).navigate(R.id.action_menuFragment_to_excerciseOneFragment);
    }

    private void toExcerciseTwo() {
        NavHostFragment.findNavController(this).navigate(R.id.action_menuFragment_to_excerciseTwoFragment);
    }

    private void toLogin() {
        SharedPreferences preferences = getActivity().getSharedPreferences("credential", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLogged", false);
        editor.commit();

        NavHostFragment.findNavController(this).navigate(R.id.action_menuFragment_to_loginFragment);
    }
}

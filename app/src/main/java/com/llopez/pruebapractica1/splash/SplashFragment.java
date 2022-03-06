package com.llopez.pruebapractica1.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llopez.pruebapractica1.R;

public class SplashFragment extends Fragment {
    public SplashFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        verifyLogin();
    }

    private void verifyLogin() {
        SharedPreferences preferences = getActivity().getSharedPreferences("credential", Context.MODE_PRIVATE);
        Boolean isLogged = preferences.getBoolean("isLogged", false);

        if (isLogged) {
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_menuFragment);
        } else {
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_loginFragment);
        }
    }
}
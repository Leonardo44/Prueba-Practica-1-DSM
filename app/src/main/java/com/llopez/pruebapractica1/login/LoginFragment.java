package com.llopez.pruebapractica1.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.llopez.pruebapractica1.R;

public class LoginFragment extends Fragment {
    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;
    private Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login, container, false);

        editTextTextEmailAddress = layout.findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = layout.findViewById(R.id.editTextTextPassword);
        btnLogin = layout.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCrendentials();
            }
        });
        return layout;
    }
    
    private void verifyCrendentials() {
        SharedPreferences preferences = getActivity().getSharedPreferences("credential", Context.MODE_PRIVATE);

        String email = preferences.getString("userEmail", "usuarioprueba2022@gmail.com");
        String password = preferences.getString("userPassword", "pa$$w0rd");

        if (password.equals(editTextTextPassword.getText().toString()) && !editTextTextEmailAddress.getText().toString().trim().isEmpty()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLogged", true);
            editor.putString("userEmail", editTextTextEmailAddress.getText().toString().trim());
            editor.apply();

            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_menuFragment);
        } else {
            Toast toast= Toast.makeText(getContext(), R.string.login_error, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

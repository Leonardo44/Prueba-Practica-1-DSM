package com.llopez.pruebapractica1.excercise_1;

import android.Manifest;
import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.llopez.pruebapractica1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ExcerciseOneFragment extends Fragment {
    private EditText txtName;
    private EditText txtCode;
    private EditText txtSales;
    private Spinner spinnerMonth;
    private Button btnUploadImage;
    private Button btnCalculate;
    private ImageView userImageView;

    private Uri absolutePhotoUri;

    private ActivityResultLauncher<String[]> selectPhotoFromGalleryContract;
    private ActivityResultLauncher<String> requestPermissionContract;

    //mime types of files.
    private final String[] filesMimeTypes =  {"image/*"};
    private final String readExternalStoragePermission = Manifest.permission.READ_EXTERNAL_STORAGE;

    public ExcerciseOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectPhotoFromGalleryContract = registerForActivityResult(new ActivityResultContracts.OpenDocument(),  photoUri -> {
            if (photoUri != null) {
                absolutePhotoUri = photoUri;
                userImageView.setImageURI(photoUri);
            }
        });

        requestPermissionContract = registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
            if (result) {
                selectPhotoFromGalleryContract.launch(filesMimeTypes);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_excercise_one, container, false);

        txtName = layout.findViewById(R.id.editTextUserName);
        txtCode = layout.findViewById(R.id.editTextCode);
        txtSales = layout.findViewById(R.id.editTextSales);
        spinnerMonth = layout.findViewById(R.id.spinnerMonth);
        btnUploadImage = layout.findViewById(R.id.ivUploadPhoto);
        btnCalculate = layout.findViewById(R.id.btnCalculate);
        userImageView = layout.findViewById(R.id.ivUser);

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImage();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Enero");
        spinnerArray.add("Febrero");
        spinnerArray.add("Marzo");
        spinnerArray.add("Abril");
        spinnerArray.add("Mayo");
        spinnerArray.add("Junio");
        spinnerArray.add("Julio");
        spinnerArray.add("Agosto");
        spinnerArray.add("Septiembre");
        spinnerArray.add("Octubre");
        spinnerArray.add("Noviembre");
        spinnerArray.add("Diciembre");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);

        return layout;
    }

    private void requestImage() {
        requestPermissionContract.launch(readExternalStoragePermission);
    }

    private void calculate() {
        String name = txtName.getText().toString().trim();
        String code = txtCode.getText().toString().trim();
        String salesString = txtSales.getText().toString().trim();
        String month = spinnerMonth.getSelectedItem().toString();

        if (!name.isEmpty() && !code.isEmpty() && !salesString.isEmpty() && !month.isEmpty() && absolutePhotoUri != null) {
            try {
                Double sales = Double.parseDouble(salesString);

                if (sales > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", name);
                    bundle.putString("userImage", absolutePhotoUri.toString());
                    bundle.putString("userMonth", month);
                    bundle.putString("userSales", salesString);

                    NavHostFragment.findNavController(this).navigate(R.id.action_excerciseOneFragment_to_detailExcerciseOneFragment, bundle);
                } else {
                    Toast toast= Toast.makeText(getContext(), R.string.incorrect_data, Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (Exception err) {
                Toast toast= Toast.makeText(getContext(), R.string.incorrect_data, Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast= Toast.makeText(getContext(), R.string.incorrect_data, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onDestroy() {
        selectPhotoFromGalleryContract = null;
        requestPermissionContract = null;
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        absolutePhotoUri = null;
        super.onDestroyView();
    }
}
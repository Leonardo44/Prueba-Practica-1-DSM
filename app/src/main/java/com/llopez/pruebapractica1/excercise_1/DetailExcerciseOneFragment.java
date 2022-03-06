package com.llopez.pruebapractica1.excercise_1;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.llopez.pruebapractica1.R;

public class DetailExcerciseOneFragment extends Fragment {
    private ImageView ivUserDetail;
    private TextView tvUserDetailName;
    private TextView tvUserMonth;
    private TextView tvSalesTotal;
    private TextView tvUserPercentage;
    private TextView tvUserTotal;

    public DetailExcerciseOneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detail_excercise_one, container, false);
        ivUserDetail = layout.findViewById(R.id.ivUserDetail);
        tvUserDetailName = layout.findViewById(R.id.tvUserDetailName);
        tvUserMonth = layout.findViewById(R.id.tvUserMonth);
        tvSalesTotal = layout.findViewById(R.id.tvSalesTotal);
        tvUserPercentage = layout.findViewById(R.id.tvUserPercentage);
        tvUserTotal = layout.findViewById(R.id.tvUserTotal);

        assert getArguments() != null;
        String userName = getArguments().getString("userName");
        String userImage = getArguments().getString("userImage");
        String userMonth = getArguments().getString("userMonth");
        String userSales = getArguments().getString("userSales");

        try {
            Uri photoUri = Uri.parse(userImage);
            Double sales = Double.parseDouble(userSales);

            tvUserDetailName.setText(userName);
            tvUserMonth.setText("Mes: " + userMonth);
            tvUserMonth.setText("Mes: " + userMonth);
            tvSalesTotal.setText("Ventas totales: " + userSales);
            ivUserDetail.setImageURI(photoUri);

            if (sales < 500) { // 0%
                tvUserPercentage.setText("Porcentaje de comisiones: 0%");
                tvUserTotal.setText("Comisiones a recibir: $0");
            } else if (sales >= 500 && sales < 1000) { // 5%
                tvUserPercentage.setText("Porcentaje de comisiones: 5%");
                tvUserTotal.setText("Comisiones a recibir: $" + String.format("%.2f", (sales * 0.05)));
            } else if (sales >= 1000 && sales < 2000) { // 10%
                tvUserPercentage.setText("Porcentaje de comisiones: 10%");
                tvUserTotal.setText("Comisiones a recibir: $" + String.format("%.2f", (sales * 0.1)));
            } else if (sales >= 2000 && sales < 3000) { // 15%
                tvUserPercentage.setText("Porcentaje de comisiones: 15%");
                tvUserTotal.setText("Comisiones a recibir: $" + String.format("%.2f", (sales * 0.15)));
            } else if (sales >= 3000 && sales < 4000) { // 20%
                tvUserPercentage.setText("Porcentaje de comisiones: 20%");
                tvUserTotal.setText("Comisiones a recibir: $" + String.format("%.2f", (sales * 0.2)));
            } else { // 30%
                tvUserPercentage.setText("Porcentaje de comisiones: 30%");
                tvUserTotal.setText("Comisiones a recibir: $" + String.format("%.2f", (sales * 0.3)));
            }
        } catch (Exception err) {
        }

        return layout;
    }
}
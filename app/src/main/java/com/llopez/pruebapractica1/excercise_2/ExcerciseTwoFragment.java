import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.llopez.pruebapractica1.R;

public class ExcerciseTwoFragment extends Fragment {
    private EditText txtA;
    private EditText txtB;
    private EditText txtC;
    private Button btnCalculate;
    private TextView tvResult;

    public ExcerciseTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_excercise_two, container, false);

        txtA = layout.findViewById(R.id.editTextA);
        txtB = layout.findViewById(R.id.editTextB);
        txtC = layout.findViewById(R.id.editTextC);
        btnCalculate = layout.findViewById(R.id.btnCalculateTwo);
        tvResult = layout.findViewById(R.id.tvResultTwo);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        return layout;
    }

    private void calculate() {
        try {
            Double a = Double.parseDouble(txtA.getText().toString());
            Double b = Double.parseDouble(txtB.getText().toString());
            Double c = Double.parseDouble(txtC.getText().toString());

            Double discriminating = Math.pow(b, 2) - ( 4 * a * c);

            if (discriminating > 0) { // 2 soluciones
                Double resultOne = (((-1) * (b)) + Math.sqrt(discriminating)) / ((2) * (a));
                Double resultTwo = (((-1) * (b)) - Math.sqrt(discriminating)) / ((2) * (a));

                tvResult.setText("X1 = " + resultOne + "; X2 = " + resultTwo);
            } else if (discriminating < 0) { // 0 soluciones
                tvResult.setText("La ecuación no posee soluciones reales.");
            } else { // 1 solucion
                Double result = (((-1) * (b)) + Math.sqrt(discriminating)) / ((2) * (a));
                tvResult.setText("X = " + result);
            }
        } catch (Exception err) {
            Toast toast= Toast.makeText(getContext(), R.string.incorrect_data, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

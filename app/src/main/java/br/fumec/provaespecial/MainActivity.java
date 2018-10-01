package br.fumec.provaespecial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnLimpar;
    private Spinner spinnerCores;
    private CustomCanvas customCanvas;
    private final String[] cores = {"Vermelho", "Verde", "Azul"};
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instanciarElemTela();

        spinnerCores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customCanvas.setPosCorEscolhida(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                customCanvas.setPosCorEscolhida(0);
            }
        });
        
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_pequeno:
                        customCanvas.setPequeno(true);
                        break;
                    case R.id.radio_grande:
                        customCanvas.setPequeno(false);
                        break;
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCanvas.setLimpar(true);
                customCanvas.getCoords().removeAll(customCanvas.getCoords());
                customCanvas.invalidate();
            }
        });
    }

    private void instanciarElemTela() {
        radioGroup = findViewById(R.id.radio_group);
        btnLimpar = findViewById(R.id.btn_limpar);
        spinnerCores = findViewById(R.id.spinner_cores);
        customCanvas = findViewById(R.id.custom_canvas_main);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cores);
        spinnerCores.setAdapter(adapter);
    }
}

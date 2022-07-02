package br.pro.adalto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Tela01Activity extends AppCompatActivity {

    private TextView lblResultado;
    private EditText etValor;
    private Button btnCalcular;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela01);

        lblResultado = (TextView) findViewById(R.id.tvResultado);
        etValor = findViewById(R.id.etValor);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });

    }

    private void calcular(){
        String valor = etValor.getText().toString();

        if( !valor.isEmpty() ){
            double numero = Double.valueOf( valor );
            double result = numero * 2;
            lblResultado.setText( String.valueOf(result) );
        }
    }


}

package br.pro.applayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AgendaActivity extends AppCompatActivity {

    private Spinner spCidade, spEstado;
    private ArrayAdapter adapter;

    private String[] rs = {"Selecione a cidade...", "Alvorada", "Canoas",
            "Porto Alegre"};
    private String[] sc = {"Selecione a cidade...", "Florianópolis",
            "Passo de Torres", "Praia Grande"};
    private String[] pr = {"Selecione a cidade...", "Cascavél",
            "Curitiba", "Maringá"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        spCidade = findViewById(R.id.spCidade);
        spEstado = findViewById(R.id.spEstado);

        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                carregarCidades();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void carregarCidades(){
        int posicao = spEstado.getSelectedItemPosition();
        //       String estadoSelecionado = spEstado.getSelectedItem().toString();
        String[] cidades = {};
        spCidade.setEnabled(true);
        switch (posicao){
            case 1:
                cidades = pr;
                break;
            case 2:
                cidades = rs;
                break;
            case 3:
                cidades = sc;
                break;
            default:
                cidades = new String[] { getString(R.string.estadoZero)  };
                spCidade.setEnabled(false);
                Toast.makeText(this,"O estado deve ser selecionado",
                        Toast.LENGTH_LONG ).show();
        }

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, cidades );
        spCidade.setAdapter( adapter );

    }
}
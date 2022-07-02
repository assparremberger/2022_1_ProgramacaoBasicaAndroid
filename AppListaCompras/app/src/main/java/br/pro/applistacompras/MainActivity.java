package br.pro.applistacompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Produto> lista;
    private EditText etNome, etQTD;
    private ListView lvProdutos;
//    private ArrayAdapter adapter;
    private AdapterProduto adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = new ArrayList<>();
        etNome = findViewById(R.id.etNome);
        etQTD = findViewById(R.id.etQuantidade);
        lvProdutos = findViewById(R.id.lvProdutos);
        adapter = new AdapterProduto(this, lista);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lvProdutos.setAdapter(adapter);

        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( i );
                Log.i("erro", "posicao: "+ i);
                return true;
            }
        });

    }

    public void adicionar(View view){
        // Toast.makeText(this, "Oi", Toast.LENGTH_LONG).show();
        String nome = etNome.getText().toString();
        if( nome.isEmpty() ){
            Toast.makeText(this, "O nome é obrigatório!", Toast.LENGTH_LONG).show();
        }else{
            String quantidade = etQTD.getText().toString();
            double qtd = 0.0;
            if( !quantidade.isEmpty() ){
                qtd = Double.valueOf( quantidade );
            }
            Produto prod =new Produto(nome, qtd);
            lista.add( prod );
            adapter.notifyDataSetChanged();
        }
    }

    private void excluir( int posicao ){
        Produto prod = lista.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon( android.R.drawable.ic_delete );
        alerta.setTitle( "Atençao!" );
        alerta.setMessage( "Confirma excluir o produto " +
                prod.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                lista.remove(posicao);
                adapter.notifyDataSetChanged();
            }
        });
        alerta.setNegativeButton("Nao" , (dialog, i)->{
            Toast.makeText(this, "Nao foi excluído", Toast.LENGTH_LONG).show();
        });
        alerta.show();
    }

}
package br.pro.applivraria.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.pro.applivraria.R
import br.pro.applivraria.model.Livro
import br.pro.applivraria.repository.DatabaseHandler

class FormularioActivity : AppCompatActivity() {

    lateinit var etTitulo: EditText
    lateinit var etPaginas: EditText
    lateinit var btnSalvar: Button

    var acao: String = ""
    var livro: Livro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        etTitulo = findViewById(R.id.etTitulo)
        etPaginas = findViewById(R.id.etPaginas)
        btnSalvar = findViewById(R.id.btnSalvar)
        acao = intent.getStringExtra("acao")!!
        if( acao.equals( "inserir")){
            livro = Livro()
        }else{

            val idLivro = intent.getIntExtra("idLivro", 0)
            // buscar o livro no banco
            livro = DatabaseHandler(this).getlivroById(idLivro)
            if( livro == null )
                finish()
            else
                carregarFormulario()
        }
    }

    fun carregarFormulario(){
        etTitulo.setText( livro?.titulo )
        etPaginas.setText( livro?.paginas.toString())
    }

    fun salvar(view: View){
        livro?.titulo = etTitulo.text.toString()
        if( etPaginas.text.toString().isEmpty() ){
            livro?.paginas = 0
        }else{
            livro?.paginas = etPaginas.text.toString().toInt()
        }
        val databaseHandler = DatabaseHandler(this)
        if (acao.equals("inserir")){
            databaseHandler.addLivro( livro!! )
            etTitulo.setText("")
            etPaginas.setText("")
        }else{
            databaseHandler.updateLivro( livro!! )
            finish()
        }
    }

}
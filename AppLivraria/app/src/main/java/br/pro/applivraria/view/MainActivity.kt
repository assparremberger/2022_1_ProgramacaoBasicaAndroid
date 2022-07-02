package br.pro.applivraria.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.pro.applivraria.R
import br.pro.applivraria.adapter.ListaAdapter
import br.pro.applivraria.databinding.ActivityMainBinding
import br.pro.applivraria.model.Livro
import br.pro.applivraria.repository.DatabaseHandler

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var listaAdapter: ListaAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    var livros = ArrayList<Livro>()
//    var databaseHandler = DatabaseHandler(this)

    lateinit var rvLivros : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        rvLivros = findViewById(R.id.rvLivros)




//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            val intent = Intent(this, FormularioActivity::class.java)
            intent.putExtra("acao", "inserir")
            startActivity( intent )

        }


    }

    override fun onResume() {
        super.onResume()

        val databaseHandler = DatabaseHandler(this)
        livros = databaseHandler.getlivros()

        listaAdapter = ListaAdapter(livros, this) { position ->
            var intent = Intent(this, FormularioActivity::class.java)
            intent.putExtra("acao", "editar")
            intent.putExtra("idLivro", livros[position].id )
            startActivity(intent)
        }
        linearLayoutManager = LinearLayoutManager(this)
        rvLivros.layoutManager = linearLayoutManager
        rvLivros.adapter = listaAdapter

 //       Toast.makeText(this, "Total de livros: $cont",
 //           Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
        return true
    }
}
package br.pro.applivraria.adapter

import android.content.ComponentCallbacks
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.pro.applivraria.R
import br.pro.applivraria.model.Livro

class ListaAdapter( private val livros: List<Livro> , internal val context: Context,
                    private val callbacks: (Int) -> Unit) :
    RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

//class ListaAdapter( private val livros: List<Livro> , internal val context: Context) :
//    RecyclerView.Adapter<ListaAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.content_lista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val livro = livros[ position ]
        holder.tvTitulo.text = livro.titulo
        holder.tvId.text = livro.id.toString()
        holder.tvPaginas.text = livro.paginas.toString()
        if( position % 2 == 0)
            holder.layout.setBackgroundColor(Color.rgb(240, 240, 240))

        holder.layout.setOnClickListener {
            this.callbacks( position )
        }


    }

    override fun getItemCount(): Int {
        return livros.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvTitulo: TextView = view.findViewById(R.id.tvTitulo)
        var tvPaginas: TextView = view.findViewById(R.id.tvPaginas)
        var tvId: TextView = view.findViewById(R.id.tvId)
        var layout: LinearLayout = view.findViewById(R.id.linearLayout)
    }
}
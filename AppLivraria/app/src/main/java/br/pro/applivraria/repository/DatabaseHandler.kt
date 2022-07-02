package br.pro.applivraria.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.pro.applivraria.model.Livro

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,NAME, null, VERSION) {

        companion object{
            const val NAME = "livraria"
            const val VERSION = 1
            const val TABLE = "livros"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        val SQL = "CREATE TABLE IF NOT EXISTS $TABLE ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " titulo TEXT , " +
                " paginas INTEGER ) "
        db?.execSQL( SQL )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addLivro( livro: Livro): Boolean{
        var db = this.writableDatabase
        // Manual
//        var paginas = livro.paginas
//        var sql = "INSERT INTO livros (titulo, paginas) VALUES ( '${livro.titulo}' , $paginas ) "
//        db.execSQL( sql )

        val values = ContentValues()
        values.put("titulo", livro.titulo)
        values.put("paginas", livro.paginas)
        val retorno = db.insert(TABLE, null, values)
        db.close()
        return retorno >= 0
    }

    fun updateLivro( livro: Livro): Boolean{
        var db = this.writableDatabase

        val values = ContentValues()
        values.put("titulo", livro.titulo)
        values.put("paginas", livro.paginas)
        val retorno = db.update(
            TABLE, values,
            " id = " + livro.id, null)
        db.close()
        return retorno >= 0
    }


    fun deleteLivro( idLivro: Int): Boolean{
        var db = this.writableDatabase
        val retorno = db.delete(TABLE," id = " + idLivro, null)
        db.close()
        return retorno >= 0
    }


    fun getlivros(): ArrayList<Livro>{
        var lista = ArrayList<Livro>()
        var db = this.readableDatabase
        val query = "SELECT * FROM $TABLE ORDER BY titulo"
        val cursor = db.rawQuery( query , null)
        if( cursor != null){
            if( cursor.count > 0){
                cursor.moveToFirst()
                do {
                    val id = cursor.getInt( 0 )
                    val titulo = cursor.getString( 1 )
                    val paginas = cursor.getInt( 2 )
                    val livro = Livro(id, titulo, paginas)
                    lista.add( livro )
                }while ( cursor.moveToNext() )
            }
        }
        return lista
    }

    fun getlivroById(idLivro: Int): Livro?{
        var db = this.readableDatabase
        val query = "SELECT * FROM $TABLE WHERE id = $idLivro ORDER BY titulo"
        val cursor = db.rawQuery( query , null)
        if( cursor != null){
            if( cursor.count > 0){
                cursor.moveToFirst()
                val id = cursor.getInt( 0 )
                val titulo = cursor.getString( 1 )
                val paginas = cursor.getInt( 2 )
                val livro = Livro(id, titulo, paginas)
                return livro
            }
        }
        return null
    }



}
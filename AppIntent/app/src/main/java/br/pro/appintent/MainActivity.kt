package br.pro.appintent

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var etValor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etValor = findViewById(R.id.etValor)
    }


    fun ligar(view : View){
        if( etValor.text.toString().isEmpty() ){
//            var alerta = Toast(this)
//            alerta.setText("Informe o número do telefone!")
//            alerta.duration = Toast.LENGTH_LONG
//            alerta.show()
            Toast.makeText(this,"Informe o número do telefone!" ,
                Toast.LENGTH_LONG).show()
        }else{
            var uri = Uri.parse( "tel:" + etValor.text.toString())
//            var intent = Intent(Intent.ACTION_DIAL, uri )
            var intent = Intent(Intent.ACTION_CALL, uri )
            if( ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        val permissoes = arrayOf( android.Manifest.permission.CALL_PHONE )
                        ActivityCompat.requestPermissions(this, permissoes, 1)

            }
            try {
                startActivity(intent)
            }catch ( e: Exception){
                Log.i("erro", e.toString())
            }

        }
    }

    fun acessar(view : View){
        if( etValor.text.toString().isEmpty() ) {
            var snackbar = Snackbar.make( view,
                "Informe a URL!", Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.setTextColor(Color.WHITE)
            snackbar.show()
        }else{
            var uri = Uri.parse( etValor.text.toString())
            var intent = Intent(Intent.ACTION_VIEW, uri )
            startActivity(intent)
        }

    }

}
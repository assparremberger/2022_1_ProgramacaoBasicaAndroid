package br.pro.adalto.appformasgeometricas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var rgFormas: RadioGroup
    lateinit var rbRetangulo: RadioButton
    lateinit var rbTrianguloRetangulo: RadioButton
    lateinit var etCampo1: EditText
    lateinit var etCampo2: EditText
    lateinit var etCampo3: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var forma = FormaGeometrica(3.0, 2.0)
//        Log.i("area", "Área: " + forma.area )
//
//        var tr = TrianguloRetangulo(3.0, 4.0, 5.0)
//        Log.i("area", "Hipotenusa: " + tr.hipotenusa )
        rbRetangulo = findViewById(R.id.rbRetangulo)
        rbTrianguloRetangulo = findViewById(R.id.rbTriangulo)
        etCampo1 = findViewById(R.id.etCampo1)
        etCampo2 = findViewById(R.id.etCampo2)
        etCampo3 = findViewById(R.id.etCampo3)
        rgFormas = findViewById(R.id.rgFormas)

        esconder()

        rgFormas.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            esconder()
            if( rbRetangulo.isChecked ){
                etCampo1.visibility = View.VISIBLE
                etCampo2.visibility = View.VISIBLE
                etCampo1.setHint("Base: ")
                etCampo2.setHint("Altura: ")
            }
            if( rbTrianguloRetangulo.isChecked ){
                etCampo1.visibility = View.VISIBLE
                etCampo2.visibility = View.VISIBLE
                etCampo3.visibility = View.VISIBLE
                etCampo1.setHint("Cateto A: ")
                etCampo2.setHint("Cateto B: ")
                etCampo3.setHint("Hipotenusa: ")
            }
        })


    }
    fun esconder(){
        etCampo1.visibility = View.INVISIBLE
        etCampo2.visibility = View.INVISIBLE
        etCampo3.visibility = View.INVISIBLE
       // rgFormas.clearCheck()
        etCampo1.setText("")
        etCampo2.setText("")
        etCampo3.setText("")
    }

    fun calcular(view: View){
        if( etCampo1.text.isEmpty()  || etCampo2.text.isEmpty()){

        }else {
            var area: Double = 0.0
            var valor1 = etCampo1.text.toString().toDouble()
            var valor2 = etCampo2.text.toString().toDouble()
            if( rbRetangulo.isChecked ){
                var retangulo = Retangulo(valor1, valor2)
                area = retangulo.calcularArea()
            }
            if ( rbTrianguloRetangulo.isChecked ){
                var hipotenusa = 0.0
                if( etCampo3.text.isEmpty() ){
                    hipotenusa = TrianguloRetangulo.calcularHipotenusa(valor1, valor2)
                    etCampo3.setText( hipotenusa.toString() )
                }else{
                    hipotenusa = etCampo3.text.toString().toDouble()
                }
                var trianguloRetangulo = TrianguloRetangulo(valor1, valor2, hipotenusa)
                area = trianguloRetangulo.calcularArea()
            }
            var tvArea = findViewById<TextView>(R.id.tvArea)
            tvArea.setText( area.toString() )
        }
    }

}
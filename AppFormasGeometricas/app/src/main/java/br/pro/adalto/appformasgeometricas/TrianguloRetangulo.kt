package br.pro.adalto.appformasgeometricas

class TrianguloRetangulo(base: Double, altura: Double, var hipotenusa: Double ) : FormaGeometrica(base, altura){

    override fun calcularArea(): Double {
        return super.calcularArea() / 2
    }

    override fun calcularPerimetro(): Double {
        return base + altura + hipotenusa
    }

    companion object{
        fun calcularHipotenusa(catA: Double, catB: Double): Double {
            return Math.sqrt(Math.pow(catA, 2.0) + Math.pow(catB, 2.0))
        }
    }


}
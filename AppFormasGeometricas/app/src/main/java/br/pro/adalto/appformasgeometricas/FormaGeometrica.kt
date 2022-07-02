package br.pro.adalto.appformasgeometricas

//class FormaGeometrica(base: Double, altura: Double) {
//    var base: Double = 0.0
//    var altura: Double = 0.0
//    init {
//        this.base = base
//        this.altura = altura
//    }
//}

//class FormaGeometrica(var base: Double, var altura: Double) {
//    var area : Double = 0.0
//    init {
//        this.area = base * altura
//    }
//
//}

open class FormaGeometrica {
    var base: Double = 0.0
    var altura: Double = 0.0
    var area: Double = 0.0

    constructor(base: Double, altura: Double){
        this.base = base
        this.altura = altura
        area = base * altura
    }

    open fun calcularArea(): Double{
        return  base * altura
    }

    open fun calcularPerimetro(): Double{
        return (base + altura) * 2
    }
}
package net.azarquiel.caravanretrofit.model

import java.io.Serializable

data class Comunidad(var id:Int, var nombre:String):Serializable
data class Provincia(var id:Int, var comunidad:Int, var nombre:String):Serializable
data class Municipio(var id:Int, var nombre:String, var provincia:Int, var latitud:String, var longitud:String):Serializable
data class Lugar(var id:Int, var titre:String, var description_es:String, var ville:String, var photos:List<Foto>):Serializable
data class Foto(var id:Int, var link_thumb:String, var link_large:String):Serializable
data class Punto(var id:Int, var usuario:Int, var lugar:Int, var puntos:Int):Serializable
data class Usuario(var id:Int, var nick:String, var pass:String):Serializable

data class Respuesta(
    var comunidades:List<Comunidad>,
    var provincias:List<Provincia>,
    var municipios:List<Municipio>,
    var lieux:List<Lugar>,
    var usuario: Usuario,
    var avg:String,
    var punto:Punto
)
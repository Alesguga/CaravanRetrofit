package net.azarquiel.caravanretrofit.api

import net.azarquiel.caravanretrofit.model.Comunidad
import net.azarquiel.caravanretrofit.model.Lugar
import net.azarquiel.caravanretrofit.model.Municipio
import net.azarquiel.caravanretrofit.model.Provincia
import net.azarquiel.caravanretrofit.model.Punto
import net.azarquiel.caravanretrofit.model.Usuario

class MainRepository() {
    val service = WebAccess.caravanService

    suspend fun getComunidades(): List<Comunidad> {
        val webResponse = service.getComunidades().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comunidades
        }
        return emptyList()
    }


    suspend fun getProviciasByComunidad(idcomunidad:Int): List<Provincia> {
        val webResponse = service.getProviciasByComunidad(idcomunidad).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.provincias
        }
        return emptyList()
    }

    suspend fun getMunicipioByProvincia(idprovincia:Int): List<Municipio> {
        val webResponse = service.getMunicipioByProvincia(idprovincia).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.municipios
        }
        return emptyList()
    }

    suspend fun getLugaresByUbicacion(latitud:String, longitud:String): List<Lugar> {
        val webResponse = service.getLugaresByUbicacion(latitud, longitud).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.lieux
        }
        return emptyList()
    }

    suspend fun getStarLugar(idlugar:Int): String {
        val webResponse = service.getStarLugar(idlugar).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avg
        }
        return "0"
    }


    suspend fun savePuntos(idlugar:Int, idusuario:Int, puntos:Int): Punto? {
        var puntoResponse:Punto? = null
        val webResponse = service.savePuntos(idlugar, idusuario, puntos ).await()
        if (webResponse.isSuccessful) {
            puntoResponse = webResponse.body()!!.punto
        }
        return puntoResponse
    }

    suspend fun getUsuario(nick:String, pass:String): Usuario? {
        val webResponse = service.getUsuario(nick,pass).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        var usuarioResponse:Usuario? = null
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            usuarioResponse = webResponse.body()!!.usuario
        }
        return usuarioResponse
    }
}


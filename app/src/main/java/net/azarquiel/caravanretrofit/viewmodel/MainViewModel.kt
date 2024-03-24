package net.azarquiel.caravanretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.caravanretrofit.api.MainRepository
import net.azarquiel.caravanretrofit.model.Comunidad
import net.azarquiel.caravanretrofit.model.Lugar
import net.azarquiel.caravanretrofit.model.Municipio
import net.azarquiel.caravanretrofit.model.Provincia
import net.azarquiel.caravanretrofit.model.Punto
import net.azarquiel.caravanretrofit.model.Usuario

// ……

/**
 * Created by Paco Pulido.
 */
class MainViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getComunidades(): MutableLiveData<List<Comunidad>> {
        val comunidades = MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Main) {
            comunidades.value = repository.getComunidades()
        }
        return comunidades
    }

    fun getProviciasByComunidad(idcomunidad:Int): MutableLiveData<List<Provincia>> {
        val provincias = MutableLiveData<List<Provincia>>()
        GlobalScope.launch(Main) {
            provincias.value = repository.getProviciasByComunidad(idcomunidad)
        }
        return provincias
    }

    fun getMunicipioByProvincia(idprovincia:Int): MutableLiveData<List<Municipio>> {
        val municipios = MutableLiveData<List<Municipio>>()
        GlobalScope.launch(Main) {
            municipios.value = repository.getMunicipioByProvincia(idprovincia)
        }
        return municipios
    }

    fun getLugaresByUbicacion(latitud:String, longitud:String): MutableLiveData<List<Lugar>> {
        val lugares = MutableLiveData<List<Lugar>>()
        GlobalScope.launch(Main) {
            lugares.value = repository.getLugaresByUbicacion(latitud, longitud)
        }
        return lugares
    }

    fun getStarLugar(idlugar:Int): MutableLiveData<String> {
        val media = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            media.value = repository.getStarLugar(idlugar)
        }
        return media
    }
    fun savePuntos(idlugar:Int, idusuario:Int, puntos:Int):MutableLiveData<Punto> {
        val punto= MutableLiveData<Punto>()
        GlobalScope.launch(Main) {
            punto.value = repository.savePuntos(idlugar, idusuario, puntos)
        }
        return punto
    }

    fun getUsuario(nick:String, pass:String): MutableLiveData<Usuario> {
        val usuario = MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuario.value = repository.getUsuario(nick,pass)
        }
        return usuario
    }

    fun saveUsuario(usuario: Usuario):MutableLiveData<Usuario> {
        val usuarioRespose= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioRespose.value = repository.saveUsuario(usuario)
        }
        return usuarioRespose
    }

}

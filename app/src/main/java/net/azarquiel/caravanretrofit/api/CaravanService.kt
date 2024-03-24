package net.azarquiel.caravanretrofit.api

import kotlinx.coroutines.Deferred
import net.azarquiel.caravanretrofit.model.Respuesta
import net.azarquiel.caravanretrofit.model.Usuario
import retrofit2.Response
import retrofit2.http.*
/**
 * Created by Paco Pulido.
 */
interface CaravanService {
    // No necesita nada para trabajar
    @GET("comunidad")
    fun getComunidades(): Deferred<Response<Respuesta>>

    @GET("comunidad/{idcomunidad}/provincia")
    fun getProviciasByComunidad(@Path("idcomunidad") idcomunidad:Int): Deferred<Response<Respuesta>>

    @GET("provincia/{idprovincia}/municipio")
    fun getMunicipioByProvincia(@Path("idprovincia") idprovincia:Int): Deferred<Response<Respuesta>>

    @GET("lugar")
    fun getLugaresByUbicacion(
        @Query("latitud") latitud:String,
        @Query("longitud") longitud:String): Deferred<Response<Respuesta>>

    // nick y pass variables sueltas en la url?nick=paco&pass=paco => @Query
    @GET("usuario")
    fun getUsuario(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Respuesta>>

    @GET("lugar/{idlugar}/avgpuntos")
    fun getStarLugar(@Path("idlugar") idlugar:Int): Deferred<Response<Respuesta>>

    // post con objeto => @Body
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Respuesta>>

    // post con variables sueltas => @Field y Obligatorio @FormUrlEncoded
    @FormUrlEncoded
    @POST("lugar/{idlugar}/puntos")
    fun savePuntos(@Path("idlugar") idlugar: Int,
                   @Field("usuario") idusuario: Int,
                   @Field("puntos") puntos: Int): Deferred<Response<Respuesta>>

// ……..   resto de métodos de la interfaz ………..
}

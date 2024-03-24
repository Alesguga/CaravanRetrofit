package net.azarquiel.caravanretrofit.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Paco Pulido.
 */

object WebAccess {

    val caravanService : CaravanService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apicaravan/")
            .build()

        return@lazy retrofit.create(CaravanService::class.java)
    }
}
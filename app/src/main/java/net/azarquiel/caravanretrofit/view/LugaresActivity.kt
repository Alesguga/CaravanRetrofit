package net.azarquiel.caravanretrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravanretrofit.R
import net.azarquiel.caravanretrofit.model.Comunidad
import net.azarquiel.caravanretrofit.model.Lugar
import net.azarquiel.caravanretrofit.model.Municipio
import net.azarquiel.caravanretrofit.model.Provincia
import net.azarquiel.caravanretrofit.model.Usuario
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel
import net.azarquiel.recyclerviewpajaros.adapter.AdapterLugar
import net.azarquiel.recyclerviewpajaros.adapter.AdapterProvincia

class LugaresActivity : AppCompatActivity() {
    private var usuario: Usuario?=null
    private lateinit var adapter: AdapterLugar
    private lateinit var rvlugares: RecyclerView
    private lateinit var municipio: Municipio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugares)


        municipio = intent.getSerializableExtra("municipio") as Municipio
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        rvlugares = findViewById<RecyclerView>(R.id.rvlugares)

        initRV()

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLugaresByUbicacion(municipio.latitud, municipio.longitud).observe(this, Observer {
            it?.let{
                adapter.setLugares(it)
            }
        })
    }

    private fun initRV() {
        adapter = AdapterLugar(this, R.layout.rowlugar)
        rvlugares.adapter = adapter
        rvlugares.layoutManager = LinearLayoutManager(this)
    }

    fun onClickLugar(v: View) {
        val lugar = v.tag as Lugar
        val intent = Intent(this, LugarDetailActivity::class.java)
        intent.putExtra("lugar", lugar)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }
}
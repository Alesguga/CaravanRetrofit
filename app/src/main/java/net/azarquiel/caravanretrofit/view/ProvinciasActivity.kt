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
import net.azarquiel.caravanretrofit.model.Provincia
import net.azarquiel.caravanretrofit.model.Usuario
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel
import net.azarquiel.recyclerviewpajaros.adapter.AdapterComunidad
import net.azarquiel.recyclerviewpajaros.adapter.AdapterProvincia

class ProvinciasActivity : AppCompatActivity() {
    private var usuario: Usuario?=null
    private lateinit var adapter: AdapterProvincia
    private lateinit var rvprovincia: RecyclerView
    private lateinit var comunidad: Comunidad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provincias)

        comunidad = intent.getSerializableExtra("comunidad") as Comunidad
        usuario = intent.getSerializableExtra("usuario") as Usuario?

        rvprovincia = findViewById<RecyclerView>(R.id.rvprovincia)
        initRV()

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getProviciasByComunidad(comunidad.id).observe(this, Observer {
            it?.let{
                adapter.setProvincias(it)
            }
        })
    }

    private fun initRV() {
        adapter = AdapterProvincia(this, R.layout.rowprovincia)
        rvprovincia.adapter = adapter
        rvprovincia.layoutManager = LinearLayoutManager(this)
    }

    fun onClickProvincia(v: View) {
        val provincia = v.tag as Provincia
        val intent = Intent(this, MunicipioActivity::class.java)
        intent.putExtra("provincia", provincia)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }


}
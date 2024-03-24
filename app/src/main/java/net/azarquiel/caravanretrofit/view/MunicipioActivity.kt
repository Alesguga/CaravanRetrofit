package net.azarquiel.caravanretrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravanretrofit.R
import net.azarquiel.caravanretrofit.model.Municipio
import net.azarquiel.caravanretrofit.model.Provincia
import net.azarquiel.caravanretrofit.model.Usuario
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel
import net.azarquiel.recyclerviewpajaros.adapter.AdapterMunicipio

class MunicipioActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var usuario: Usuario?=null
    private lateinit var municipios: List<Municipio>
    private lateinit var adapter: AdapterMunicipio
    private lateinit var rvmunicipio: RecyclerView
    private lateinit var provincia: Provincia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_municipio)

        val toolBar = findViewById<Toolbar>(R.id.toolbarmunicipios)
        setSupportActionBar(toolBar)

        provincia = intent.getSerializableExtra("provincia") as Provincia
        usuario = intent.getSerializableExtra("usuario") as Usuario?

        rvmunicipio = findViewById<RecyclerView>(R.id.rvmunicipio)
        initRV()

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.getMunicipioByProvincia(provincia.id).observe(this, Observer {
            it?.let{
                municipios = it
                adapter.setMunicipios(it)
            }
        })
    }

    private fun initRV() {
        adapter = AdapterMunicipio(this, R.layout.rowmunicipio)
        rvmunicipio.adapter = adapter
        rvmunicipio.layoutManager = LinearLayoutManager(this)
    }

    fun onClickMunicipio(v: View) {
        val municipio = v.tag as Municipio
        val intent = Intent(this, LugaresActivity::class.java)
        intent.putExtra("municipio", municipio)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.municipio_menu, menu)
        // ************* <Filtro> ************
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
// ************* </Filtro> ************

        return true
    }



    // ************* <Filtro> ************
    override fun onQueryTextChange(query: String): Boolean {
        val original = ArrayList<Municipio>(municipios)
        adapter.setMunicipios(original.filter { municipio -> municipio.nombre.contains(query,true) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
// ************* </Filtro> ************


}
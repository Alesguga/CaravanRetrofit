package net.azarquiel.caravanretrofit.view

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import net.azarquiel.caravanretrofit.R
import net.azarquiel.caravanretrofit.model.Lugar
import net.azarquiel.caravanretrofit.model.Usuario
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel

class LugarDetailActivity : AppCompatActivity() {
    private var usuario: Usuario?=null
    private lateinit var rbvotar: RatingBar
    private lateinit var rbcurrent: RatingBar
    private lateinit var linearfotos: LinearLayout
    private lateinit var tvdescripciondetail: TextView
    private lateinit var tvtitulodetail: TextView
    private lateinit var lugar: Lugar
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugar_detail)
        findView()

        lugar = intent.getSerializableExtra("lugar") as Lugar
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        show()
    }

    private fun findView() {
        tvtitulodetail = findViewById<TextView>(R.id.tvtitulodetail)
        tvdescripciondetail = findViewById<TextView>(R.id.tvdescripciondetail)
        linearfotos = findViewById<LinearLayout>(R.id.linearfotos)
        rbcurrent = findViewById<RatingBar>(R.id.rbcurrent)
        rbcurrent.isEnabled = false
        rbvotar = findViewById<RatingBar>(R.id.rbvotar)
        rbvotar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            updateStarLugar(rating)
        }
    }

    private fun show() {
        tvtitulodetail.text = "${lugar.titre} - ${lugar.id}"
        tvdescripciondetail.text = lugar.description_es

        lugar.photos.forEach {
            val iv = ImageView(this)
            val lp = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 700)
            lp.setMargins(0,0, 10, 0)
            iv.layoutParams = lp
            Picasso.get().load(it.link_large).into(iv)
            linearfotos.addView(iv)
        }
        showStarCurrent()
    }

    private fun showStarCurrent() {
        // llamar a la api para ver las estrellas que tiene este lugar
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getStarLugar(lugar.id).observe(this, Observer {
            it?.let{
                rbcurrent.rating = it.toFloat()
            }
        })
    }

    private fun updateStarLugar(rating: Float) {
        if (usuario==null) {
            Toast.makeText(this, "No login, No rating...", Toast.LENGTH_SHORT).show()
            return
        }
        val star = rating.toInt()
        viewModel.savePuntos(lugar.id, usuario!!.id, star).observe(this, Observer {
            it?.let{
                Toast.makeText(this, "Anotado $rating stars a ${lugar.titre}", Toast.LENGTH_SHORT).show()
                showStarCurrent()
                rbvotar.isEnabled = false
            }
        })


    }
}
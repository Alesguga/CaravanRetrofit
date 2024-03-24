package net.azarquiel.recyclerviewpajaros.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravanretrofit.R
import net.azarquiel.caravanretrofit.model.Municipio

/**
 * Created by pacopulido
 */
class AdapterMunicipio(val context: Context,
                       val layout: Int
                    ) : RecyclerView.Adapter<AdapterMunicipio.ViewHolder>() {

    private var dataList: List<Municipio> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setMunicipios(municipios: List<Municipio>) {
        this.dataList = municipios
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Municipio){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvrowmunicipio = itemView.findViewById(R.id.tvrowmunicipio) as TextView

            tvrowmunicipio.text = dataItem.nombre

            itemView.tag = dataItem

        }

    }
}
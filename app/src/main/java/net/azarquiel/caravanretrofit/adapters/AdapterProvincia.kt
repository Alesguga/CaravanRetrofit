package net.azarquiel.recyclerviewpajaros.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravanretrofit.R
import net.azarquiel.caravanretrofit.model.Provincia

/**
 * Created by pacopulido
 */
class AdapterProvincia(val context: Context,
                       val layout: Int
                    ) : RecyclerView.Adapter<AdapterProvincia.ViewHolder>() {

    private var dataList: List<Provincia> = emptyList()

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

    internal fun setProvincias(provincias: List<Provincia>) {
        this.dataList = provincias
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Provincia){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvrowprovincia = itemView.findViewById(R.id.tvrowprovincia) as TextView

            tvrowprovincia.text = dataItem.nombre

            itemView.tag = dataItem

        }

    }
}
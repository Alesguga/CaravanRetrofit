package net.azarquiel.recyclerviewpajaros.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravanretrofit.R
import net.azarquiel.caravanretrofit.model.Lugar

/**
 * Created by pacopulido
 */
class AdapterLugar(val context: Context,
                   val layout: Int
                    ) : RecyclerView.Adapter<AdapterLugar.ViewHolder>() {

    private var dataList: List<Lugar> = emptyList()

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

    internal fun setLugares(lugares: List<Lugar>) {
        this.dataList = lugares
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Lugar){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvtitulorowlugar = itemView.findViewById(R.id.tvtitulorowlugar) as TextView
            val tvdescripcionrowlugar = itemView.findViewById(R.id.tvdescripcionrowlugar) as TextView

            tvtitulorowlugar.text = dataItem.titre
            tvdescripcionrowlugar.text = dataItem.description_es

            itemView.tag = dataItem

        }

    }
}
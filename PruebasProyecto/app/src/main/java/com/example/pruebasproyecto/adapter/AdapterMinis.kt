package com.example.pruebasproyecto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebasproyecto.R
import com.example.pruebasproyecto.model.Minis

class AdapterMinis(var listaMinis:List<Minis>) : RecyclerView.Adapter<AdapterMinis.MyHolder>() {
    //TODO cambiar fuente letra recycler
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nombre:TextView
        var imagen: ImageView

        init {
            nombre = itemView.findViewById(R.id.recycler_item_text)
            imagen = itemView.findViewById(R.id.recycler_item_imagen)
        }
    }
    fun addMini(minis: Minis){
        (listaMinis as ArrayList<Minis>).add(minis)
        notifyItemInserted(listaMinis.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_minis, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val mini = listaMinis[position]
        holder.nombre.text = mini.nombrePerfil
        //holder.imagen = mini.imagen
    }

    override fun getItemCount(): Int {
        return listaMinis.size
    }
}
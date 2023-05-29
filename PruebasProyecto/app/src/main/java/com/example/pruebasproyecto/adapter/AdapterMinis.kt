package com.example.pruebasproyecto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebasproyecto.R
import com.example.pruebasproyecto.model.Perfil

class AdapterMinis(var listaMinis:List<Perfil>,var contexto:Context) : RecyclerView.Adapter<AdapterMinis.MyHolder>() {
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nombre : TextView
        var imagen: ImageView


        init {
            nombre = itemView.findViewById(R.id.recycler_item_text)
            imagen = itemView.findViewById(R.id.recycler_item_imagen)
        }
    }
    fun addMini(minis: Perfil){
        (listaMinis as ArrayList<Perfil>).add(minis)
        notifyItemInserted(listaMinis.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_perfil, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val mini = listaMinis[position]
        holder.nombre.text = mini.nombrePerfil
        Glide.with(contexto).load(mini.imagen).into(holder.imagen)
    }

    override fun getItemCount(): Int {
        return listaMinis.size
    }

    fun filtrar(listaFiltrada:ArrayList<Perfil>){
        this.listaMinis = listaFiltrada
        notifyDataSetChanged()
    }
}
package com.example.pruebasproyecto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebasproyecto.R
import com.example.pruebasproyecto.model.Perfil
import com.example.pruebasproyecto.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AdapterFavoritos(var listaMinis:List<Perfil>, var contexto: Context, var supporFragmentManager: FragmentManager) : RecyclerView.Adapter<AdapterFavoritos.MyHolder>() {

    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private var usuario: Usuario? = null

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nombre : TextView
        var cantidad: TextView
        var especie: TextView
        var universo: TextView
        var imagen: ImageView


        init {
            nombre = itemView.findViewById(R.id.label_nombre)
            imagen = itemView.findViewById(R.id.item_imagen)
            universo = itemView.findViewById(R.id.label_universo)
            especie = itemView.findViewById(R.id.label_especie)
            cantidad = itemView.findViewById(R.id.label_cantidad)

        }
    }
    fun addMini(mini: Perfil){
        (listaMinis as ArrayList<Perfil>).add(mini)
        notifyItemInserted(listaMinis.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_favoritos, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        //NO TOCAR
        val mini = listaMinis[position]
        holder.nombre.text = mini.nombrePerfil
        Glide.with(contexto).load(mini.imagen).into(holder.imagen)
        holder.especie.text = mini.especie
        holder.cantidad.text = mini.nMinis.toString()
        holder.universo.text = mini.universo
    }

    override fun getItemCount(): Int {
        return listaMinis.size
    }

    fun filtrar(listaFiltrada:ArrayList<Perfil>){
        this.listaMinis = listaFiltrada
        notifyDataSetChanged()
    }
}
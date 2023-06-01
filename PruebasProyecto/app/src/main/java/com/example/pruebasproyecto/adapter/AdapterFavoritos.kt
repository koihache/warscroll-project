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

//Construimos el Adaptador recibiendo como parámetros la lista de miniaturas para poder pintarlas y el contexto para inflar la vista
class AdapterFavoritos(var listaMinis: List<Perfil>, var contexto: Context) :
    RecyclerView.Adapter<AdapterFavoritos.MyHolder>() {

    //Declaramos el holder para poder llamar a la vista y representar los datos de cada uno de los objetos de la lista
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombre: TextView
        var cantidad: TextView
        var especie: TextView
        var universo: TextView
        var imagen: ImageView

        //Inicializamos los items para poder pintarlos más tarde
        init {
            nombre = itemView.findViewById(R.id.text_nombre)
            imagen = itemView.findViewById(R.id.item_imagen)
            universo = itemView.findViewById(R.id.text_universo)
            especie = itemView.findViewById(R.id.text_especie)
            cantidad = itemView.findViewById(R.id.text_cantidad)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        //Inflamos la vista para poder visualizar el item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_favoritos, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //Obtenemos los parametros que usaremos para poder pintarlos en cada item del holder
        val mini = listaMinis[position]
        holder.nombre.text = mini.nombrePerfil
        Glide.with(contexto).load(mini.imagen).into(holder.imagen)
        holder.especie.text = mini.especie
        holder.cantidad.text = mini.nMinis.toString()
        holder.universo.text = mini.universo
    }

    //Nos devuelve la cantidad de items que tiene la lista
    override fun getItemCount(): Int {
        return listaMinis.size
    }

    //Agrega miniaturas a la lista y permite usarla para la funcion filtrar
    fun addMini(mini: Perfil) {
        (listaMinis as ArrayList<Perfil>).add(mini)
        notifyItemInserted(listaMinis.size - 1)
    }

    //Realiza el cambio de la lista sin filtrar a la lista filtrada
    fun filtrar(listaFiltrada: ArrayList<Perfil>) {
        this.listaMinis = listaFiltrada
        notifyDataSetChanged()
    }
}
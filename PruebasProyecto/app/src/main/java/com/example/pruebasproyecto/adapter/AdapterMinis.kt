package com.example.pruebasproyecto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebasproyecto.dialog.DialogoPerfil
import com.example.pruebasproyecto.R
import com.example.pruebasproyecto.model.Perfil
import com.example.pruebasproyecto.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AdapterMinis(var listaMinis:List<Perfil>,var contexto:Context, var supporFragmentManager: FragmentManager) : RecyclerView.Adapter<AdapterMinis.MyHolder>() {

    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private var usuario: Usuario? = null

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nombre : TextView
        var imagen: ImageView
        var constraintLayout: ConstraintLayout
        var botonFav: Switch


        init {
            nombre = itemView.findViewById(R.id.text_nombre)
            imagen = itemView.findViewById(R.id.item_imagen)
            constraintLayout = itemView.findViewById(R.id.recycler_item_constraint)
            botonFav = itemView.findViewById(R.id.recycler_item_fav)
        }
    }
    fun addMini(mini: Perfil){
        (listaMinis as ArrayList<Perfil>).add(mini)
        notifyItemInserted(listaMinis.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_perfil, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //TODO Favoritos
        /*dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth


        dataBase.getReference("usuarios").orderByChild("idUsuario").equalTo(auth.uid!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (i in snapshot.children) {
                            usuario = (i.getValue(Usuario::class.java) as Usuario)
                        }
                        //TODO Revisar porque no puedo sacar el valor
                        //TODO usuario fuera de la sentencia de la bdd
                        usuario!!.favoritos!!.forEach {



                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })*/


        //NO TOCAR
        val mini = listaMinis[position]
        holder.nombre.text = mini.nombrePerfil
        Glide.with(contexto).load(mini.imagen).into(holder.imagen)
        holder.constraintLayout.setOnClickListener {

            val dialogo = DialogoPerfil.newInstance(mini)
            dialogo.show(supporFragmentManager,"")

        }
    }

    override fun getItemCount(): Int {
        return listaMinis.size
    }

    fun filtrar(listaFiltrada:ArrayList<Perfil>){
        this.listaMinis = listaFiltrada
        notifyDataSetChanged()
    }
}
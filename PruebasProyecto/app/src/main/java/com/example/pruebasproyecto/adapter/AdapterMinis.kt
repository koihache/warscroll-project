package com.example.pruebasproyecto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebasproyecto.R
import com.example.pruebasproyecto.dialog.DialogoPerfil
import com.example.pruebasproyecto.model.Perfil
import com.example.pruebasproyecto.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class AdapterMinis(
    var listaMinis: List<Perfil>,
    var contexto: Context,
    var supporFragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterMinis.MyHolder>() {

    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private val favoritosSet = HashSet<String>()


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombre: TextView
        var imagen: ImageView
        var constraintLayout: ConstraintLayout
        var botonFav: Switch
        var esFavorito: Boolean = false


        init {
            nombre = itemView.findViewById(R.id.text_nombre)
            imagen = itemView.findViewById(R.id.item_imagen)
            constraintLayout = itemView.findViewById(R.id.recycler_item_constraint)
            botonFav = itemView.findViewById(R.id.recycler_item_fav)
        }
    }

    fun addMini(mini: Perfil) {
        (listaMinis as ArrayList<Perfil>).add(mini)
        notifyItemInserted(listaMinis.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_perfil, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth

        val mini = listaMinis[position]
        holder.nombre.text = mini.nombrePerfil
        Glide.with(contexto).load(mini.imagen).into(holder.imagen)
        holder.constraintLayout.setOnClickListener {

            val dialogo = DialogoPerfil.newInstance(mini)
            dialogo.show(supporFragmentManager, "")
        }
        holder.esFavorito = favoritosSet.contains(mini.idMini.toString())

        holder.botonFav.isChecked = holder.esFavorito

        dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos").orderByChild("name")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (i in snapshot.children) {
                            if((i.getValue(Perfil::class.java) as Perfil)==(mini)){
                                holder.botonFav.isChecked = true;
                            }
                        }
                    } else {
                        //ERROR EN SNAPSHOT
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


        holder.botonFav.setOnClickListener {
            holder.esFavorito = !holder.esFavorito
            holder.botonFav.isChecked = holder.esFavorito

            // Realizar acciones según el estado de esFavorito
            if (holder.esFavorito) {
                dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos")
                    .child(mini.nombrePerfil.toString()).setValue(mini)
            } else {
                dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos")
                    .child(mini.nombrePerfil.toString()).setValue(null)
            }
        }
    }

    override fun getItemCount(): Int {
        return listaMinis.size
    }

    fun filtrar(listaFiltrada: ArrayList<Perfil>) {
        this.listaMinis = listaFiltrada
        notifyDataSetChanged()
    }
}
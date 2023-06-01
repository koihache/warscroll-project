package com.example.pruebasproyecto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
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

//Construimos el Adaptador recibiendo como parámetros la lista de miniaturas para poder pintarlas y el contexto para inflar la vista
class AdapterMinis(
    var listaMinis: List<Perfil>,
    var contexto: Context,
    var supporFragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterMinis.MyHolder>() {

    //Declaramos algunas variables como lo son la base de datos, la autenticacion y un hashset que nos ayudará a no se duplique la accion del boton favoritos
    //a la hora de pulsarse
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private val favoritosSet = HashSet<String>()

    //Declaramos el holder para poder llamar a la vista y representar los datos de cada uno de los objetos de la lista
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombre: TextView
        var imagen: ImageView
        var constraintLayout: ConstraintLayout
        var botonFav: Switch
        var esFavorito: Boolean = false

        //Inicializamos los items para poder pintarlos más tarde
        init {
            nombre = itemView.findViewById(R.id.text_nombre)
            imagen = itemView.findViewById(R.id.item_imagen)
            constraintLayout = itemView.findViewById(R.id.recycler_item_constraint)
            botonFav = itemView.findViewById(R.id.recycler_item_fav)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        //Inflamos la vista para poder visualizar el item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_perfil, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //Definimos valores a la base de datos y a la autenticacion para poder usarlas
        dataBase = FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")
        auth = Firebase.auth

        //Obtenemos los parametros que usaremos para poder pintarlos en cada item del holder
        val mini = listaMinis[position]
        holder.nombre.text = mini.nombrePerfil
        Glide.with(contexto).load(mini.imagen).into(holder.imagen)

        //Manejamos la pulsación del item para que muestre un dialogo que dará información al usuario acerca del perfil
        holder.constraintLayout.setOnClickListener {
            val dialogo = DialogoPerfil.newInstance(mini)
            dialogo.show(supporFragmentManager, "")
        }

        //Rellenamos con información que nos permitirá realizar la lectura de favoritos
        holder.esFavorito = favoritosSet.contains(mini.idMini.toString())
        holder.botonFav.isChecked = holder.esFavorito

        //Hacemos la comprobación de los perfiles dentro del usuario
        dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos").orderByChild("name")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (i in snapshot.children) {
                            if ((i.getValue(Perfil::class.java) as Perfil) == (mini)) {
                                holder.botonFav.isChecked = true;
                            }
                        }
                    } else {
                        Toast.makeText(contexto,"No hay perfiles",Toast.LENGTH_SHORT)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        contexto,
                        "Error en la base de datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })

        //Manejamos pulsacion al pulsar el Switch de favorito
        holder.botonFav.setOnClickListener {
            holder.esFavorito = !holder.esFavorito
            holder.botonFav.isChecked = holder.esFavorito


            if (holder.esFavorito) {
                //Setea la mini/perfil dentro del usuario en la bbdd
                dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos")
                    .child(mini.nombrePerfil.toString()).setValue(mini)
            } else {
                //Borra la mini/perfil dentro del usuario en la bbdd
                dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos")
                    .child(mini.nombrePerfil.toString()).setValue(null)
            }
        }
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
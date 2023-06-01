package com.example.pruebasproyecto

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.FragmentInicioBinding
import com.example.pruebasproyecto.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    private var usuario: Usuario? = null
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: OnSaberMasListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //Igualamos al contexto para llenar la interfaz
        listener = context as OnSaberMasListener

        //Traemos bbdd y autenticacion
        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth

        //Traemos el usuario que ha hecho login
        dataBase.getReference("usuarios").orderByChild("idUsuario").equalTo(auth.uid!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (i in snapshot.children) {
                            usuario = (i.getValue(Usuario::class.java) as Usuario)
                        }
                        //Asignamos el valor al item correspondiente
                        binding.textInicioNombreUsuario.text = usuario!!.usuario;
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context,"Ha ocurrido un error en bbdd",Toast.LENGTH_SHORT).show()
                }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Manejamos pulsacion botones de sabermas
        binding.botonInicioSabermasWarcry.setOnClickListener {
            listener.onSaberMasSelected(1)
        }
        binding.botonInicioSabermasKillteam.setOnClickListener {
            listener.onSaberMasSelected(2)
        }
    }

    //Interfaz de callback para gestionar pulsaciones de boton
    interface OnSaberMasListener{
        fun onSaberMasSelected(opcion: Int)
    }

}
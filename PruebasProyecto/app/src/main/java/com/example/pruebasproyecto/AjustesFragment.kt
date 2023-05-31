package com.example.pruebasproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.FragmentAjustesBinding
import com.example.pruebasproyecto.dialog.DialogoBorrarDatos
import com.example.pruebasproyecto.dialog.DialogoCerrarSesion
import com.example.pruebasproyecto.dialog.DialogoContrasenia
import com.example.pruebasproyecto.dialog.DialogoTerminos
import com.example.pruebasproyecto.dialog.DialogoUsuario
import com.example.pruebasproyecto.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class AjustesFragment: Fragment(){

    private var _binding: FragmentAjustesBinding? = null

    private val binding get() = _binding!!
    private var usuario: Usuario? = null
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth


        binding.botonAjustesPerfil.setOnClickListener {


            dataBase.getReference("usuarios").orderByChild("idUsuario").equalTo(auth.uid!!)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (i in snapshot.children) {
                                usuario = (i.getValue(Usuario::class.java) as Usuario)
                            }
                            //TODO Revisar porque no puedo sacar el valor
                            //TODO usuario fuera de la sentencia de la bdd
                            val dialogo = DialogoUsuario.newInstance(usuario?.correo!!, usuario?.usuario!!)
                            dialogo.show(requireActivity().supportFragmentManager,"")
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }

        binding.botonAjustesTerminos.setOnClickListener {
            DialogoTerminos().show(requireActivity().supportFragmentManager,"")
        }

        binding.botonAjustesDatos.setOnClickListener {
            DialogoBorrarDatos().show(requireActivity().supportFragmentManager,"")
        }
        binding.botonAjustesPass.setOnClickListener {
            DialogoContrasenia().show(requireActivity().supportFragmentManager,"")
        }

        binding.botonAjustesCerrarsesion.setOnClickListener {
            DialogoCerrarSesion().show(requireActivity().supportFragmentManager,"")
        }

    }
}
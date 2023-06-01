package com.example.pruebasproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.FragmentAjustesBinding
import com.example.pruebasproyecto.dialog.DialogoBorrarDatos
import com.example.pruebasproyecto.dialog.DialogoCerrarSesion
import com.example.pruebasproyecto.dialog.DialogoContrasenia
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
        //Inflamos la vista del fragment
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Traemos la bbdd y la autenticacion
        dataBase = FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")
        auth = Firebase.auth

        //Pulsacion boton mostrar perfil (traemos bbdd por problemas con el transpaso de datos)
        binding.botonAjustesPerfil.setOnClickListener {
            dataBase.getReference("usuarios").orderByChild("idUsuario").equalTo(auth.uid!!)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (i in snapshot.children) {
                                usuario = (i.getValue(Usuario::class.java) as Usuario)
                            }
                            //Crea el dialogo y envia la información necesaria
                            val dialogo = DialogoUsuario.newInstance(usuario?.correo!!, usuario?.usuario!!)
                            dialogo.show(requireActivity().supportFragmentManager,"")
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }

        //Pulsacion boton borrar datos
        binding.botonAjustesDatos.setOnClickListener {
            DialogoBorrarDatos().show(requireActivity().supportFragmentManager,"")
        }
        //Pulsacion boton contrasenia
        binding.botonAjustesPass.setOnClickListener {
            DialogoContrasenia().show(requireActivity().supportFragmentManager,"")
        }
        //Pulsacion boton cerrar sesion
        binding.botonAjustesCerrarsesion.setOnClickListener {
            DialogoCerrarSesion().show(requireActivity().supportFragmentManager,"")
        }

    }
}
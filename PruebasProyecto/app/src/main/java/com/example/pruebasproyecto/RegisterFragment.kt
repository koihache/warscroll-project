package com.example.pruebasproyecto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pruebasproyecto.databinding.FragmentRegisterBinding
import com.example.pruebasproyecto.model.Perfil
import com.example.pruebasproyecto.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private lateinit var dataBase: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Traemos bbdd y autenticacion
        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth

        //Pulsacion boton crear cuenta
        binding.botonCrearcuenta.setOnClickListener {
            //Hacemos comprobación de todos los campos
            if (!binding.editRegisterCorreo.text.isNullOrBlank() && !binding.editRegisterUsuario.text.isNullOrBlank() && !binding.editRegisterPassword.text.isNullOrBlank() && !binding.editRegisterRepetir.text.isNullOrBlank()) {
                if (binding.editRegisterPassword.text.toString().length > 5) {
                    if (binding.editRegisterPassword.text.toString() == binding.editRegisterRepetir.text.toString()) {
                        auth.createUserWithEmailAndPassword(binding.editRegisterCorreo.text.toString(), binding.editRegisterPassword.text.toString())
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    Snackbar.make(
                                        binding.editRegisterCorreo,
                                        "El usuario se ha creado correctamente",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                    //Crea un usuario y lo envía a la bbdd y lo registra en la autenticacion
                                    var usuarioNuevo: Usuario = Usuario(auth.uid,binding.editRegisterCorreo.text.toString(),
                                        binding.editRegisterUsuario.text.toString(), HashMap())
                                    dataBase.reference.child("usuarios").child(usuarioNuevo.idUsuario!!).setValue(usuarioNuevo)

                                    //Nos dirige al Login
                                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                                } else{
                                    Snackbar.make(
                                        binding.editRegisterCorreo,
                                        "El usuario no se ha creado correctamente",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        Snackbar.make(
                            binding.editRegisterCorreo,
                            "Las contraseñas no son iguales",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        binding.editRegisterCorreo,
                        "La contraseña debe tener 6 carácteres mínimo",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.editRegisterCorreo,
                    "Por favor, rellene todos los campos correctamente",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        //Te redirije al Login
        binding.botonVolver.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

        }
    }
}
package com.example.pruebasproyecto

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pruebasproyecto.databinding.FragmentLoginBinding
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


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private lateinit var auth: FirebaseAuth

    private lateinit var dataBase: FirebaseDatabase

    private lateinit var correo: String

    private lateinit var password: String


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        correo = arguments?.getString("correo").toString()
        password = arguments?.getString("password").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        dataBase = FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        //Si no están vacios los campos rellenados anteriormente por el register
        //los pone en los edit para facilitar el usuario el inicio de la sesión

        if ((correo==null) && (password==null)){
            binding.editLoginCorreo.setText(correo)
            binding.editLoginPassword.setText(password)
        }

        //Controlamos la pulsación del botón Iniciar Sesión
        binding.botonLogin.setOnClickListener{

            if(!binding.editLoginCorreo.text.isNullOrBlank() && !binding.editLoginPassword.text.isNullOrBlank()){

                //Mediante el auth, solicitamos iniciar sesión a Firebase
                auth.signInWithEmailAndPassword(
                    binding.editLoginCorreo.text.toString(),
                    binding.editLoginPassword.text.toString()
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        //TODO Revisar el bundle
                        val bundle = Bundle()
                        bundle.putString("nombre", binding.editLoginCorreo.text.toString())
                        bundle.putString("uid", auth.uid)

                        Snackbar.make(
                            binding.botonLogin, "Sesión iniciada correctamente",
                            Snackbar.LENGTH_SHORT
                        ).show()

                        findNavController().navigate(
                            // nombre del metodo del nav,
                            R.id.action_Login_to_SecondActivity,
                            bundle
                        )
                    } else {
                        Snackbar.make(
                            binding.botonLogin, "Usuario o contraseña introducidos no correctos",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }else{
                Snackbar.make(
                    binding.editLoginCorreo,
                    "Rellene todos los campos necesarios",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        //Te dirije directamente a crear una cuenta
        binding.botonRegistrar.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }
    }
}
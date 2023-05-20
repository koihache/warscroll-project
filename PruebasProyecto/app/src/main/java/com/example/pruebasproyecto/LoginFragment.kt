package com.example.pruebasproyecto

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pruebasproyecto.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private lateinit var auth: FirebaseAuth

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

        //Si no están vacios los campos rellenados anteriormente por el register
        //los pone en los edit para facilitar el usuario el inicio de la sesión


        if (correo.isNullOrEmpty()||password.isNullOrEmpty()){
            binding.editLoginCorreo.setText(correo)
            binding.editLoginPassword.setText(password)
        }

        binding.botonLogin.setOnClickListener{

            auth.signInWithEmailAndPassword(
                binding.editLoginCorreo.text.toString(),
                binding.editLoginPassword.text.toString()
            ).addOnCompleteListener {

                if (it.isSuccessful) {
                    val bundle = Bundle()
                    bundle.putString("nombre", binding.editLoginCorreo.text.toString())
                    bundle.putString("uid", auth.uid)

                    Snackbar.make(
                        binding.botonLogin, "Sesión iniciada correctamente",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(
                        //TODO nombre del metodo del nav,
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

        }

        binding.botonRegistrar.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }

    }
}
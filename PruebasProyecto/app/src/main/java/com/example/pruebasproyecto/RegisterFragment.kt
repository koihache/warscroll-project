package com.example.pruebasproyecto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pruebasproyecto.databinding.FragmentRegisterBinding
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

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private lateinit var auth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO revisar funcionalidad correcta
        //Creo que no hace falta traer esto aqui
        database =
            Firebase.database("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth

        binding.botonCrearcuenta.setOnClickListener {


            if (!binding.editRegisterCorreo.text.isNullOrBlank() && !binding.editRegisterUsuario.text.isNullOrBlank() && !binding.editRegisterPassword.text.isNullOrBlank() && !binding.editRegisterRepetir.text.isNullOrBlank()) {
                if (binding.editRegisterPassword.text.toString().length > 5) {
                    if (binding.editRegisterPassword.text.toString() == binding.editRegisterRepetir.text.toString()) {

                        if (auth.createUserWithEmailAndPassword(binding.editRegisterCorreo.text.toString(), binding.editRegisterPassword.text.toString())
                                .isSuccessful) {

                            //TODO Revisar si funciona en portatil (con la sentencia del if vale o no)
                            /*auth.createUserWithEmailAndPassword(
                                binding.editRegisterCorreo.text.toString(),
                                binding.editRegisterPassword.text.toString()
                            )*/
                            Snackbar.make(
                                binding.editRegisterCorreo,
                                "El usuario se ha creado correctamente",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            val bundle = Bundle()
                            bundle.putString("correo", binding.editRegisterCorreo.text.toString())
                            bundle.putString("password", binding.editRegisterPassword.text.toString())
                            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)

                        } else {
                            Snackbar.make(
                                binding.editRegisterCorreo,
                                "El usuario no se ha creado correctamente",
                                Snackbar.LENGTH_SHORT
                            ).show()
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

            /*val ref = database.getReference("usuarios")

            var usuario: Usuario;

            var correoUsuario: String = binding.editRegisterCorreo.text.toString()

            database.getReference("usuarios").orderByChild("correo").equalTo(correoUsuario).addListenerForSingleValueEvent(object:ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        for (i in snapshot.children){
                            for (j in i.children){
                                Log.v("probando", j.value.toString())
                            }
                        }
                    }
                    else {
                        Log.v("probando", "sin resultados")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })*/


            //Comprobar porque se genera el error con esta sentencia
            //(Posiblemente sea por el onCreateSucces())
            /*auth.createUserWithEmailAndPassword(
                binding.editRegisterCorreo.text.toString(),
                binding.editRegisterPassword.text.toString()
            ).addOnCompleteListener {

                if (it.isSuccessful) {
                    val bundle = Bundle()
                    bundle.putString("correo", binding.editRegisterCorreo.text.toString())
                    bundle.putString("password", binding.editRegisterPassword.text.toString())

                    Snackbar.make(
                        binding.editRegisterCorreo, "Usuario creado correctamente",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    findNavController().navigate( R.id.action_SecondFragment_to_FirstFragment,
                        bundle
                    )
                } else {
                    Snackbar.make(
                        binding.editRegisterCorreo, "El correo ya tiene una cuenta asignada",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
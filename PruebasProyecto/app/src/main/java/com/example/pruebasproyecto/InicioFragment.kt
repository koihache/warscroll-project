package com.example.pruebasproyecto

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputContentInfo
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pruebasproyecto.databinding.FragmentInicioBinding
import com.example.pruebasproyecto.model.Usuario
import com.google.android.material.snackbar.Snackbar
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
    private var nombre: String ? = null
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private lateinit var listener: OnSaberMasListener


    /*companion object{
        fun newInstance(usuario: Usuario, nombre: String): InicioFragment {
            val args = Bundle()
            val fragment = InicioFragment()
            args.putSerializable("usuario", usuario)
            args.putString("nombre", nombre)
            fragment.arguments = args
            return fragment
        }
    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    //@RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnSaberMasListener

        //TODO Comprobar
        dataBase =
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
                        binding.textInicioNombreUsuario.text = usuario!!.usuario;
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO Revision de lo bdd
        //binding.textInicioNombreUsuario.text = usuario!!.usuario

        binding.botonInicioSabermasWarcry.setOnClickListener {
            listener.onSaberMasSelected(1)
        }

        binding.botonInicioSabermasKillteam.setOnClickListener {
            listener.onSaberMasSelected(2)
        }
    }

    interface OnSaberMasListener{
        fun onSaberMasSelected(opcion: Int)
    }
}
package com.example.pruebasproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputContentInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.pruebasproyecto.databinding.ActivitySecondBinding
import com.example.pruebasproyecto.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class SecondActivity : AppCompatActivity(), MenuFragment.OnCambioListener {

    private lateinit var binding: ActivitySecondBinding
    //private lateinit var usuario: Usuario

    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    //private lateinit var listaUsuarios: ArrayList<Usuario>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth

        //listaUsuarios = ArrayList()

        //TODO Hacer una lista de usuarios y coger la primera y unica posicion
        /*dataBase.getReference("usuarios").orderByChild("idUsuario").equalTo(auth.uid!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (i in snapshot.children) {
                            //TODO Revisar con BORJA
                            // listaUsuarios.add((i.getValue(Usuario::class.java) as Usuario))

                            val usuario = (i.getValue(Usuario::class.java) as Usuario)

//                          Log.v("usuario", i.toString())
//                          Log.v("usuario", usuario.usuario!!)

                            //InicioFragment.newInstance(usuario, "asdfghjk")
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Snackbar.make(binding.frameLayoutFragments, "Cancelled", Snackbar.LENGTH_SHORT)
                        .show()
                }
            })*/

    }
    override fun onCambioSelected(opcion: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        when (opcion) {
            1 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id, InicioFragment())
                fragmentTransaction.addToBackStack("f1_inicio")
            }
            2 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id,MinisFragment())
                fragmentTransaction.addToBackStack("f2_minis")
            }
            3 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id, InicioFragment())
                fragmentTransaction.addToBackStack("f3_fav")
            }
            4 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id, AjustesFragment())
                fragmentTransaction.addToBackStack("f4_ajustes")
            }
        }
        fragmentTransaction.commit();
    }
}

package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class DialogoBorrarDatos: DialogFragment() {

    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth

        var builder = AlertDialog.Builder(requireContext());

        builder.setTitle("Borrar datos")
            .setMessage("¿Estas seguro de que quieres borrar todos tus datos? \n Esto eliminará todos tus perfiles favoritos")
            .setPositiveButton("Si") { dialogInterface, posicion ->

                dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos").setValue(null)

            }.setNegativeButton("No"){ dialogInterface, posicion ->
                Toast.makeText(requireActivity().applicationContext, "Operación cancelada", Toast.LENGTH_SHORT).show()
            }
        return builder.create()
    }
}
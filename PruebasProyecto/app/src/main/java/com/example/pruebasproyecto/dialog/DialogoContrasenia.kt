package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.pruebasproyecto.R
import com.example.pruebasproyecto.model.Usuario
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class DialogoContrasenia : DialogFragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dataBase: FirebaseDatabase

    private lateinit var usuario: Usuario

    private lateinit var context:Context;

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(requireContext());

        context = requireContext()

        auth = Firebase.auth
        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        builder.setTitle("Cambiar de Contraseña")
            .setMessage("¿Desea cambiar la contraseña por una nueva?")
            .setPositiveButton("Aceptar") { dialogInterface, posicion ->

                dataBase.getReference("usuarios").orderByChild("idUsuario").equalTo(auth.uid!!)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                for (i in snapshot.children) {
                                    usuario = (i.getValue(Usuario::class.java) as Usuario)
                                }
                                auth.sendPasswordResetEmail(usuario.correo.toString())
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            context,
                                            "Correo enviado correctamente",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(
                                            context,
                                            "Error al enviar el correo",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
            }.setNegativeButton("Cancelar") { dialogInterface, posicion ->
                Toast.makeText(requireActivity().applicationContext, "Operación cancelada",Toast.LENGTH_SHORT).show()
            }
        return builder.create()
    }
}
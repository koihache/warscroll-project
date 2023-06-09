package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.DialogFragment
import com.example.pruebasproyecto.LoginFragment
import com.example.pruebasproyecto.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class DialogoCerrarSesion: DialogFragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Creamos el dialogo
        var builder = AlertDialog.Builder(requireContext());

        //Traemos la autenticacion
        auth = Firebase.auth

        builder.setTitle("Confirmación de salida")
            .setMessage("¿Seguro que quieres cerrar la sesión?")
            .setPositiveButton("Aceptar") { dialogInterface, posicion ->

                //Cerramos la sesion
                auth.signOut()

                //Devuelve a la pestaña de inicio de sesión
                startActivity(Intent(requireActivity().applicationContext, MainActivity::class.java))

            }.setNegativeButton("Cancelar"){ dialogInterface, posicion ->
                Toast.makeText(requireActivity().applicationContext, "Operación cancelada",Toast.LENGTH_SHORT).show()
            }
        return builder.create()
    }
}


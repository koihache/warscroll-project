package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DialogoTerminos: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        var builder = AlertDialog.Builder(requireContext());
        builder.setTitle("Terminos Y Condiciones")
            .setMessage("Saldra de la aplicacion,¿estás seguro de querer salir?")
            .setPositiveButton("Si") { dialogInterface, posicion ->

            }.setNegativeButton("No"){ dialogInterface, posicion ->

            }
        return builder.create()
    }
}
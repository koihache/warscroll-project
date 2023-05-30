package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class DialogoBorrarDatos: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(requireContext());
        builder.setTitle("Borrar datos")
            .setMessage("¿Estas seguro de borrar todos los datos?")
            .setPositiveButton("Si") { dialogInterface, posicion ->

            }.setNegativeButton("No"){ dialogInterface, posicion ->

            }
        return builder.create()
    }
}
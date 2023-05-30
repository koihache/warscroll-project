package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class DialogoBorrarDatos: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación de salida")
            .setMessage("¿Estas seguro de borrar todos os datos?")
            .setPositiveButton("Si") { dialogInterface, posicion ->
                Log.v(
                    "dialogos",
                    "Pulsado si "+posicion.toString()
                )
            }.setNegativeButton("No"){ dialogInterface, posicion ->
                Log.v(
                    "dialogos",
                    "Pulsado no "+posicion.toString()
                )
            }
        return builder.create()
    }
}
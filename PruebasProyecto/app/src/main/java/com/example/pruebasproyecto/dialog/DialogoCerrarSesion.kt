package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class DialogoCerrarSesion: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación de salida")
            .setMessage("¿Seguro que quieres cerrar la sesión?")
            .setPositiveButton("Aceptar") { dialogInterface, posicion ->
                Log.v(
                    "dialogos",
                    "Pulsado aceptar "+posicion.toString()
                )
            }.setNegativeButton("Cancelar"){ dialogInterface, posicion ->
                Log.v(
                    "dialogos",
                    "Pulsado cancelar "+posicion.toString()
                )
            }
        return builder.create()
    }
}
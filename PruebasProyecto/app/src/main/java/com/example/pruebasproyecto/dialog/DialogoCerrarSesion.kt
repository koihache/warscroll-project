package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogoCerrarSesion: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación de salida")
    }
}
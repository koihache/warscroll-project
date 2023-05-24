package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogoTerminos: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var  builder = AlertDialog.Builder(requireContext())

        return builder.create()
    }
}
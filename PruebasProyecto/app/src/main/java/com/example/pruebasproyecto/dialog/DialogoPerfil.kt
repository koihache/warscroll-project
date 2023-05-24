package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.pruebasproyecto.R

class DialogoPerfil: DialogFragment() {

    private lateinit var vista:View
    private lateinit var correo:EditText
    private lateinit var usuario:EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_perfil, null);

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var  builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        correo = vista.findViewById(R.id.dialogo_perfil_correo)
        usuario = vista.findViewById(R.id.dialogo_perfil_usuario)
    }

    override fun onResume() {
        super.onResume()
    }
}
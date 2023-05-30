package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.pruebasproyecto.R

class DialogoMinis: DialogFragment() {

    private lateinit var vista: View
    private lateinit var imagen: ImageView
    private lateinit var titulo: TextView
    private lateinit var descripcion: TextView

    private lateinit var listener: OnMinisListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_perfil, null);

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var  builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)

        return builder.create()
    }

    override fun onResume() {
        super.onResume()
        acciones()

    }

    private fun acciones() {
        dismiss()
    }

    private fun instancias() {
        imagen = vista.findViewById(R.id.dialogo_minis_imagen)
        titulo = vista.findViewById(R.id.dialogo_minis_imagen)
        descripcion = vista.findViewById(R.id.dialogo_minis_imagen)
    }

    override fun onStart() {
        super.onStart()
        instancias()
    }

    interface OnMinisListener{
        fun onMinisDataSelected(imagen:String, titulo: String,descripcion: String)
    }
}

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
import com.example.pruebasproyecto.model.Perfil

class DialogoMinis: DialogFragment() {

    private lateinit var vista: View
    private lateinit var topImagen: ImageView;
    private lateinit var labelTitulo: TextView;
    private lateinit var labelDescripcion: TextView;

    private lateinit var perfilRecibido: Perfil

    companion object{
        fun newInstance(perfil: Perfil): DialogoMinis {
            val args = Bundle()
            args.putSerializable("perfil",perfil) as Perfil
            val fragment = DialogoMinis()
            fragment.arguments
            return fragment

        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_perfil, null);

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setView(vista)
        perfilRecibido = this.arguments?.get("perfil") as Perfil
        return builder.create()
    }


    override fun onStart() {
        super.onStart()
        topImagen = vista.findViewById(R.id.dialogo_minis_imagen);
        labelTitulo = vista.findViewById(R.id.dialogo_minis_titulo);
        labelDescripcion = vista.findViewById(R.id.dialogo_minis_descripcion);

        Glide.with(requireContext()).load(perfilRecibido.imagen).into(topImagen)
        labelTitulo.setText(perfilRecibido.nombrePerfil)
        labelDescripcion.setText(perfilRecibido.nMinis.toString())


    }

}

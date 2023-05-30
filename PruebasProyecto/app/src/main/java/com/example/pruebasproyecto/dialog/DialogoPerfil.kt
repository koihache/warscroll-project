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

class DialogoPerfil: DialogFragment() {

    private lateinit var vista: View
    private lateinit var imagenPerfil: ImageView;
    private lateinit var textNombre: TextView;
    private lateinit var textEspecie: TextView;
    private lateinit var textCantidad: TextView;
    private lateinit var textUniverso: TextView;
    private lateinit var perfilRecibido:Perfil

    companion object{
        fun newInstance(perfil: Perfil): DialogoPerfil {
            val args = Bundle()
            args.putSerializable("perfil",perfil)
            val fragment = DialogoPerfil()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_perfil, null);
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)
        perfilRecibido = arguments?.getSerializable("perfil") as Perfil
        return builder.create()
    }


    override fun onStart() {
        super.onStart()

        imagenPerfil = vista.findViewById(R.id.dialogo_perfil_imagen);
        textNombre = vista.findViewById(R.id.dialogo_perfil_nombre);
        textEspecie = vista.findViewById(R.id.dialogo_perfil_especie);
        textCantidad = vista.findViewById(R.id.dialogo_perfil_cantidad)
        textUniverso = vista.findViewById(R.id.dialogo_perfil_universo)

        Glide.with(requireContext()).load(perfilRecibido.imagen).into(imagenPerfil)
        textNombre.setText(perfilRecibido.nombrePerfil)
        textEspecie.setText(perfilRecibido.especie)
        textCantidad.setText(perfilRecibido.nMinis.toString())
        textUniverso.setText(perfilRecibido.universo)

    }

}

package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.pruebasproyecto.R
import com.google.firebase.database.FirebaseDatabase

class DialogoMinis: DialogFragment() {

    private lateinit var vista: View
    private lateinit var topImagen: ImageView;
    //private lateinit var labelTitulo: TextView;
    //private lateinit var labelDescripcion: TextView;

    private lateinit var dataBase: FirebaseDatabase

    private lateinit var imagen: String

    companion object{
        fun newInstance(imagen: String): DialogoMinis {
            val args = Bundle()
            args.putString("imagen",imagen)
            val fragment = DialogoMinis()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_minis, null);
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)
        imagen = arguments?.getString("imagen").toString()
        return builder.create()
    }


    override fun onStart() {
        super.onStart()

        topImagen = vista.findViewById(R.id.dialogo_minis_imagen);
        //labelTitulo = vista.findViewById(R.id.dialogo_minis_titulo);
        //labelDescripcion = vista.findViewById(R.id.dialogo_minis_descripcion);

        Glide.with(requireContext()).load(imagen).into(topImagen)
        //labelTitulo.setText(perfilRecibido.nombrePerfil)
        //labelDescripcion.setText(perfilRecibido.nMinis.toString())

    }

}

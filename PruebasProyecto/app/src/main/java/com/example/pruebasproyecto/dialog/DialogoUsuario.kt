package com.example.pruebasproyecto.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.pruebasproyecto.R

class DialogoUsuario : DialogFragment() {

    lateinit var vista: View;
    private lateinit var labelCorreo: TextView;
    private lateinit var labelUsuario: TextView;

    private lateinit var correo: String
    private lateinit var usuario: String

    //Definimos metodo para traer el correo y usuario por args
    companion object{
        fun newInstance(correo : String, usuario: String): DialogoUsuario {
            val args = Bundle()
            args.putString("correo", correo)
            args.putString("usuario", usuario)
            val fragment = DialogoUsuario()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Inflamos la vista
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_usuario,null);
    }

    //Creamos la vista y traemos correo y usuario
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        builder.setView(vista)
        correo = this.arguments?.get("correo").toString()
        usuario = this.arguments?.get("usuario").toString()

        //Definimos valores del xml a los items
        labelCorreo = vista.findViewById(R.id.dialogo_perfil_correo);
        labelUsuario = vista.findViewById(R.id.dialogo_perfil_usuario);
        return builder.create()

    }

    override fun onStart() {
        super.onStart()
        //Igualamos los items a su valor correspondiente
        labelCorreo.setText(correo)
        labelUsuario.setText(usuario)
    }
}
package com.example.pruebasproyecto.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.pruebasproyecto.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DialogoContrasenia :DialogFragment(){


    private lateinit var correo: String
    private lateinit var passw: String


    companion object{
        fun newInstance(correo:String,pass : String): DialogoContrasenia {
            val args = Bundle()
            args.putString("correo", correo)
            args.putString("pass", pass)
            val fragment = DialogoContrasenia()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(requireContext());
        correo = this.arguments?.get("correo").toString()
        passw = this.arguments?.get("pass").toString()
        builder.setTitle("Cambiar de Contraseña")
            .setMessage("¿Desea cambiar la contraseña por una bnueva?")
            .setPositiveButton("Aceptar") { dialogInterface, posicion ->
                FirebaseAuth.getInstance().sendPasswordResetEmail(correo).addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        Snackbar.make(requireView(),"Email enviado correctamente",Snackbar.LENGTH_SHORT).show()
                    } else{
                        Snackbar.make(requireView(),"Email no enviado",Snackbar.LENGTH_SHORT).show()
                    }
                }


            }.setNegativeButton("Cancelar"){ dialogInterface, posicion ->
                Log.d(TAG, "Error auth failed")
            }
        return builder.create()
    }
}
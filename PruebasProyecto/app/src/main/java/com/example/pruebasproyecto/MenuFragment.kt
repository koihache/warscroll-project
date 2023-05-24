package com.example.pruebasproyecto

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ServiceCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pruebasproyecto.databinding.FragmentInicioBinding
import com.example.pruebasproyecto.databinding.FragmentMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuFragment: Fragment(){

    private var _binding: FragmentMenuBinding? = null
    private lateinit var actividad: SecondActivity;
    private lateinit var listener: OnCambioListener

    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as OnCambioListener
        } catch (e: java.lang.ClassCastException){
            Log.v("pruebas", "error de conversion en el tipo")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        actividad = activity as SecondActivity;
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Pruebas

        /*binding.botonAjustes.setOnClickListener {

            var fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction.replace(actividad. .id, FragmentInicial())
        }*/
        binding.botonInicio.setOnClickListener {
            listener.onCambioSelected(1)
        }
        binding.botonMinis.setOnClickListener {
            listener.onCambioSelected(2)
        }
        binding.botonFavoritos.setOnClickListener {
            listener.onCambioSelected(3)
        }
        binding.botonAjustes.setOnClickListener {
            listener.onCambioSelected(4)
        }
    }

    interface OnCambioListener{
        fun onCambioSelected(opcion:Int)
    }
}
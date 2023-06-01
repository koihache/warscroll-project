package com.example.pruebasproyecto

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.FragmentMenuBinding

class MenuFragment: Fragment(){

    private var _binding: FragmentMenuBinding? = null
    private lateinit var actividad: SecondActivity;
    private lateinit var listener: OnCambioListener
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        actividad = activity as SecondActivity;
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnCambioListener

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Manejamos la pulsacion del menú y hacemos control de visualización del xml

        binding.botonInicio.setOnClickListener {
            listener.onCambioSelected(1)
            binding.estadoBotonInicio.isVisible = true
            binding.estadoBotonMinis.isVisible = false
            binding.estadoBotonFavoritos.isVisible = false
            binding.estadoBotonAjustes.isVisible = false

        }
        binding.botonMinis.setOnClickListener {
            listener.onCambioSelected(2)
            binding.estadoBotonInicio.isVisible = false
            binding.estadoBotonMinis.isVisible = true
            binding.estadoBotonFavoritos.isVisible = false
            binding.estadoBotonAjustes.isVisible = false
        }
        binding.botonFavoritos.setOnClickListener {
            listener.onCambioSelected(3)
            binding.estadoBotonInicio.isVisible = false
            binding.estadoBotonMinis.isVisible = false
            binding.estadoBotonFavoritos.isVisible = true
            binding.estadoBotonAjustes.isVisible = false
        }
        binding.botonAjustes.setOnClickListener {
            listener.onCambioSelected(4)
            binding.estadoBotonInicio.isVisible = false
            binding.estadoBotonMinis.isVisible = false
            binding.estadoBotonFavoritos.isVisible = false
            binding.estadoBotonAjustes.isVisible = true
        }
    }

    //Interfaz de callback para detectar el cambio de la pulsacion en el menu
    interface OnCambioListener{
        fun onCambioSelected(opcion:Int)
    }
}
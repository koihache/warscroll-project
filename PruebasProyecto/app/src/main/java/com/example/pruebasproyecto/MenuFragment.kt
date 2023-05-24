package com.example.pruebasproyecto

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pruebasproyecto.databinding.FragmentInicioBinding
import com.example.pruebasproyecto.databinding.FragmentMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuFragment: Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Pruebas
        binding.botonAjustes.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_inicioFragment_to_ajustesFragment))
            //findNavController().navigate(R.id.action_inicioFragment_to_ajustesFragment)

    }
}
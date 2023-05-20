package com.example.pruebasproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        binding.button.setOnClickListener {

            binding.button.setText("Hola")

            Snackbar.make(
                binding.button,
                "Hola",
                Snackbar.LENGTH_SHORT
            ).show()

        }
    }
}
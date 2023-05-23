package com.example.pruebasproyecto

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.FragmentAjustesBinding
import com.example.pruebasproyecto.dialog.DialogoPerfil

class AjustesFragment: Fragment() {

    private var _binding: FragmentAjustesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        acciones();
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun acciones() {
        binding.botonAjustesVerperfil.setOnClickListener {
            DialogoPerfil()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
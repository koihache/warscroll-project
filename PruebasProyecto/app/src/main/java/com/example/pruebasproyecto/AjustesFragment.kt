package com.example.pruebasproyecto

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.pruebasproyecto.databinding.FragmentAjustesBinding
import com.example.pruebasproyecto.databinding.FragmentInicioBinding
import com.example.pruebasproyecto.dialog.DialogoPerfil
import com.google.android.material.snackbar.Snackbar

class AjustesFragment: Fragment(),View.OnClickListener{

    private var _binding: FragmentAjustesBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        acciones()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    private fun acciones() {
        binding.botonAjustesPerfil.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        binding.botonAjustesPerfil.setOnClickListener {
            Snackbar.make(
                binding.botonAjustesPerfil,
                "sedrhsdrhgyse",
                Snackbar.LENGTH_SHORT
            ).show()
            DialogoPerfil().show(requireFragmentManager(),"")

        }
    }
}
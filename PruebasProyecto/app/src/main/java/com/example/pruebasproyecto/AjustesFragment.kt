package com.example.pruebasproyecto

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.FragmentAjustesBinding
import com.example.pruebasproyecto.dialog.DialogoPerfil

class AjustesFragment: Fragment(), View.OnClickListener {

    private var _binding: FragmentAjustesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root

        //acciones();
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //prueba

    }
    //TODO dialog activity a fargment callback
    /*private fun acciones() {
        binding.botonAjustesPerfil.setOnClickListener {
            DialogoPerfil().show()
        }
    }*/

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}
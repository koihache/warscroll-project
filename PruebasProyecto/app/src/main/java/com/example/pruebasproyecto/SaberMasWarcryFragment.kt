package com.example.pruebasproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.WarcryFragmentBinding

class SaberMasWarcryFragment: Fragment(){

    private var _binding: WarcryFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WarcryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
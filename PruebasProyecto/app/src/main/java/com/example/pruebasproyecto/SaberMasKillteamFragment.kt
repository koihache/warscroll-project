package com.example.pruebasproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.KillteamFragmentBinding

class SaberMasKillteamFragment: Fragment() {

    private var _binding: KillteamFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflamos la vista
        _binding = KillteamFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
package com.example.pruebasproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pruebasproyecto.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), MenuFragment.OnCambioListener {

    private lateinit var binding: ActivitySecondBinding
    private var fragmentTransaction = supportFragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



    override fun onCambioSelected(opcion: Int) {


        when (opcion) {
            1 -> fragmentTransaction.replace(binding.frameLayoutFragments.id, InicioFragment())
            2 -> fragmentTransaction.replace(binding.frameLayoutFragments.id, InicioFragment())
            3 -> fragmentTransaction.replace(binding.frameLayoutFragments.id, InicioFragment())
            4 -> fragmentTransaction.replace(binding.frameLayoutFragments.id, AjustesFragment())
        }
        fragmentTransaction.commit();

    }
}
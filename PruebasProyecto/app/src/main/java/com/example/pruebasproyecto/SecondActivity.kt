package com.example.pruebasproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.pruebasproyecto.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), MenuFragment.OnCambioListener {

    private lateinit var binding: ActivitySecondBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



    override fun onCambioSelected(opcion: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        when (opcion) {
            1 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id, InicioFragment())
                fragmentTransaction.addToBackStack("f1_inicio")
            }
            2 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id,MinisFragment())
                fragmentTransaction.addToBackStack("f2_minis")
            }
            3 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id, InicioFragment())
                fragmentTransaction.addToBackStack("f3_fav")
            }
            4 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id, AjustesFragment())
                fragmentTransaction.addToBackStack("f4_ajustes")
            }
        }
        fragmentTransaction.commit();

    }
}

private fun FragmentTransaction.replace(id: Int, minisFragment: MinisFragment) {
    TODO("Not yet implemented")
}

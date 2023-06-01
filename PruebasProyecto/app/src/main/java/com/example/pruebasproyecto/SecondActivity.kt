package com.example.pruebasproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputContentInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.pruebasproyecto.databinding.ActivitySecondBinding
import com.example.pruebasproyecto.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class SecondActivity : AppCompatActivity(), MenuFragment.OnCambioListener, InicioFragment.OnSaberMasListener {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflamos la vista
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //Registramos la pulsacion del menu para cambiar el fragment
    override fun onCambioSelected(opcion: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
            android.R.anim.fade_out);

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
                fragmentTransaction.replace(binding.frameLayoutFragments.id, FavoritosFragment())
                fragmentTransaction.addToBackStack("f3_fav")
            }
            4 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id, AjustesFragment())
                fragmentTransaction.addToBackStack("f4_ajustes")
            }
        }
        fragmentTransaction.commit();
    }

    //Registramos la pulsacion del inicio para cambiar el fragment
    override fun onSaberMasSelected(opcion: Int) {

        var fragmentTransaction = supportFragmentManager.beginTransaction()

        when (opcion) {
            1 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id,SaberMasWarcryFragment())
                fragmentTransaction.addToBackStack("f1_sabermas_warcry")
            }
            2 -> {
                fragmentTransaction.replace(binding.frameLayoutFragments.id,SaberMasKillteamFragment())
                fragmentTransaction.addToBackStack("f2_sabermas_killteam")
            }
        }
        fragmentTransaction.commit();

    }

    //Evitamos que el usuario pulse hacia atras
    override fun onBackPressed() {
    }

}

package com.example.pruebasproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebasproyecto.adapter.AdapterMinis
import com.example.pruebasproyecto.databinding.FragmentMinisBinding
import com.example.pruebasproyecto.model.Perfil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.collections.ArrayList

class MinisFragment : Fragment() {

    private var _binding: FragmentMinisBinding? = null
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var adapterMinis: AdapterMinis


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMinisBinding.inflate(inflater, container, false)

        adapterMinis = AdapterMinis(ArrayList<Perfil>(),requireContext())
        binding.recyclerMinis.adapter = adapterMinis
        binding.recyclerMinis.layoutManager = LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.VERTICAL,false)

        return binding.root

    }

    override fun onResume() {
        super.onResume()

        //TODO Hacer pruebas para ver si ordena de verdad
        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        dataBase.getReference("perfiles").orderByChild("name").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        adapterMinis.addMini(i.getValue(Perfil::class.java) as Perfil)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}

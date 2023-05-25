package com.example.pruebasproyecto

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebasproyecto.adapter.AdapterMinis
import com.example.pruebasproyecto.databinding.FragmentMinisBinding
import com.example.pruebasproyecto.model.Minis
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class MinisFragment : AppCompatActivity() {

    private lateinit var binding: FragmentMinisBinding
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var adapterMinis: AdapterMinis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMinisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterMinis = AdapterMinis(ArrayList<Minis>())
        binding.recyclerMinis.adapter = adapterMinis
        binding.recyclerMinis.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
    }

    override fun onResume() {
        super.onResume()

        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")
        dataBase.getReference("perfiles").orderByChild("name").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        adapterMinis.addMini(i.getValue(Minis::class.java) as Minis)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}

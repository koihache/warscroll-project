package com.example.pruebasproyecto

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebasproyecto.adapter.AdapterMinis
import com.example.pruebasproyecto.databinding.FragmentMinisBinding
import com.example.pruebasproyecto.model.Perfil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MinisFragment : Fragment() {

    private var _binding: FragmentMinisBinding? = null
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var adapterMinis: AdapterMinis



    var listaMinis = arrayListOf<Perfil>()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMinisBinding.inflate(inflater, container, false)

        adapterMinis = AdapterMinis(ArrayList<Perfil>(),requireContext(), requireActivity().supportFragmentManager)

        binding.recyclerMinis.adapter = adapterMinis

        binding.recyclerMinis.layoutManager = LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.VERTICAL,false)

        filtrarLista()

        binding.recyclerFiltrar.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                filtrar(s.toString())
            }
        })
        return binding.root

    }

    private fun filtrarLista() {
        binding.recyclerMinis.layoutManager = LinearLayoutManager(requireContext())
        adapterMinis = AdapterMinis(listaMinis, requireContext(), requireActivity().supportFragmentManager)
        binding.recyclerMinis.adapter = adapterMinis
    }
    private fun filtrar(text:String){
        var listaFiltrada = arrayListOf<Perfil>()

        listaMinis.forEach {
            if (it.nombrePerfil?.toLowerCase()?.contains(text.toLowerCase())!!){
                listaFiltrada.add(it)
            }
        }
        adapterMinis.filtrar(listaFiltrada)
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

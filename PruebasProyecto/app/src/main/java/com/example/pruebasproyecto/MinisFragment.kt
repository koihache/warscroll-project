package com.example.pruebasproyecto

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebasproyecto.adapter.AdapterMinis
import com.example.pruebasproyecto.databinding.FragmentMinisBinding
import com.example.pruebasproyecto.model.Perfil
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MinisFragment : Fragment() {

    private var _binding: FragmentMinisBinding? = null
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var adapterMinis: AdapterMinis
    private var listaMinis = arrayListOf<Perfil>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMinisBinding.inflate(inflater, container, false)
        //Creamos y realizamos la igualación del adaptador para el filtro y el mostrado normal de la informacion
        adapterMinis = AdapterMinis(ArrayList<Perfil>(),requireContext(), requireActivity().supportFragmentManager)
        binding.recyclerMinis.adapter = adapterMinis
        binding.recyclerMinis.layoutManager = LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.VERTICAL,false)

        filtrarLista()

        //Filtro en caliente
        binding.minisFiltrar.addTextChangedListener(object : TextWatcher{
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Traemos bbdd
        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")
    }

    override fun onResume() {
        super.onResume()
        //Traemos los perfiles para mandarlos al adapter
        dataBase.getReference("perfiles").orderByChild("nombrePerfil").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        adapterMinis.addMini(i.getValue(Perfil::class.java) as Perfil)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Snackbar.make(binding.recyclerMinis,"Ha ocurrido un error en la base de datos",
                    Snackbar.LENGTH_LONG).show()
            }
        })
    }

    //Filtra la lista y le pasa al adaptador la lista filtrada
    private fun filtrarLista() {
        binding.recyclerMinis.layoutManager = LinearLayoutManager(requireContext())
        adapterMinis = AdapterMinis(listaMinis, requireContext(), requireActivity().supportFragmentManager)
        binding.recyclerMinis.adapter = adapterMinis
    }

    //Filtra mediante el texto pasado
    private fun filtrar(text:String){
        var listaFiltrada = arrayListOf<Perfil>()

        listaMinis.forEach {
            if (it.nombrePerfil?.toLowerCase()?.contains(text.toLowerCase())!!){
                listaFiltrada.add(it)
            }
        }
        adapterMinis.filtrar(listaFiltrada)
    }
}

package com.example.pruebasproyecto

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebasproyecto.adapter.AdapterFavoritos
import com.example.pruebasproyecto.databinding.FragmentFavoritosBinding
import com.example.pruebasproyecto.model.Perfil
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class FavoritosFragment : Fragment() {


    private var _binding: FragmentFavoritosBinding? = null
    private lateinit var dataBase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var adapterFavoritos: AdapterFavoritos
    private var listaMinisFavoritas = arrayListOf<Perfil>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        //Creamos y realizamos la igualación del adaptador para el filtro y el mostrado normal de la informacion
        adapterFavoritos = AdapterFavoritos(ArrayList<Perfil>(),requireContext())
        binding.recyclerFavoritos.adapter = adapterFavoritos
        binding.recyclerFavoritos.layoutManager = LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.VERTICAL,false)

        filtrarLista()

        //Filtro en caliente
        binding.favoritosFiltrar.addTextChangedListener(object : TextWatcher{
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
        //Traemos bbdd y autenticacion
        dataBase =
            FirebaseDatabase.getInstance("https://fir-warscroll-default-rtdb.firebaseio.com/")

        auth = Firebase.auth
    }


    override fun onResume() {
        super.onResume()

        //Traemos los perfiles favoritos de cada usuario para mandarlos al adapter
        dataBase.getReference("usuarios").child(auth.uid!!).child("favoritos").orderByChild("name").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        adapterFavoritos.addMini(i.getValue(Perfil::class.java) as Perfil)
                    }
                }else{
                    Snackbar.make(binding.favoritosFiltrar,"No tienes favoritos agregados",Snackbar.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Snackbar.make(binding.favoritosFiltrar,"Ha ocurrido un error en la base de datos",Snackbar.LENGTH_LONG).show()
            }

        })
    }

    //Filtra la lista y le pasa al adaptador la lista filtrada
    private fun filtrarLista() {
        binding.recyclerFavoritos.layoutManager = LinearLayoutManager(requireContext())
        adapterFavoritos =
            AdapterFavoritos(listaMinisFavoritas, requireContext())
        binding.recyclerFavoritos.adapter = adapterFavoritos
    }

    //Filtra mediante el texto pasado
    private fun filtrar(text: String) {
        var listaFiltrada = arrayListOf<Perfil>()

        listaMinisFavoritas.forEach {
            if (it.nombrePerfil?.toLowerCase()?.contains(text.toLowerCase())!!) {
                listaFiltrada.add(it)
            }
        }
        adapterFavoritos.filtrar(listaFiltrada)
    }
}
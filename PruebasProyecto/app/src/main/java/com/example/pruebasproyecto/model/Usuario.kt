package com.example.pruebasproyecto.model

data class Usuario (var idUsuario: String? = null, var correo: String? = null, var usuario: String? = null, var favoritos: HashMap<String,Object>? = null) : java.io.Serializable {
}

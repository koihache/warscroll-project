package com.example.pruebasproyecto.model

import java.io.Serializable

data class Perfil(var especie:String?=null,
                  var idMini:Int?=null,
                  var imagen:String?=null,
                  var nMinis: Int?=null,
                  var nombrePerfil:String?=null,
                  var universo:String?=null) : Serializable {

}
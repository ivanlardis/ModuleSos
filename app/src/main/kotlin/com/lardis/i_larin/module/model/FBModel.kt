package com.lardis.i_larin.module.model

/**
 * Created by black-sony on 06.05.17.
 */
  class FBModel
{

  var name: String = ""
  var family: String = ""
  var photoUrl: String = ""
  var idUser: String = ""
  var createTime: Long = System.currentTimeMillis()

  var intident: String = ""
  var car: String = ""
  var phoneNumber: String = ""
  var mapInfo: String = ""
  var latitude: Double? =null
  var longitude: Double?=null
  constructor()
  {

  }

  constructor(name: String, idUser: String) {
    this.name = name
    this.idUser = idUser
  }

}

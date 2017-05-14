package com.lardis.i_larin.module.ui.activity.intident

/**
 * Created by black-sony on 14.05.17.
 */
class ModelComment {
    var name:String=""
    var text:String=""
    var time:Long?=null
    var photoUrl:String=""

    constructor()
    constructor(name: String, text: String, time: Long?, photoUrl: String) {
        this.name = name
        this.text = text
        this.time = time
        this.photoUrl = photoUrl
    }

}
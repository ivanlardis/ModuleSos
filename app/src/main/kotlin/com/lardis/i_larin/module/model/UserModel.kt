package com.lardis.i_larin.module.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by black-sony on 07.05.17.
 */



class UserModel {

    @SerializedName("uid")
    @Expose
    var uid: Int? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("photo_50")
    @Expose
    var photo50: String? = null

}
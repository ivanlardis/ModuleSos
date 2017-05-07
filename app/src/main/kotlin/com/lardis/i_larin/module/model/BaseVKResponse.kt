package com.lardis.i_larin.module.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Lera on 04.12.2016.
 */

class BaseVKResponse<T> {
    @SerializedName("response")
    @Expose
    var response: T? = null

}


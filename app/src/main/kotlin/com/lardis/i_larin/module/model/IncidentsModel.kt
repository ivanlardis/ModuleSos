package com.lardis.i_larin.module.model

import java.util.*

/**
 * Created by black-sony on 07.05.17.
 */
data class IncidentsModel (val user: HashMap<String,UserModel>,val incidents: MutableList<FBModel> )
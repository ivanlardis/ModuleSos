package com.lardis.i_larin.module.ui.fragment.incidents


/**
 * Created by black-sony on 13.05.17.
 */


data class IncidentAddModel(
                            val intident: String,
                            val car: String,
                            val phoneNumber: String,
                            val mapInfo: String,
                            val latitude: Double?,
                            val longitude: Double?)
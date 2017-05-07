package com.example.i_larin.pixabayreader.repository

import com.lardis.i_larin.module.model.IncidentsModel
import rx.Observable


interface IIncidentsRepository {

    fun loadData()
    fun add()
    fun rm()
    fun subcRep(): Observable< IncidentsModel>
}

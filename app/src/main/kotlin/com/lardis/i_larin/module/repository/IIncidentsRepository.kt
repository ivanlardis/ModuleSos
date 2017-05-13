package com.example.i_larin.pixabayreader.repository

import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.ui.fragment.incidents.IncidentAddModel
import rx.Observable


interface IIncidentsRepository {

    fun loadData()
    fun add(incidentAddModel: IncidentAddModel)
    fun rm()
    fun subcRep(): Observable<List<FBModel>>
}

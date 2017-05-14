package com.lardis.i_larin.module.presentation.presenter.setting


import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.i_larin.pixabayreader.di.DI
import com.example.i_larin.pixabayreader.repository.IIncidentsRepository
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.presentation.view.setting.IncidentsView
import com.lardis.i_larin.module.ui.fragment.incidents.IncidentAddModel
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class IncidentsPresenter : MvpPresenter<IncidentsView>() {


    @Inject
    lateinit var dialogRepository: IIncidentsRepository


    init {
        DI.componentManager().businessLogicComponent().inject(this)
        dialogRepository.subcRep().observeOn(AndroidSchedulers.mainThread())

                .subscribe({ viewState.show(it) })

        dialogRepository.subcRepSelected().observeOn(AndroidSchedulers.mainThread())

                .subscribe({ viewState.showSelected(it) })

    }


    fun loadData() {
        dialogRepository.loadData()

    }

    fun rm() {
        dialogRepository.rm()

    }

    fun selected(fBModel: FBModel) {
        dialogRepository.selected(fBModel)

    }

    fun completed(fBModel: FBModel) {
        dialogRepository.completed(fBModel)

    }

    fun add(incidentAddModel: IncidentAddModel) {
        dialogRepository.add(incidentAddModel)

    }

}
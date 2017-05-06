package com.lardis.i_larin.module.presentation.presenter.setting


import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.i_larin.pixabayreader.di.DI
import com.example.i_larin.pixabayreader.repository.IIncidentsRepository
import com.lardis.i_larin.module.presentation.view.setting.IncidentsView
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class IncidentsPresenter : MvpPresenter<IncidentsView>() {


    @Inject
    lateinit var dialogRepository: IIncidentsRepository



    init {
        DI.componentManager().businessLogicComponent().inject(this)
        dialogRepository.subcRep().observeOn(AndroidSchedulers.mainThread())

                .subscribe({viewState.show(it)})

    }


    fun loadData() {
        dialogRepository.loadData()

    }

    fun rm() {
        dialogRepository.rm()

    }
    fun add() {
        dialogRepository.add()

    }

}
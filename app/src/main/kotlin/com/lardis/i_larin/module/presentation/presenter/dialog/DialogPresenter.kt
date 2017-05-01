package com.lardis.i_larin.module.presentation.presenter.setting


import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.i_larin.pixabayreader.di.DI
import com.example.i_larin.pixabayreader.repository.IDialogRepository
import com.lardis.i_larin.module.presentation.view.setting.SettingView
import javax.inject.Inject

@InjectViewState
class DialogPresenter : MvpPresenter<SettingView>() {


    @Inject
    lateinit var dialogRepository: IDialogRepository

    init {
        DI.componentManager().businessLogicComponent().inject(this)
    }

    fun loadData() = dialogRepository.loadData()

}
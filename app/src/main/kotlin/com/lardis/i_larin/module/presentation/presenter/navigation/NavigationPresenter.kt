package com.lardis.i_larin.module.presentation.presenter.navigation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.lardis.i_larin.module.presentation.view.navigation.NavigationsView
import ru.terrakok.cicerone.Router

@InjectViewState
class NavigationPresenter(val router: Router) : MvpPresenter<NavigationsView>() {




    fun onBackCommandClick() {
        router.exit()
    }




    fun onNewRootCommandClick(nameScreen:String) {
        router.newRootScreen(nameScreen)
    }
}

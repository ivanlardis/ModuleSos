package com.lardis.i_larin.module.presentation.view.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


interface LoginView : MvpView {

    @StateStrategyType(value = SingleStateStrategy::class)
    fun showProgress(boolean: Boolean)

    @StateStrategyType(value = SkipStrategy::class)
    fun showWarning(    )

    @StateStrategyType(value = SkipStrategy::class)
    fun showOK()
}

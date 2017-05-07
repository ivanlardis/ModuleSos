package com.lardis.i_larin.module.presentation.view.setting

import com.arellomobile.mvp.MvpView
import com.lardis.i_larin.module.model.IncidentsModel
import rx.Observable

interface IncidentsView : MvpView
{

    fun show(data:  IncidentsModel)

}


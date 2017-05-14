package com.lardis.i_larin.module.presentation.view.setting

import com.arellomobile.mvp.MvpView
import com.lardis.i_larin.module.model.FBModel

interface IncidentsView : MvpView
{

    fun show(data:  List<FBModel>)
    fun showSelected(data:   FBModel)

}


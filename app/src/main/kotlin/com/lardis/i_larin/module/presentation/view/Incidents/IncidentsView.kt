package com.lardis.i_larin.module.presentation.view.setting

import com.arellomobile.mvp.MvpView
import com.lardis.i_larin.module.repository.TestModel

interface IncidentsView : MvpView
{

    fun show(data: MutableList<TestModel>)

}


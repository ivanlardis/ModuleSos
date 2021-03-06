package com.example.i_larin.pixabayreader.di.component

import com.example.i_larin.pixabayreader.di.model.BusinessLogicModule
import com.lardis.i_larin.module.presentation.presenter.login.LoginPresenter
import com.lardis.i_larin.module.presentation.presenter.setting.DialogPresenter
import com.lardis.i_larin.module.presentation.presenter.setting.IncidentsPresenter
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by black-sony on 29.01.17.
 */

@Singleton
@Subcomponent(modules = arrayOf(BusinessLogicModule::class))
interface BusinessLogicComponent {

    fun inject(entry: IncidentsPresenter)
    fun inject(entry: DialogPresenter)
    fun inject(entry: LoginPresenter)
}
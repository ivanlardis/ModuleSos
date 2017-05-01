package com.lardis.i_larin.module.presentation.presenter.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.i_larin.pixabayreader.di.DI
import com.example.i_larin.pixabayreader.repository.ILoginRepository
import com.lardis.i_larin.module.presentation.view.login.LoginView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    @Inject
    lateinit var dialogRepository: ILoginRepository

    init {
        DI.componentManager().businessLogicComponent().inject(this)

    }


    fun checkUser(login: String, password: String) {

        viewState.showProgress(true)
        dialogRepository.checkUser(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            viewState.showProgress(false)
                            if (it) {
                                viewState.showOK()
                            } else {
                                viewState.showWarning()
                            }
                        },
                        {}, {})


    }

}

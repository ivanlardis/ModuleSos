package com.lardis.i_larin.module.presentation.presenter.setting


import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.i_larin.pixabayreader.di.DI
import com.example.i_larin.pixabayreader.repository.IDialogRepository
import com.lardis.i_larin.module.App
import com.lardis.i_larin.module.presentation.view.setting.DialogView
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.ui.activity.navigation.NavigationScreens
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class DialogPresenter : MvpPresenter<DialogView>() {


    @Inject
    lateinit var dialogRepository: IDialogRepository

    init {
        DI.componentManager().businessLogicComponent().inject(this)
        subscribeDialog()
        subscribeMessage()
    }



    fun selectedDialog(id: Long?) {
        id?.let {
            dialogRepository.selectedDialog(id)
            App.INSTANCE.getRouter().navigateTo(NavigationScreens.MESSAGE)
            Timber.e("$id")
        }

    }

    fun saveMessage(message:String) {
        dialogRepository.saveMessage(message)

    }


    fun saveDialog(dialogModel: DialogModel) {

        dialogRepository.saveDialog(dialogModel)
        Timber.e("saveDialog")
    }

    fun back( ) {
             App.INSTANCE.getRouter().backTo(null)


    }


    fun subscribeDialog() {


        dialogRepository.subcribeDialogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.showDialogs(it) },
                        {}, {})


    }

    fun subscribeMessage() {


        dialogRepository.subcribeMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.showMessages(it) },
                        {}, {})


    }


}
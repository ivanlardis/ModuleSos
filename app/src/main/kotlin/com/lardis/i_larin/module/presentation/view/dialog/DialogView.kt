package com.lardis.i_larin.module.presentation.view.setting

import com.arellomobile.mvp.MvpView
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel

interface DialogView : MvpView
{

    fun showDialogs(dialogModels:List<DialogModel> )
    fun showMessages(dialogModels:List<MessageModel> )


}

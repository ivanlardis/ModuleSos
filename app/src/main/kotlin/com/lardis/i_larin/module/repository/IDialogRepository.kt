package com.example.i_larin.pixabayreader.repository

import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel
import rx.Observable


interface IDialogRepository {

    fun subcribeDialogs(): Observable<List<DialogModel>>
    fun subcribeMessage(): Observable<List<MessageModel>>
    fun saveDialog(dialogModel: DialogModel)
    fun selectedDialog(id:Long)
    fun saveMessage(message:String)
}

package com.example.i_larin.pixabayreader.repository

import com.lardis.i_larin.module.prefs.Prefs
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel
import com.lardis.i_larin.module.storage.tables.DialogTable
import com.lardis.i_larin.module.storage.tables.MessageTable
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.queries.RawQuery
import rx.Observable
import rx.schedulers.Schedulers
import rx.subjects.BehaviorSubject
import timber.log.Timber


class DialogRepository : IDialogRepository {
    override fun saveMessage(message: String) {

        selectedDialod?.let {
            var messageModel = MessageModel(null, message, Prefs.USER_ID.long, it,
                    System.currentTimeMillis())
            saveMessageDB(messageModel)

            loadMessage(it)
            loadDialogs()

        }


    }

    private fun saveMessageDB(messageModel: MessageModel) {
        var message = messageModel.message
          storIOSQLite
                .put()
                .`object`(messageModel)
                .prepare()
                .executeAsBlocking()

        storIOSQLite
                .executeSQL()
                .withQuery(RawQuery.builder()
                        .query("update ${DialogTable.TABLE}" +
                                " set  ${DialogTable.COLUMN_LAST_MESSAGE} =  \" $message  \" " +
                                " where  ${DialogTable.COLUMN_ID } = $selectedDialod   ")
                        .build())
                .prepare()
                .executeAsBlocking();

    }


    override fun saveDialog(dialogModel: DialogModel) {
        saveDialogDB(dialogModel)

        loadDialogs()
    }

    private fun saveDialogDB(dialogModel: DialogModel) {
        storIOSQLite
                .put()
                .`object`(dialogModel)
                .prepare()
                .executeAsBlocking()
    }

    var selectedDialod: Long? = null
    override fun selectedDialog(id: Long) {
        selectedDialod = id;
        loadMessage(id)


    }

    private fun loadMessage(id: Long) {
        storIOSQLite
                .get()
                .listOfObjects(MessageModel::class.java!!)


                .withQuery(RawQuery.builder()
                        .query("select * from ${MessageTable.TABLE} " +
                                " where ${MessageTable.COLUMN_ID_DIALOG} == $id   " +
                                " order by ${MessageTable.COLUMN_TIME_CREATE} desc ")

                        .build())
                .prepare()
                .asRxObservable()
                .subscribeOn(Schedulers.io())
                .subscribe({ messageModelsSubject.onNext(it) },
                        { Timber.e(it.message) },
                        {}

                )
    }

    var storIOSQLite: StorIOSQLite
    private var dialogModelsSubject: BehaviorSubject<List<DialogModel>>
    private var messageModelsSubject: BehaviorSubject<List<MessageModel>>


    override fun subcribeDialogs(): Observable<List<DialogModel>> = dialogModelsSubject.asObservable()
    override fun subcribeMessage(): Observable<List<MessageModel>> = messageModelsSubject.asObservable()

    constructor(storIOSQLite: StorIOSQLite) {
        this.storIOSQLite = storIOSQLite
        dialogModelsSubject = BehaviorSubject.create()
        messageModelsSubject = BehaviorSubject.create()
        loadDialogs()
    }


    fun loadDialogs() {
        storIOSQLite
                .get()
                .listOfObjects(DialogModel::class.java!!)
                .withQuery(DialogTable.QUERY_ALL)
                .prepare()
                .asRxObservable()
                .subscribeOn(Schedulers.io())
                .subscribe({ dialogModelsSubject.onNext(it) },
                        { Timber.e(it.message) },
                        {}

                )
    }

}
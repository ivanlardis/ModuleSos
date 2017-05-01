package com.example.i_larin.pixabayreader.repository

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
import java.util.*




class DialogRepository : IDialogRepository {
    override fun selectedDialog(id: Long) {

        storIOSQLite
                .get()
                .listOfObjects(MessageModel::class.java!!)


                .withQuery(RawQuery.builder()
                        .query("select * from ${MessageTable.TABLE} " +
                                " where ${MessageTable.COLUMN_ID_DIALOG} == $id")

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







    override fun loadData() {
        var lang = ArrayList<DialogModel>()

        lang.add(DialogModel(null, "daw", "dwa", 1212, ""))
        lang.add(DialogModel(null, "daw", "dwa", 1212, ""))
        lang.add(DialogModel(null, "daw", "dwa", 1212, ""))
        lang.add(DialogModel(null, "daw", "dwa", 1212, ""))

        storIOSQLite
                .put()
                .objects(lang)
                .prepare()
                .executeAsBlocking()
        var lang1 = ArrayList<MessageModel>()

        lang1.add(MessageModel(null, "daw", 1221, 1212, 2112))
        lang1.add(MessageModel(null, "daw", 1221, 1212, 2112))
        lang1.add(MessageModel(null, "daw", 1221, 1212, 2112))
        lang1.add(MessageModel(null, "daw", 1221, 1212, 2112))
        lang1.add(MessageModel(null, "daw", 1221, 1212, 2112))
        lang1.add(MessageModel(null, "daw", 1221, 1212, 2112))

        storIOSQLite
                .put()
                .objects(lang1)
                .prepare()
                .executeAsBlocking()

    }

}
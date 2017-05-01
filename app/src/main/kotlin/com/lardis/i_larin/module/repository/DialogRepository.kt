package com.example.i_larin.pixabayreader.repository

import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import java.util.*


/**
 * Created by i_larin on 28.01.17.
 */

class DialogRepository : IDialogRepository {

      var storIOSQLite: StorIOSQLite

    constructor(storIOSQLite: StorIOSQLite) {
        this.storIOSQLite = storIOSQLite
    }

    override fun loadData() {
var lang=ArrayList<DialogModel>()

        lang.add(DialogModel(null,"daw","dwa",1212,""))
        lang.add(DialogModel(null,"daw","dwa",1212,""))
        lang.add(DialogModel(null,"daw","dwa",1212,""))
        lang.add(DialogModel(null,"daw","dwa",1212,""))

        storIOSQLite
                .put()
                .objects(lang)
                .prepare()
                .executeAsBlocking()
        var lang1=ArrayList<MessageModel>()

        lang1.add(MessageModel(null,"daw",1221,1212,2112))
        lang1.add(MessageModel(null,"daw",1221,1212,2112))
        lang1.add(MessageModel(null,"daw",1221,1212,2112))
        lang1.add(MessageModel(null,"daw",1221,1212,2112))
        lang1.add(MessageModel(null,"daw",1221,1212,2112))
        lang1.add(MessageModel(null,"daw",1221,1212,2112))

        storIOSQLite
                .put()
                .objects(lang1)
                .prepare()
                .executeAsBlocking()

    }

}
package com.digittonic.storiosample.db

import android.database.Cursor
import com.lardis.i_larin.module.storage.entities.MessageModel
import com.lardis.i_larin.module.storage.tables.MessageTable
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver

/**
 * Created by rpagyc on 13-Dec-15.
 */
class MessageGetResolver : DefaultGetResolver<MessageModel>() {

    override fun mapFromCursor(cursor: Cursor): MessageModel {
        val track = MessageModel(
                cursor.getLong(cursor.getColumnIndexOrThrow(MessageTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(MessageTable.COLUMN_MESSAGE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(MessageTable.COLUMN_ID_AUTHOR)),
                cursor.getLong(cursor.getColumnIndexOrThrow(MessageTable.COLUMN_ID_DIALOG)),
                cursor.getLong(cursor.getColumnIndexOrThrow(MessageTable.COLUMN_TIME_CREATE))


        )

        return track;
    }

}
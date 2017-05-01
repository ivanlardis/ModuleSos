package com.digittonic.storiosample.db

import android.database.Cursor
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.tables.DialogTable
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver

/**
 * Created by rpagyc on 13-Dec-15.
 */
class DialogGetResolver : DefaultGetResolver<DialogModel>() {

    override fun mapFromCursor(cursor: Cursor): DialogModel =DialogModel(
                cursor.getLong(cursor.getColumnIndexOrThrow(DialogTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DialogTable.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(DialogTable.COLUMN_DESCRIPTION)),
                cursor.getLong(cursor.getColumnIndexOrThrow(DialogTable.COLUMN_TIME_CREATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(DialogTable.COLUMN_LAST_MESSAGE))
        )



}
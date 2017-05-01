package com.digittonic.storiosample.db

import android.content.ContentValues
import com.lardis.i_larin.module.storage.entities.MessageModel
import com.lardis.i_larin.module.storage.tables.DialogTable
import com.lardis.i_larin.module.storage.tables.MessageTable
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver
import com.pushtorefresh.storio.sqlite.queries.InsertQuery
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery


class MessagePutResolver : DefaultPutResolver<MessageModel>() {

  override fun mapToContentValues(model: MessageModel): ContentValues {
    val cv = ContentValues()
    model.id?.let {    cv.put(DialogTable.COLUMN_ID, model.id)}
    cv.put(MessageTable.COLUMN_ID_DIALOG, model.idDialog)
    cv.put(MessageTable.COLUMN_TIME_CREATE, model.timeCreate)
    cv.put(MessageTable.COLUMN_ID_AUTHOR, model.idAuthor)
    cv.put(MessageTable.COLUMN_MESSAGE, model.message)

    return cv
  }

  override fun mapToInsertQuery(track: MessageModel): InsertQuery {
    return InsertQuery.builder()
      .table(MessageTable.TABLE)
      .build()
  }

  override fun mapToUpdateQuery(track: MessageModel): UpdateQuery {
    return UpdateQuery.builder()
      .table(MessageTable.TABLE)
      .where("${MessageTable.COLUMN_ID} = ?")
      .whereArgs(track.id)
      .build()
  }

}
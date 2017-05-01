package com.digittonic.storiosample.db

import android.content.ContentValues
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.tables.DialogTable
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver
import com.pushtorefresh.storio.sqlite.queries.InsertQuery
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery

/**
 * Created by rpagyc on 13-Dec-15.
 */
class DialogPutResolver : DefaultPutResolver<DialogModel>() {

  override fun mapToContentValues(model: DialogModel): ContentValues {
    val cv = ContentValues()
 model.id?.let {    cv.put(DialogTable.COLUMN_ID, model.id)}
    cv.put(DialogTable.COLUMN_TIME_CREATE, model.timeCreate)
    cv.put(DialogTable.COLUMN_DESCRIPTION, model.description)
    cv.put(DialogTable.COLUMN_TITLE, model.title)
    cv.put(DialogTable.COLUMN_LAST_MESSAGE, model.lastMessage)

    return cv
  }

  override fun mapToInsertQuery(track: DialogModel): InsertQuery {
    return InsertQuery.builder()
      .table(DialogTable.TABLE)
      .build()
  }

  override fun mapToUpdateQuery(track: DialogModel): UpdateQuery {
    return UpdateQuery.builder()
      .table(DialogTable.TABLE)
      .where("${DialogTable.COLUMN_ID} = ?")
      .whereArgs(track.id)
      .build()
  }

}
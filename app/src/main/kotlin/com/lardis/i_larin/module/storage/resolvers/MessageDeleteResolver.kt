package com.digittonic.storiosample.db

import com.lardis.i_larin.module.storage.entities.MessageModel
import com.lardis.i_larin.module.storage.tables.DialogTable
import com.lardis.i_larin.module.storage.tables.MessageTable
import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery


class MessageDeleteResolver : DefaultDeleteResolver<MessageModel>() {
  override fun mapToDeleteQuery(model: MessageModel): DeleteQuery {
    return DeleteQuery.builder()
      .table(MessageTable.TABLE)
      .where("${DialogTable.COLUMN_ID} = ?")
      .whereArgs(model.id)
      .build()
  }
}
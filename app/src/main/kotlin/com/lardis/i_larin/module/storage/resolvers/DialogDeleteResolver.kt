package com.digittonic.storiosample.db

import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.tables.DialogTable
import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery

/**
 * Created by rpagyc on 13-Dec-15.
 */
class DialogDeleteResolver : DefaultDeleteResolver<DialogModel>() {
  override fun mapToDeleteQuery(model: DialogModel): DeleteQuery {
    return DeleteQuery.builder()
      .table(DialogTable.TABLE)
      .where("${DialogTable.COLUMN_ID} = ?")
      .whereArgs(model.id)
      .build()
  }
}
package com.lardis.i_larin.module.storage.tables

import com.pushtorefresh.storio.sqlite.queries.Query

object DialogTable// This is just class with Meta Data, we don't need instances

{
    ///1. Сделать две SQL таблицы, Диалог(id, Заголовок, Описание, Время создания
    /// ) и Сообщение(id, Текст сообщения, id Автора, Время создания).
      val TABLE = "dialog"

      val COLUMN_ID = "_id"

      val COLUMN_TITLE = "TITLE"

      val COLUMN_DESCRIPTION = "DESCRIPTION"

      val COLUMN_TIME_CREATE = "TIME_CREATE"

      val COLUMN_LAST_MESSAGE = "LAST_MESSAGE"

    val QUERY_ALL = Query.builder()
            .table(TABLE)
            .build()

    val createTableQuery: String
        get() = "CREATE TABLE $TABLE( " +
                "$COLUMN_ID  INTEGER NOT NULL PRIMARY KEY , " +
                "$COLUMN_TITLE  TEXT  NOT NULL , " +
                "$COLUMN_DESCRIPTION  TEXT NOT NULL , " +
                "$COLUMN_LAST_MESSAGE  TEXT  , " +
                "$COLUMN_TIME_CREATE  INTEGER NOT NULL );"

}




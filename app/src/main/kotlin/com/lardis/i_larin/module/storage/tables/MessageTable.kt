package com.lardis.i_larin.module.storage.tables

import com.pushtorefresh.storio.sqlite.queries.Query

object MessageTable {

    ///1. Сделать две SQL таблицы, Диалог(id, Заголовок, Описание, Время создания
    /// ) и Сообщение(id, Текст сообщения, id Автора, Время создания).


    val TABLE = "message"

    val COLUMN_ID = "_id"


    val COLUMN_MESSAGE = "message"

    val COLUMN_ID_AUTHOR = "id_author"

    val COLUMN_ID_DIALOG = "id_dialog"

    val COLUMN_TIME_CREATE = "time_create"

    val QUERY_ALL = Query.builder()
            .table(TABLE)
            .build()

    val createTableQuery: String
        get() = "CREATE TABLE $TABLE(" +
                " $COLUMN_ID  INTEGER NOT NULL PRIMARY KEY ," +
                " $COLUMN_TIME_CREATE  INTEGER NOT  NULL , " +
                " $COLUMN_MESSAGE  TEXT NOT  NULL , " +
                " $COLUMN_ID_DIALOG  INTEGER NOT  NULL , " +
                " $COLUMN_ID_AUTHOR  INTEGER      );"

}


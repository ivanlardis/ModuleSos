package com.lardis.i_larin.module.storage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lardis.i_larin.module.storage.tables.DialogTable
import com.lardis.i_larin.module.storage.tables.MessageTable

class DbOpenHelper(context: Context) : SQLiteOpenHelper(context, "table", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DialogTable.createTableQuery)
        db.execSQL(MessageTable.createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {


            db.execSQL( "ALTER TABLE ${DialogTable.TABLE} " +
                    " ADD COLUMN ${DialogTable.COLUMN_LAST_MESSAGE} text  ;");
        }
    }
}

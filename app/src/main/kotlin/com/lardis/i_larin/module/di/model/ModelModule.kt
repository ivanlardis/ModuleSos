package com.example.i_larin.pixabayreader.di.model

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.digittonic.storiosample.db.*
import com.google.firebase.database.FirebaseDatabase
import com.lardis.i_larin.module.storage.DbOpenHelper
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ModelModule(val context: Context) {


    @Provides
    @Singleton
    fun provideSQLiteOpenHelper(): SQLiteOpenHelper {
        return DbOpenHelper(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        var instance = FirebaseDatabase.getInstance()
        instance.setPersistenceEnabled(true)
        return instance
    }




    @Provides
    @Singleton
    fun provideStorIOSQLite(sqLiteOpenHelper: SQLiteOpenHelper): StorIOSQLite {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(DialogModel::class.java, SQLiteTypeMapping.builder<DialogModel>()
                        .putResolver(DialogPutResolver())
                        .getResolver(DialogGetResolver())
                        .deleteResolver(DialogDeleteResolver())
                        .build())
                .addTypeMapping(MessageModel::class.java, SQLiteTypeMapping.builder<MessageModel>()
                        .putResolver(MessagePutResolver())
                        .getResolver(MessageGetResolver())
                        .deleteResolver(MessageDeleteResolver())
                        .build())
                .build()
    }

}
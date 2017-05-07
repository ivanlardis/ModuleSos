package com.example.i_larin.pixabayreader.di.model

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.digittonic.storiosample.db.*
import com.example.i_larin.pixabayreader.network.ApiFactoty
import com.example.i_larin.pixabayreader.network.OkHttpClientFactory
import com.example.i_larin.pixabayreader.network.VKApi
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.GsonBuilder
import com.lardis.i_larin.module.storage.DbOpenHelper
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ModelModule(val context: Context) {

    val VK_BASE_URL = "https://api.vk.com/method/"

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


    @Provides
    @Singleton
    fun provideApi(apiFactoty: ApiFactoty): VKApi = apiFactoty.create(VKApi::class.java!!)

    @Provides
    @Singleton
    fun provideApiFactoty(converterFactory: Converter.Factory,
                          callAdapterFactory: CallAdapter.Factory, okHttpClientFactory: OkHttpClientFactory): ApiFactoty =
            ApiFactoty(VK_BASE_URL, converterFactory, callAdapterFactory, okHttpClientFactory)



    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJavaCallAdapterFactory.create()


    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClientFactory(context)
}
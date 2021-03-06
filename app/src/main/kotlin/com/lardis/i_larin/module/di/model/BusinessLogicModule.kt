package com.example.i_larin.pixabayreader.di.model

import com.example.i_larin.pixabayreader.network.VKApi
import com.example.i_larin.pixabayreader.repository.*
import com.google.firebase.database.FirebaseDatabase
import com.pushtorefresh.storio.sqlite.StorIOSQLite
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by black-sony on 29.01.17.
 */

@Module
class BusinessLogicModule {


    @Singleton
    @Provides
    fun provideDialogRepository(storIOSQLite: StorIOSQLite): IDialogRepository {
        return DialogRepository(storIOSQLite)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(api: VKApi): ILoginRepository {
        return LoginRepository(api)
    }

    @Singleton
    @Provides
    fun provideIncidentsRepository(api: VKApi, firebaseDatabase: FirebaseDatabase): IIncidentsRepository {
        return IncidentsRepository(api,firebaseDatabase)
    }

}

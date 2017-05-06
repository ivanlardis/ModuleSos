package com.example.i_larin.pixabayreader.di.model

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
    fun provideLoginRepository(): ILoginRepository {
        return LoginRepository()
    }

    @Singleton
    @Provides
    fun provideIncidentsRepository(firebaseDatabase: FirebaseDatabase): IIncidentsRepository {
        return IncidentsRepository(firebaseDatabase)
    }

}

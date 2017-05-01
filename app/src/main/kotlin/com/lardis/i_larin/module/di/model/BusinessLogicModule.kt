package com.example.i_larin.pixabayreader.di.model

import com.example.i_larin.pixabayreader.repository.DialogRepository
import com.example.i_larin.pixabayreader.repository.IDialogRepository
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

}

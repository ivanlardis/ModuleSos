package com.lardis.i_larin.module

import android.app.Application
import com.example.i_larin.pixabayreader.di.DI
import com.facebook.stetho.Stetho
import com.lardis.i_larin.module.prefs.PrefsRX
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber


/**
 * Created by black-sony on 30.04.17.
 */
class App :Application(){
companion object
{

    lateinit var INSTANCE: App

}
    lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        PrefsRX.getInstance().init(this)

        DI.init(applicationContext)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }


        INSTANCE = this
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone.router
    }
}
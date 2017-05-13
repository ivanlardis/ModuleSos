package com.example.i_larin.pixabayreader.repository

import com.example.i_larin.pixabayreader.network.VKApi
import com.lardis.i_larin.module.prefs.Prefs
import com.vk.sdk.VKAccessToken
import rx.Observable
import rx.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit


class LoginRepository : ILoginRepository {
    override fun updateInfo() {
        VKAccessToken.currentToken()?.let {

            getUser(it.userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(
                            { it?.forEach {

                                Timber.e("updateInfo")
                                         Prefs.USER_ID.set(it.uid)
                                        Prefs.FIRST_NAME.set(it.firstName)
                                        Prefs.LAST_NAME.set(it.lastName)
                                        Prefs.PHOTO_URL.set(it.photo50)

                                }

                            },

                            {}, {})

        }


    }

    var api: VKApi

    constructor(api: VKApi) {
        this.api = api
    }

    fun getUser(id: String) =
            api.getUsers(id, "photo_50", "5.64")
                    .map { it.response }


    override fun checkUser(login: String, password: String): Observable<Boolean> =
            Observable.just(isValidUser(login, password))
                    .delay(5, TimeUnit.SECONDS)


    private fun isValidUser(login: String, password: String) = login.equals(Prefs.LOGIN.string) && password.equals(Prefs.PASWORD.string)

}
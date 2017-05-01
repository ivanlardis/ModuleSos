package com.example.i_larin.pixabayreader.repository

import com.lardis.i_larin.module.prefs.Prefs
import rx.Observable
import java.util.concurrent.TimeUnit


class LoginRepository : ILoginRepository {
    override fun checkUser(login: String, password: String): Observable<Boolean> =
            Observable.just(isValidUser(login, password))
                    .delay(5, TimeUnit.SECONDS)


    private fun isValidUser(login: String, password: String) = login.equals(Prefs.LOGIN.string) && password.equals(Prefs.PASWORD.string)

}
package com.example.i_larin.pixabayreader.repository

import rx.Observable


interface ILoginRepository {


    fun checkUser(login:String,password:String): Observable<Boolean>
    fun updateInfo( )
}

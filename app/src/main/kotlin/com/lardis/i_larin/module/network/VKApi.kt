package com.example.i_larin.pixabayreader.network

import com.lardis.i_larin.module.model.BaseVKResponse
import com.lardis.i_larin.module.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by i_larin on 28.01.17.
 */
interface VKApi {
    @GET("users.get?")
      fun getUsers(
            @Query("user_ids") user_ids: String,
            @Query("fields") fields: String,
            @Query("version") version: String): Observable<BaseVKResponse<MutableList<UserModel>>>

}
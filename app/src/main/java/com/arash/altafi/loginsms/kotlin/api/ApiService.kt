package com.arash.altafi.loginsms.kotlin.api

import com.arash.altafi.loginsms.kotlin.models.ResponseVerifyKotlin
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("auth/verify.php")
    fun registerUser(@Field("mobile_phone") phone:String, @Field("name_family") name:String) : Single<ResponseVerifyKotlin>

}

fun retrofitApi() : ApiService {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://arashaltafi.ir/git/android/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}

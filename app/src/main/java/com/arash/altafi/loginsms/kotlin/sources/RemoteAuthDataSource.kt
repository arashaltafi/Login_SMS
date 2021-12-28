package com.arash.altafi.loginsms.kotlin.sources

import com.arash.altafi.loginsms.kotlin.api.ApiService
import com.arash.altafi.loginsms.kotlin.models.ResponseVerifyKotlin
import io.reactivex.Single

class RemoteAuthDataSource (val apiService: ApiService) : AuthDataSource {

    override fun registerUser(phone: String, name: String): Single<ResponseVerifyKotlin> = apiService.registerUser(phone , name)

}
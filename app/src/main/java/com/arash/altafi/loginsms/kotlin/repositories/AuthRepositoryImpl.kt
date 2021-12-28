package com.arash.altafi.loginsms.kotlin.repositories

import com.arash.altafi.loginsms.kotlin.models.ResponseVerifyKotlin
import com.arash.altafi.loginsms.kotlin.sources.AuthDataSource
import io.reactivex.Single

class AuthRepositoryImpl(val remoteAuthDataSource: AuthDataSource) : AuthRepository {

    override fun registerUser(phone: String, name: String): Single<ResponseVerifyKotlin> = remoteAuthDataSource.registerUser(phone , name)

}
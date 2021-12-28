package com.arash.altafi.loginsms.kotlin.sources

import com.arash.altafi.loginsms.kotlin.models.ResponseVerifyKotlin
import io.reactivex.Single

interface AuthDataSource {

    fun registerUser(phone:String , name:String) : Single<ResponseVerifyKotlin>

}
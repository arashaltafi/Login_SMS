package com.arash.altafi.loginsms.kotlin.repositories

import com.arash.altafi.loginsms.kotlin.models.ResponseVerifyKotlin
import io.reactivex.Single

interface AuthRepository {

    fun registerUser(phone:String , name:String) : Single<ResponseVerifyKotlin>

}
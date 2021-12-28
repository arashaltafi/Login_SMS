package com.arash.altafi.loginsms.kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arash.altafi.loginsms.kotlin.models.ResponseVerifyKotlin
import com.arash.altafi.loginsms.kotlin.repositories.AuthRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.coroutines.coroutineContext

class KotlinViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val registerUserLiveData = MutableLiveData<ResponseVerifyKotlin>()

    fun registerUser(phone : String , name : String) {
        authRepository.registerUser("09187677641", "Arash")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ResponseVerifyKotlin> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }
                override fun onSuccess(t: ResponseVerifyKotlin) {
                    registerUserLiveData.value = t
                }
                override fun onError(e: Throwable) {

                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

}
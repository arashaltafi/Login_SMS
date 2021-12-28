package com.arash.altafi.loginsms.java.api;

import com.arash.altafi.loginsms.java.models.ResponseVerifyJava;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/verify.php")
    Single<ResponseVerifyJava> sendUser(@Field("mobile_phone") String phone , @Field("name_family") String name);

}

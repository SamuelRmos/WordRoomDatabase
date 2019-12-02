package com.example.roomwordsample.controller.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    @FormUrlEncoded
    fun registerUser(@Field("email") email:String,
                     @Field("name") name:String,
                     @Field("password") password: String):Observable<String>

    @POST("login")
    @FormUrlEncoded
    fun loginUser(@Field("email") email: String,
                  @Field("password") password: String): Observable<String>
}
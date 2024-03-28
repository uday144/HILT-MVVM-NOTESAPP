package com.hiltmvvm.notesample.repository

import android.util.Log
import com.hiltmvvm.notesample.api.UserAPI
import com.hiltmvvm.notesample.models.UserRequest
import com.hiltmvvm.notesample.utils.Constants.TAG
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    suspend fun registerUser(userRequest: UserRequest) {
        val response = userAPI.signup(userRequest)
        Log.d(TAG, response.body().toString())
    }

    suspend fun loginUser(userRequest: UserRequest) {
        val response =userAPI.signin(userRequest)
        Log.d(TAG, response.body().toString())
    }
}
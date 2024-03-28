package com.hiltmvvm.notesample.models

import com.hiltmvvm.notesample.models.User

data class UserResponse(
    val token: String,
    val user: User
)
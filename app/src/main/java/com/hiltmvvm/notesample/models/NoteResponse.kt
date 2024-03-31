package com.hiltmvvm.notesample.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteResponse(
    @PrimaryKey(autoGenerate = false)
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val title: String,
    val updatedAt: String,
    val userId: String
)
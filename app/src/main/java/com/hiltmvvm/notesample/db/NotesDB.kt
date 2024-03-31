package com.hiltmvvm.notesample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hiltmvvm.notesample.models.NoteResponse

@Database(entities = [NoteResponse::class], version = 1)
abstract class NotesDB : RoomDatabase() {

    abstract fun getNotesDAO() : NotesDAO

}
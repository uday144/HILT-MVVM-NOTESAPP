package com.hiltmvvm.notesample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hiltmvvm.notesample.models.NoteResponse

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotes(products : List<NoteResponse>)

    @Query("SELECT * FROM NoteResponse")
    suspend fun getNotes() : List<NoteResponse>

}
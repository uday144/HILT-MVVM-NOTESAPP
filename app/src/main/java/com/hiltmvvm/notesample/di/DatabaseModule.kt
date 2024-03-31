package com.hiltmvvm.notesample.di

import android.content.Context
import androidx.room.Room
import com.hiltmvvm.notesample.db.NotesDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context: Context): NotesDB {
        return Room.databaseBuilder(context, NotesDB::class.java, "NotesDB").build()
    }
}
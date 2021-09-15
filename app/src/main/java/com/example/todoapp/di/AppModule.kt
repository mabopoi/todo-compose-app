package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.db.ToDoItemDao
import com.example.todoapp.data.db.ToDoItemDatabase
import com.example.todoapp.data.repository.HomeRepositoryImpl
import com.example.todoapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideToDoItemDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ToDoItemDatabase::class.java, "todo_item_db").build()

    @Singleton
    @Provides
    fun provideToDoItemDao(
        database: ToDoItemDatabase
    ) = database.toDoItemDao()

    @Singleton
    @Provides
    fun provideHomeRepository(dao: ToDoItemDao): HomeRepository = HomeRepositoryImpl(dao)
}
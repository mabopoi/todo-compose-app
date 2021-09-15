package com.example.todoapp.data.db

import androidx.room.*
import com.example.todoapp.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ToDoItem): Int

    @Query("SELECT * FROM todo_item")
    fun observeAll() : Flow<List<ToDoItem>>

    @Delete
    suspend fun delete(item: ToDoItem)
}
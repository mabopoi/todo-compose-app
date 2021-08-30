package com.example.todoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.models.ToDoItem

@Dao
interface ToDoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ToDoItem): Int

    @Query("SELECT * FROM todo_item")
    fun observeAll() : LiveData<List<ToDoItem>>

    @Delete
    suspend fun delete(item: ToDoItem)
}
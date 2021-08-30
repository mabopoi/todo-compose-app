package com.example.todoapp.data.repositories.home

import androidx.lifecycle.LiveData
import com.example.todoapp.models.ToDoItem

interface IHomeRepository {
    fun getAllToDos(): LiveData<List<ToDoItem>>

    suspend fun deleteToDo(item: ToDoItem)
}
package com.example.todoapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.domain.model.ToDoItem

interface HomeRepository {
    fun getAllToDos(): LiveData<List<ToDoItem>>

    suspend fun deleteToDo(item: ToDoItem)
}
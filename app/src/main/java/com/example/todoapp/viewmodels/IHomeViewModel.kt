package com.example.todoapp.viewmodels

import androidx.lifecycle.LiveData
import com.example.todoapp.models.ToDoItem

interface IHomeViewModel {
    fun getAllToDos(): LiveData<List<ToDoItem>>

    suspend fun deleteToDo(item: ToDoItem)
}
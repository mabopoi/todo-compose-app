package com.example.todoapp.domain.repository

import com.example.todoapp.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllToDos(): Flow<List<ToDoItem>>

    suspend fun deleteToDo(item: ToDoItem)
}
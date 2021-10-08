package com.example.todoapp.domain.use_case.add_todo

import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.repository.HomeRepository
import java.lang.Exception
import javax.inject.Inject

class AddToDoUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(item: ToDoItem): Long? {
        return try {
            repository.addToDo(item)
        } catch (e: Exception) {
            return null
        }
    }
}
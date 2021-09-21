package com.example.todoapp.domain.use_case.delete_todo

import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.repository.HomeRepository
import java.lang.Exception
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(item: ToDoItem): Boolean {
        try {
            repository.deleteToDo(item)
        } catch (e: Exception) {
            return false
        }
        return true
    }
}
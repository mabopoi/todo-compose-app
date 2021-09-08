package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.db.ToDoItemDao
import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class HomeRepositoryImpl @Inject constructor(
    private val dao: ToDoItemDao
): HomeRepository {

    override fun getAllToDos(): LiveData<List<ToDoItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteToDo(item: ToDoItem) {
        TODO("Not yet implemented")
    }

}
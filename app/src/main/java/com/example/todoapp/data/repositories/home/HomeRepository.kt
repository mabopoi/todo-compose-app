package com.example.todoapp.data.repositories.home

import androidx.lifecycle.LiveData
import com.example.todoapp.data.db.ToDoItemDao
import com.example.todoapp.models.ToDoItem
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class HomeRepository @Inject constructor(
    private val dao: ToDoItemDao
): IHomeRepository {

    override fun getAllToDos(): LiveData<List<ToDoItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteToDo(item: ToDoItem) {
        TODO("Not yet implemented")
    }

}
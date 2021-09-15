package com.example.todoapp.data.repository

import com.example.todoapp.data.db.ToDoItemDao
import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class HomeRepositoryImpl @Inject constructor(
    private val dao: ToDoItemDao
): HomeRepository {

    override fun getAllToDos(): Flow<List<ToDoItem>> {
        return dao.observeAll()
    }

    override suspend fun deleteToDo(item: ToDoItem) {
        return dao.delete(item)
    }

}
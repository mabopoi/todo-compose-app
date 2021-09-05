package com.example.todoapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.data.repositories.home.HomeRepository
import com.example.todoapp.models.ToDoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): IHomeViewModel {

    private val _toDosList = MutableLiveData<List<ToDoItem>>(emptyList())
    val toDosList: LiveData<List<ToDoItem>> = _toDosList

    override fun getAllToDos(): LiveData<List<ToDoItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteToDo(item: ToDoItem) {
        TODO("Not yet implemented")
    }
}
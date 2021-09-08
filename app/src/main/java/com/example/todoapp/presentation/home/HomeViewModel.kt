package com.example.todoapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    private val _toDosList = MutableLiveData<List<ToDoItem>>(emptyList())
    val toDosList: LiveData<List<ToDoItem>> = _toDosList

   fun getAllToDos(): LiveData<List<ToDoItem>> {
        TODO("Not yet implemented")
    }

   suspend fun deleteToDo(item: ToDoItem) {
        TODO("Not yet implemented")
    }
}
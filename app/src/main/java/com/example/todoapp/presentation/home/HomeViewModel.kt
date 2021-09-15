package com.example.todoapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.common.Resource
import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.use_case.get_todos.GetToDosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getToDosUseCase: GetToDosUseCase
) : ViewModel() {

    private val _toDosList =
        mutableStateOf<List<ToDoItem>>(emptyList()) //must change to a Model State
    val toDosList: State<List<ToDoItem>> = _toDosList // must change to a Model State

    fun getAllToDos() {
        viewModelScope.launch {
            getToDosUseCase().collect { res ->
                when (res) {
                    is Resource.Success -> TODO()
                    is Resource.Error -> TODO()
                    is Resource.Loading -> TODO()
                }
            }
        }
    }

    suspend fun deleteToDo(item: ToDoItem) {
        TODO("Not yet implemented")
    }
}
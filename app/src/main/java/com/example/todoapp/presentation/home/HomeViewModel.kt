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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getToDosUseCase: GetToDosUseCase
) : ViewModel() {

    private val _state =
        mutableStateOf(HomeState()) //must change to a Model State
    val state: State<HomeState> = _state // must change to a Model State

    init {
        getAllToDos()
    }

    private fun getAllToDos() {
        viewModelScope.launch {
            getToDosUseCase().collect { res ->
                when (res) {
                    is Resource.Success -> res.data?.onEach { _state.value = HomeState(list = it) }
                    is Resource.Error -> _state.value = HomeState(error = res.message)
                    is Resource.Loading -> _state.value = HomeState(isLoading = true)
                }
            }
        }
    }

    suspend fun deleteToDo(item: ToDoItem) {
        TODO("Not yet implemented")
    }
}
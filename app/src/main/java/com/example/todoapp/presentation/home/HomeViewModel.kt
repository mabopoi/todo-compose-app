package com.example.todoapp.presentation.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.common.Resource
import com.example.todoapp.common.Times
import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.use_case.add_todo.AddToDoUseCase
import com.example.todoapp.domain.use_case.delete_todo.DeleteToDoUseCase
import com.example.todoapp.domain.use_case.get_todos.GetToDosUseCase
import com.example.todoapp.utils.NotificationManagerCustom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getToDosUseCase: GetToDosUseCase,
    private val addToDoUseCase: AddToDoUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val injectedDispatcher: CoroutineDispatcher //good practice for testing
) : ViewModel() {

    private val _state =
        mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getAllToDos()
    }

    private fun getAllToDos() {
        viewModelScope.launch(injectedDispatcher) {
            getToDosUseCase().collect { res ->
                when (res) {
                    is Resource.Success -> res.data?.onEach { _state.value = HomeState(list = it) }
                        ?.launchIn(viewModelScope)
                    is Resource.Error -> _state.value = HomeState(error = res.message)
                    is Resource.Loading -> _state.value = HomeState(isLoading = true)
                }
            }
        }
    }

    fun addToDo(
        title: String,
        description: String,
        context: Context,
        timeAmount: String,
        time: Times
    ) {
        val formattedTimeAmount = timeAmount.replace(Regex("[, ./]"), "").toLong()

        val rememberTime = System.currentTimeMillis() + formattedTimeAmount * time.valueInMilis

        val item = ToDoItem(title = title, description = description, rememberTime = rememberTime)
        viewModelScope.launch(injectedDispatcher) {
            val newItemId = addToDoUseCase(item)
            if (newItemId != null)
                NotificationManagerCustom.showNewItemNotification(newItemId, context)
        }
    }

    fun deleteToDo(item: ToDoItem) {
        viewModelScope.launch(injectedDispatcher) {
            deleteToDoUseCase(item)
        }
    }
}
package com.example.todoapp.presentation.home

import com.example.todoapp.domain.model.ToDoItem

data class HomeState(
    val list: List<ToDoItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

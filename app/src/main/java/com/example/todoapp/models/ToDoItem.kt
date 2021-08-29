package com.example.todoapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_item")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
)

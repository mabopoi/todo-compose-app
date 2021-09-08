package com.example.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.domain.model.ToDoItem

@Database(
    entities = [ToDoItem::class],
    version = 1
)
abstract class ToDoItemDatabase : RoomDatabase() {

    abstract fun toDoItemDao(): ToDoItemDao

}
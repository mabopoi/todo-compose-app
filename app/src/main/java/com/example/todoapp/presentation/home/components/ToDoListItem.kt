package com.example.todoapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todoapp.domain.model.ToDoItem

@ExperimentalMaterialApi
@Composable
fun ToDoListItem(toDoItem: ToDoItem, onClick: () -> Unit) {
    val dismissState = rememberDismissState()

    SwipeToDismiss(state = dismissState, background = { Box(modifier = Modifier.fillMaxSize()) }){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = toDoItem.title)
                Text(text = toDoItem.description)
            }
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null )
        }
    }
}
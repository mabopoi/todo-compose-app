package com.example.todoapp.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.presentation.home.components.NewToDoDialog
import com.example.todoapp.presentation.home.components.ToDoListItem

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailScreen: (toDoId: String) -> Unit,
) {
    val state = viewModel.state.value
    var showDialog by remember { mutableStateOf(false) }

    //DialogLambdas
    val setShowDialog = { value: Boolean -> showDialog = value }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { setShowDialog(true) },
                modifier = Modifier.offset(y = (-50).dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
            }
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = state.list, key = { item -> item.id!! }) { toDoItem ->
                        ToDoListItem(
                            toDoItem = toDoItem,
                            onClick = { navigateToDetailScreen(toDoItem.id.toString()) },
                            onSwipe = viewModel::deleteToDo
                        )
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                if (state.error != null) {
                    Text(
                        text = state.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center),
                        color = Color.Red
                    )
                }
            }

            if (showDialog) {
                NewToDoDialog(createToDo = viewModel::addToDo, setShowDialog = setShowDialog)
            }
        })
}
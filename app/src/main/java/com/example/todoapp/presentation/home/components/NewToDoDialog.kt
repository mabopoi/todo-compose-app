package com.example.todoapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.utils.NotificationManagerCustom

@Composable
fun NewToDoDialog(
    createToDo: (title: String, desc: String) -> Unit,
    setShowDialog: (Boolean) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current

    Dialog(
        onDismissRequest = { setShowDialog(false) },
        content = {
            Column(
                modifier = Modifier
                    .background(White)
                    .padding(8.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { value -> title = value })
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = description,
                    onValueChange = { value -> description = value })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { setShowDialog(false) }) {
                        Text(text = "Cancelar")
                    }
                    Button(onClick = {
                        createToDo(title, description)
                        setShowDialog(false)
                        title = ""
                        description = ""
                        NotificationManagerCustom.showNewItemNotification(1, context)
                    }) {
                        Text(text = "Agregar")
                    }
                }
            }
        },
    )
}
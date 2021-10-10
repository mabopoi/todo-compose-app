package com.example.todoapp.presentation.home.components

import android.content.Context
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
import com.example.todoapp.common.Times
import com.example.todoapp.utils.NotificationManagerCustom

@Composable
fun NewToDoDialog(
    createToDo: (title: String, desc: String, context: Context) -> Unit,
    setShowDialog: (Boolean) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var timeAmount by remember { mutableStateOf("1") }
    var selectedTime by remember { mutableStateOf(Times.Minutes) }

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
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Remember me in:")
                    TimeSelector(
                        timeAmount = timeAmount,
                        selectedTime = selectedTime,
                        setTimeAmount = { timeAmount = it },
                        setSelectedTime = { selectedTime = it })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { setShowDialog(false) }) {
                        Text(text = "Go back")
                    }
                    Button(onClick = {
                        createToDo(title, description, context)
                        setShowDialog(false)
                    }) {
                        Text(text = "Add")
                    }
                }
            }
        },
    )
}
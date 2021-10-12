package com.example.todoapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.todoapp.common.Times

@Composable
fun TimeSelector(
    timeAmount: String,
    selectedTime: Times,
    setTimeAmount: (String) -> Unit,
    setSelectedTime: (Times) -> Unit,
    onEnterPressed: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = timeAmount,
            onValueChange = setTimeAmount,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Go
            ),
            modifier = Modifier.width(40.dp),
            keyboardActions = KeyboardActions(onGo = { onEnterPressed() } )
        )
        Text(text = selectedTime.name, modifier = Modifier.clickable { isExpanded = true })
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .width(160.dp)
        ) {
            Times.values().forEach {
                DropdownMenuItem(onClick = {
                    setSelectedTime(it)
                    isExpanded = false
                }) {
                    Text(it.name)
                }
            }
        }
    }

}
package com.lixoten.tasklist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddItemButton(
    modifier: Modifier = Modifier,
    onAddItemClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onAddItemClick()
        }
    ) {
        Text(text = "Add Generic Item")
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel(),
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        TextField(
            value = taskViewModel.inputTask,
            onValueChange = { taskViewModel.updateInputTask(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text(text = "Task")},
            placeholder = { Text(text = "Enter Task")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        AddItemButton(
            onAddItemClick = {
                taskViewModel.addItem(
                    Task(
                        label = taskViewModel.inputTask
                    )
                )
            }
        )

        TasksList(
            list = taskViewModel.tasks,
            onCheckedTask = { task, checked ->
                taskViewModel.changeTaskChecked(task, checked)
            },
            onRemoveItem = { task ->
                taskViewModel.removeItem(task)
            }
        )
    }
}
package com.lixoten.tasklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    list: List<Task>,
    onCheckedTask: (Task, Boolean) -> Unit,
    onRemoveItem: (Task) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier
    ) {
        items(
            items = list,
            /**
             * Use key param to define unique keys representing the items in a mutable list,
             * instead of using the default key (list position). This prevents unnecessary
             * recompositions.
             */
            key = { task -> task.id }
        ) { task ->
            TaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { itt -> onCheckedTask(task, itt) },
                onRemoveItem = { onRemoveItem(task) }
            )
        }
    }
}

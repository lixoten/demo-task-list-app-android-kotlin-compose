package com.lixoten.tasklist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class TaskViewModel : ViewModel() {
    private val _tasks = getTasks().toMutableStateList()
    val tasks: List<Task>
        get() = _tasks

    var inputTask by mutableStateOf("")
        private set

    fun updateInputTask(input: String){
        inputTask = input
    }

    private fun formatNewItem(input: String, num: Int): String{
        return if (input == "") {
            "Task # $num"
        } else {
            "$input # $num"
        }
    }

    fun addItem(item: Task) {
        val newId = tasks[tasks.lastIndex].id + 1
        val tmp = formatNewItem(item.label, newId)
        _tasks.add(
            Task(
                id = newId,
                label = tmp
            )
        )
    }

    fun removeItem(item: Task) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: Task, checked: Boolean) {
        _tasks.set(item.id, item.copy(
                checked = checked
            )
        )
    }
}


private fun getTasks() =
    List(6) {
        Task(it, "Task # $it")
    }
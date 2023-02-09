package com.lixoten.tasklist

data class Task(
    val id: Int = 0,
    val label: String = "",
    val checked: Boolean = false
)
//class Task(
//    val id: Int = 0,
//    val label: String = "",
//    initialChecked: Boolean = false
//) {
//    var checked: Boolean by mutableStateOf(initialChecked)
//}

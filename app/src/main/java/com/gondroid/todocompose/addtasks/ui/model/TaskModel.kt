package com.gondroid.todocompose.addtasks.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    val selected: Boolean = false
)
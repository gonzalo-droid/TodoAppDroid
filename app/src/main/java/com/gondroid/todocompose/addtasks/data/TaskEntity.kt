package com.gondroid.todocompose.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity (
    @PrimaryKey
    val id: Int,
    val task: String,
    val selected: Boolean = false
)
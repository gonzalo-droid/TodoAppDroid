package com.gondroid.todocompose.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TodoDataBase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
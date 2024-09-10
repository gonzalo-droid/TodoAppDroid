package com.gondroid.todocompose.addtasks.data

import com.gondroid.todocompose.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks()
        .map { items ->
            items.map { item ->
                TaskModel(item.id, item.task, item.selected)
            }
        }

    suspend fun delete(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.toData())
    }

    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(taskModel.toData())
    }

    suspend fun update(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toData())
    }
}

fun TaskModel.toData(): TaskEntity = TaskEntity(this.id, this.task, this.selected)

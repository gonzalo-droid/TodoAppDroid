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
        taskDao.deleteTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }

    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }
}
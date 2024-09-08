package com.gondroid.todocompose.addtasks.domain

import com.gondroid.todocompose.addtasks.data.TaskRepository
import com.gondroid.todocompose.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        return taskRepository.delete(taskModel)
    }

}
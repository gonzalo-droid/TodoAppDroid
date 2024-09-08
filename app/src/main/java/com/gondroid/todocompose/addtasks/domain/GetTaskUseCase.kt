package com.gondroid.todocompose.addtasks.domain

import com.gondroid.todocompose.addtasks.data.TaskRepository
import com.gondroid.todocompose.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    operator  fun invoke():Flow<List<TaskModel>>{
        return repository.tasks
    }
}
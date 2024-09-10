package com.gondroid.todocompose.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gondroid.todocompose.addtasks.data.TaskEntity
import com.gondroid.todocompose.addtasks.domain.AddTaskUseCase
import com.gondroid.todocompose.addtasks.domain.DeleteTaskUseCase
import com.gondroid.todocompose.addtasks.domain.GetTaskUseCase
import com.gondroid.todocompose.addtasks.domain.UpdateTaskUseCase
import com.gondroid.todocompose.addtasks.ui.TasksUiState.Error
import com.gondroid.todocompose.addtasks.ui.TasksUiState.Loading
import com.gondroid.todocompose.addtasks.ui.TasksUiState.Success
import com.gondroid.todocompose.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUiState> =
        getTaskUseCase().map { items -> Success(items) } // ::Success
            .catch { Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog


    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(value: String) {
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = value))
        }
        onDialogClose()
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(task: TaskModel) {
//        val index = _tasks.indexOf(task)
//        // asigna todo el objeto cambiando el valor,ya que si solo cambia el valor la vista no lo reconoce
//        _tasks[index] = _tasks[index].let {
//            it.copy(selected = !it.selected)
//        }
        viewModelScope.launch {
            updateTaskUseCase(task.copy(selected = !task.selected))
        }

    }

    fun onItemRemove(task: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
//        val taskModel = _tasks.find { it.id == task.id }
//        _tasks.remove(taskModel)
    }
}
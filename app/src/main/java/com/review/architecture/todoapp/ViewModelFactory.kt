
package com.review.architecture.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.review.architecture.todoapp.addedittask.AddEditTaskViewModel
import com.review.architecture.todoapp.data.source.TasksRepository
import com.review.architecture.todoapp.domain.ActivateTaskUseCase
import com.review.architecture.todoapp.domain.ClearCompletedTasksUseCase
import com.review.architecture.todoapp.domain.CompleteTaskUseCase
import com.review.architecture.todoapp.domain.DeleteTaskUseCase
import com.review.architecture.todoapp.domain.GetTaskUseCase
import com.review.architecture.todoapp.domain.GetTasksUseCase
import com.review.architecture.todoapp.domain.SaveTaskUseCase
import com.review.architecture.todoapp.statistics.StatisticsViewModel
import com.review.architecture.todoapp.taskdetail.TaskDetailViewModel
import com.review.architecture.todoapp.tasks.TasksViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val tasksRepository: TasksRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(StatisticsViewModel::class.java) ->
                        StatisticsViewModel(
                            GetTasksUseCase(tasksRepository)
                        )
                    isAssignableFrom(TaskDetailViewModel::class.java) ->
                        TaskDetailViewModel(
                            GetTaskUseCase(tasksRepository),
                            DeleteTaskUseCase(tasksRepository),
                            CompleteTaskUseCase(tasksRepository),
                            ActivateTaskUseCase(tasksRepository)
                        )
                    isAssignableFrom(AddEditTaskViewModel::class.java) ->
                        AddEditTaskViewModel(
                            GetTaskUseCase(tasksRepository),
                            SaveTaskUseCase(tasksRepository)
                        )
                    isAssignableFrom(TasksViewModel::class.java) ->
                        TasksViewModel(
                            GetTasksUseCase(tasksRepository),
                            ClearCompletedTasksUseCase(tasksRepository),
                            CompleteTaskUseCase(tasksRepository),
                            ActivateTaskUseCase(tasksRepository)
                        )
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}

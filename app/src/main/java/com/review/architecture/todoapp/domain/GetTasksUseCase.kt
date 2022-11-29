package com.review.architecture.todoapp.domain

import com.review.architecture.todoapp.data.Result
import com.review.architecture.todoapp.data.Result.Success
import com.review.architecture.todoapp.data.Task
import com.review.architecture.todoapp.data.source.TasksRepository
import com.review.architecture.todoapp.tasks.TasksFilterType
import com.review.architecture.todoapp.tasks.TasksFilterType.ACTIVE_TASKS
import com.review.architecture.todoapp.tasks.TasksFilterType.ALL_TASKS
import com.review.architecture.todoapp.tasks.TasksFilterType.COMPLETED_TASKS
import com.review.architecture.todoapp.util.wrapEspressoIdlingResource

class GetTasksUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(
        forceUpdate: Boolean = false,
        currentFiltering: TasksFilterType = ALL_TASKS
    ): Result<List<Task>> {
        wrapEspressoIdlingResource {

            val tasksResult = tasksRepository.getTasks(forceUpdate)

            // Filter tasks
            if (tasksResult is Success && currentFiltering != ALL_TASKS) {
                val tasks = tasksResult.data

                val tasksToShow = mutableListOf<Task>()
                // We filter the tasks based on the requestType
                for (task in tasks) {
                    when (currentFiltering) {
                        ACTIVE_TASKS -> if (task.isActive) {
                            tasksToShow.add(task)
                        }
                        COMPLETED_TASKS -> if (task.isCompleted) {
                            tasksToShow.add(task)
                        }
                        else -> NotImplementedError()
                    }
                }
                return Success(tasksToShow)
            }
            return tasksResult
        }
    }

}
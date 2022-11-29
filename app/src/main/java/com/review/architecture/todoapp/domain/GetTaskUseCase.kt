package com.review.architecture.todoapp.domain

import com.review.architecture.todoapp.data.Result
import com.review.architecture.todoapp.data.Task
import com.review.architecture.todoapp.data.source.TasksRepository
import com.review.architecture.todoapp.util.wrapEspressoIdlingResource

class GetTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(taskId: String, forceUpdate: Boolean = false): Result<Task> {

        wrapEspressoIdlingResource {
            return tasksRepository.getTask(taskId, forceUpdate)
        }
    }

}
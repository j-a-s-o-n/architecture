package com.review.architecture.todoapp.domain

import com.review.architecture.todoapp.data.source.TasksRepository
import com.review.architecture.todoapp.util.wrapEspressoIdlingResource

class DeleteTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(taskId: String) {

        wrapEspressoIdlingResource {
            return tasksRepository.deleteTask(taskId)
        }
    }

}
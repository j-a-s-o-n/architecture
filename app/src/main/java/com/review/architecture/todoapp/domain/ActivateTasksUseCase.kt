package com.review.architecture.todoapp.domain

import com.review.architecture.todoapp.data.Task
import com.review.architecture.todoapp.data.source.TasksRepository
import com.review.architecture.todoapp.util.wrapEspressoIdlingResource

class ActivateTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(task: Task) {

        wrapEspressoIdlingResource {
            tasksRepository.activateTask(task)
        }
    }
}
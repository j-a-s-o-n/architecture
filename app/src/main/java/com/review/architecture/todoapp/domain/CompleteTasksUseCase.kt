package com.review.architecture.todoapp.domain

import com.review.architecture.todoapp.data.Task
import com.review.architecture.todoapp.data.source.TasksRepository
import com.review.architecture.todoapp.util.wrapEspressoIdlingResource

class CompleteTaskUseCase(
    private val tasksRepository: TasksRepository
) {
    suspend operator fun invoke(task: Task) {

        wrapEspressoIdlingResource {
            tasksRepository.completeTask(task)
        }
    }
}
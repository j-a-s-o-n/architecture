
package com.review.architecture.todoapp.util

/**
 * Extension functions for Fragment.
 */

import androidx.fragment.app.Fragment
import com.review.architecture.todoapp.TodoApplication
import com.review.architecture.todoapp.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TodoApplication).taskRepository
    return ViewModelFactory(repository)
}

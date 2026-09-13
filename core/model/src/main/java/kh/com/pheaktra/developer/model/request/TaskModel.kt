package kh.com.pheaktra.developer.model.request

import androidx.compose.runtime.Immutable

@Immutable
data class TaskModel(
    val taskId: Int = 0,
    val title: String,
    val description: String,
    val completedYN: Boolean = false
)

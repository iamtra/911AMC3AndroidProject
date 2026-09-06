package kh.com.pheaktra.developer.model.request

data class TaskModel(
    val taskId: Int = 0,
    val title: String,
    val description: String,
    val completedYN: Boolean = false
)

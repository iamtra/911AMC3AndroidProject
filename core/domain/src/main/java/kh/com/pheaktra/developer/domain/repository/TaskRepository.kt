package kh.com.pheaktra.developer.domain.repository

import kh.com.pheaktra.developer.model.request.TaskModel

interface TaskRepository {
    suspend fun getAllTasks(): List<TaskModel>

    suspend fun getTaskById(taskId: Int): TaskModel?

    suspend fun deleteTaskById(taskId: Int)

    suspend fun searchTasks(query: String): List<TaskModel>

    suspend fun createTask(task: TaskModel)

    suspend fun updateTask(task: TaskModel)
}
package kh.com.pheaktra.developer.domain.repository

import kh.com.pheaktra.developer.model.request.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskModel>>

    suspend fun getTaskById(taskId: Int): TaskModel?

    suspend fun deleteTaskById(taskId: Int)

    fun searchTasks(query: String): Flow<List<TaskModel>>

    suspend fun createTask(task: TaskModel)

    suspend fun updateTask(task: TaskModel)
}
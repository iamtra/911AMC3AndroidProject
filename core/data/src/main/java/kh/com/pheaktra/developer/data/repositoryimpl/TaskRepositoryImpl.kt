package kh.com.pheaktra.developer.data.repositoryimpl

import kh.com.pheaktra.developer.data.local.dao.TaskDao
import kh.com.pheaktra.developer.data.local.entities.toTaskEntity
import kh.com.pheaktra.developer.data.local.entities.toTaskModel
import kh.com.pheaktra.developer.data.local.entities.toTaskModelList
import kh.com.pheaktra.developer.domain.repository.TaskRepository
import kh.com.pheaktra.developer.model.request.TaskModel
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override suspend fun getAllTasks(): List<TaskModel> {
        return taskDao.getAllTasks().toTaskModelList()
    }

    override suspend fun getTaskById(taskId: Int): TaskModel? {
        return taskDao.getTaskById(taskId)?.toTaskModel()
    }

    override suspend fun deleteTaskById(taskId: Int) {
        taskDao.deleteTaskById(taskId)
    }

    override suspend fun searchTasks(query: String): List<TaskModel> {
        return taskDao.searchTasks(query).toTaskModelList()
    }

    override suspend fun createTask(task: TaskModel) {
        taskDao.createTask(task = task.toTaskEntity())
    }

    override suspend fun updateTask(task: TaskModel) {
        taskDao.updateTask(task = task.toTaskEntity())
    }
}
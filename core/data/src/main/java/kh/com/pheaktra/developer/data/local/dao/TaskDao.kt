package kh.com.pheaktra.developer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kh.com.pheaktra.developer.data.local.entities.TaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: Int): TaskEntity?

    @Query("DELETE FROM tasks WHERE taskId = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    @Query(
        """
        SELECT * FROM tasks
        WHERE title LIKE '%' || :query || '%'
           OR description LIKE '%' || :query || '%'
        """
    )
    fun searchTasks(query: String): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun createTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)
}

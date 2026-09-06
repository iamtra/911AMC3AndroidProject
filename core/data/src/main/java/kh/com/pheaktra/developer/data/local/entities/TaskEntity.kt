package kh.com.pheaktra.developer.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kh.com.pheaktra.developer.model.request.TaskModel


@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "completed_yn")
    val completedYN: Boolean = false
)

fun TaskModel.toTaskEntity(): TaskEntity {
    return TaskEntity(
        taskId = taskId,
        title = title,
        description = description,
        completedYN = completedYN
    )
}

fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        taskId = taskId,
        title = title,
        description = description,
        completedYN = completedYN
    )
}

fun List<TaskEntity>.toTaskModelList(): List<TaskModel> {
    return map { it.toTaskModel() }
}

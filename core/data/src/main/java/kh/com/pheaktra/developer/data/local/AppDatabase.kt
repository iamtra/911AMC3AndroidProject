package kh.com.pheaktra.developer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kh.com.pheaktra.developer.data.local.dao.TaskDao
import kh.com.pheaktra.developer.data.local.entities.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        // Add other entities here...
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
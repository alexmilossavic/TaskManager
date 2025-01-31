package de.syntax_institut.taskmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todos")
    fun getAllToDos(): Flow<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todo: ToDo)

    @Delete
    suspend fun deleteToDo(todo: ToDo)

    @Update
    suspend fun updateToDo(todo: ToDo)
}


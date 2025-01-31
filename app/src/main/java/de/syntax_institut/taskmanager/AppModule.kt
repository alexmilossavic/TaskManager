package de.syntax_institut.taskmanager

import android.content.Context
import androidx.room.Room
import de.syntax_institut.taskmanager.data.ToDoRepository

import kotlinx.coroutines.flow.Flow


object AppModule {


    fun provideDatabase(context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }


    fun provideToDoRepository(database: ToDoDatabase): ToDoRepository {
        return ToDoRepository(database.toDoDao())
    }


    fun provideSortPreferenceRepository(context: Context): SortPreferenceRepository {
        return SortPreferenceRepository(context)
    }
}

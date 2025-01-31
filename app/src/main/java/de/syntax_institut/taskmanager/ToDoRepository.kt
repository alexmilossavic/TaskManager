package de.syntax_institut.taskmanager.data

import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val toDoDao: ToDoDao) {

    val allToDos: Flow<List<ToDo>> = toDoDao.getAllToDos()

    suspend fun insertToDo(todo: ToDo) {
        toDoDao.insertToDo(todo)
    }

    suspend fun deleteToDo(todo: ToDo) {
        toDoDao.deleteToDo(todo)
    }

    suspend fun updateToDo(todo: ToDo) {
        toDoDao.updateToDo(todo)
    }
}

package de.syntax_institut.taskmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.taskmanager.data.ToDo
import de.syntax_institut.taskmanager.data.ToDoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {

    private val _isSorted = MutableStateFlow(false)
    val isSorted: StateFlow<Boolean> = _isSorted.asStateFlow()

    val tasks: StateFlow<List<ToDo>> = repository.allToDos
        .combine(_isSorted) { taskList, isSorted ->
            if (isSorted) taskList.sortedBy { it.title } else taskList
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleSortOrder() {
        viewModelScope.launch {
            _isSorted.value = !_isSorted.value
        }
    }

    fun addToDo(title: String) {
        viewModelScope.launch {
            repository.insertToDo(ToDo(title = title))
        }
    }

    fun deleteToDo(todo: ToDo) {
        viewModelScope.launch {
            repository.deleteToDo(todo)
        }
    }

    fun toggleToDoStatus(todo: ToDo) {
        viewModelScope.launch {
            val updatedToDo = todo.copy(isDone = !todo.isDone)
            repository.updateToDo(updatedToDo)
        }
    }
}

/** ✅ Fake ViewModel für Preview */
class FakeToDoViewModel : ViewModel() {
    val tasks = MutableStateFlow(
        listOf(
            ToDo(id = 1, title = "Milch kaufen", isDone = false),
            ToDo(id = 2, title = "Workout machen", isDone = true),
            ToDo(id = 3, title = "Programmieren", isDone = false)
        )
    )
    val isSorted = MutableStateFlow(false)

    fun toggleSortOrder() {
        isSorted.value = !isSorted.value
    }

    fun toggleToDoStatus(todo: ToDo) { }
    fun deleteToDo(todo: ToDo) { }
}


package de.syntax_institut.taskmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SortPreferenceRepository(application)

    private val _tasks = MutableStateFlow(
        listOf(
            "Einkaufen gehen",
            "E-Mails beantworten",
            "Meeting vorbereiten",
            "Blumen gießen",
            "Aufräumen"
        )
    )

    val tasks: StateFlow<List<String>> = repository.sortOrderFlow
        .map { isSorted ->
            if (isSorted) _tasks.value.sorted() else _tasks.value
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, _tasks.value)

    private val _isSorted = MutableStateFlow(false)
    val isSorted: StateFlow<Boolean> = _isSorted.asStateFlow()

    init {
        viewModelScope.launch {
            repository.sortOrderFlow.collect { isSorted ->
                _isSorted.value = isSorted
            }
        }
    }

    fun toggleSortOrder() {
        viewModelScope.launch {
            val newOrder = !_isSorted.value
            repository.saveSortOrder(newOrder)
            _isSorted.value = newOrder
        }
    }
}

package de.syntax_institut.taskmanager

import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    val tasks = listOf(
        "Einkaufen gehen",
        "E-Mails beantworten",
        "Meeting vorbereiten",
        "Blumen gießen",
        "Aufräumen"
    )
}

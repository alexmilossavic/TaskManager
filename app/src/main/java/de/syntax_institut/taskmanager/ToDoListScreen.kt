package de.syntax_institut.taskmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.taskmanager.ToDoViewModel
import de.syntax_institut.taskmanager.data.ToDo

@Composable
fun ToDoListScreen(viewModel: ToDoViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    val isSorted by viewModel.isSorted.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = if (isSorted) "Alphabetisch sortieren" else "Standard sortieren",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Sortierung:")
            Switch(
                checked = isSorted,
                onCheckedChange = { viewModel.toggleSortOrder() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onToggleStatus = { viewModel.toggleToDoStatus(task) },
                    onDelete = { viewModel.deleteToDo(task) }
                )
            }
        }
    }
}


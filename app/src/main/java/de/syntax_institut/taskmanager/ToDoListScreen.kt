package de.syntax_institut.taskmanager.ui
import de.syntax_institut.taskmanager.ToDoViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.taskmanager.TaskItem


@Composable
fun ToDoListScreen(viewModel: ToDoViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    val isSorted by viewModel.isSorted.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = if (isSorted) "Alphabetisch sortieren" else "Standard sortieren")
            Switch(
                checked = isSorted,
                onCheckedChange = { viewModel.toggleSortOrder() }
            )
        }

        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}

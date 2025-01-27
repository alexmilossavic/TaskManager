package de.syntax_institut.taskmanager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ToDoListScreen(viewModel: ToDoViewModel = viewModel()) {
    val tasks = viewModel.tasks

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewToDoListScreen() {
    ToDoListScreen()
}

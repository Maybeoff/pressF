package ru.mby.myapplication.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.mby.myapplication.data.AppItem
import ru.mby.myapplication.data.SampleAppsRepository

@Composable
fun HomeScreen(onOpenDetails: (String) -> Unit) {
    val vm: HomeViewModel = viewModel()
    val state by vm.state.collectAsState()
    val queryState: MutableState<String> = remember { mutableStateOf("") }
    val filtered: List<AppItem> = state.apps.filter { it.name.contains(queryState.value, ignoreCase = true) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = queryState.value,
            onValueChange = { queryState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Поиск приложений") }
        )
        if (state.isLoading) {
            Text("Загрузка...")
        }
        if (state.error != null) {
            Text("Ошибка: ${state.error}")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 12.dp)
        ) {
            items(filtered, key = { it.id }) { app ->
                AppRow(app = app, onClick = { onOpenDetails(app.id) })
            }
        }
    }
}

@Composable
private fun AppRow(app: AppItem, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)
        .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(app.name, style = MaterialTheme.typography.titleMedium)
            Text(app.developer, style = MaterialTheme.typography.bodyMedium)
            Text(
                "★ ${app.rating} • ${app.downloads} скачиваний",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                app.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



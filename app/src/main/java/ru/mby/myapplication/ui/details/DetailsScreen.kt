package ru.mby.myapplication.ui.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.mby.myapplication.data.AppItem
import ru.mby.myapplication.data.InstalledVersionsStore
import ru.mby.myapplication.data.SampleAppsRepository

@Composable
fun DetailsScreen(appId: String) {
    val context = LocalContext.current
    val store = InstalledVersionsStore(context)
    val app: AppItem? = SampleAppsRepository.getById(appId)
    val installed = store.getInstalledVersion(appId)
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (app == null) {
            Text("Приложение не найдено", style = MaterialTheme.typography.titleMedium)
            return@Column
        }
        val hasUpdate = installed != null && installed != app.version
        Text(app.name, style = MaterialTheme.typography.headlineSmall)
        Text("Версия: ${app.version}", style = MaterialTheme.typography.bodyMedium)
        Text(
            if (installed == null) "Не установлено" else "Установлено: ${installed}" + (if (hasUpdate) " • Доступно обновление" else ""),
            style = MaterialTheme.typography.bodyMedium
        )
        Text("Разработчик: ${app.developer}", style = MaterialTheme.typography.bodyMedium)
        Text("Рейтинг: ${app.rating}", style = MaterialTheme.typography.bodyMedium)
        Text("Скачивания: ${app.downloads}", style = MaterialTheme.typography.bodyMedium)
        Text(app.description, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 12.dp))

        Button(enabled = app.downloadUrl != null, onClick = {
            app.downloadUrl?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        }, modifier = Modifier.padding(top = 12.dp)) {
            Text("Скачать")
        }

        Button(onClick = {
            store.setInstalledVersion(appId, app.version)
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Отметить как обновлён")
        }
    }
}



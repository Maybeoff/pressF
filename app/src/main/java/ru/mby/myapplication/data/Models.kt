package ru.mby.myapplication.data

data class AppItem(
    val id: String,
    val name: String,
    val developer: String,
    val rating: Double,
    val downloads: Long,
    val description: String,
    val version: String = "0.0.0",
    val iconUrl: String? = null,
    val downloadUrl: String? = null,
)

object SampleAppsRepository {
    val apps: List<AppItem> = listOf(
        AppItem(
            id = "1",
            name = "Pressf Notes",
            developer = "Pressf Studio",
            rating = 4.6,
            downloads = 120_000,
            description = "Быстрые заметки с офлайн-доступом и синхронизацией.",
            version = "1.0.0",
            iconUrl = null,
            downloadUrl = null,
        ),
        AppItem(
            id = "2",
            name = "Pressf Player",
            developer = "Pressf Media",
            rating = 4.3,
            downloads = 310_500,
            description = "Музыкальный плеер с эквалайзером и кэшированием треков.",
            version = "1.2.0",
            iconUrl = null,
            downloadUrl = null,
        ),
        AppItem(
            id = "3",
            name = "Pressf Tasks",
            developer = "Pressf Studio",
            rating = 4.8,
            downloads = 89_000,
            description = "Список дел с приоритетами, напоминаниями и виджетами.",
            version = "0.9.5",
            iconUrl = null,
            downloadUrl = null,
        ),
    )

    fun getById(id: String): AppItem? = apps.find { it.id == id }
}



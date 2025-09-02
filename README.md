# Pressf — Магазин приложений (Android, Compose)

Возможности:
- Загрузка каталога приложений из GitHub (JSON)
- Поиск, список, экран деталей
- Скачивание по ссылке `downloadUrl`
- Проверка версии: сравнение установленной версии со свежей из JSON

Настройка:
1. В `app/src/main/java/ru/mby/myapplication/data/RemoteRepository.kt` установите `CATALOG_URL` на ваш raw JSON.
2. (Опционально, приватный репозиторий) Добавьте GitHub PAT в `local.properties`:
   - `GITHUB_TOKEN=ghp_xxx`
   И объявите в `app/build.gradle.kts`:
   ```kotlin
   defaultConfig {
       buildConfigField("String", "GITHUB_TOKEN", "\"${project.findProperty("GITHUB_TOKEN") ?: ""}\"")
   }
   ```

Формат JSON:
```json
[
  {
    "id": "notes",
    "name": "Pressf Notes",
    "developer": "Pressf Studio",
    "rating": 4.6,
    "downloads": 120000,
    "description": "Описание...",
    "version": "1.0.0",
    "iconUrl": "https://.../icon.png",
    "downloadUrl": "https://github.com/.../releases/download/v1.0.0/app.apk"
  }
]
```

Лицензия: Apache-2.0 — см. файл LICENSE.

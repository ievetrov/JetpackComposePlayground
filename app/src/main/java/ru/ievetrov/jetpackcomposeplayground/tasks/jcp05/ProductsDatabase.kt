package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * JCP-05: Практика написания тестов — Room база данных
 *
 * В интеграционном тесте создаётся in-memory версия этой базы:
 *
 * database = Room.inMemoryDatabaseBuilder(
 *     context,
 *     ProductsDatabase::class.java
 * ).allowMainThreadQueries().build()
 *
 * dao = database.productDao()
 *
 * In-memory база не сохраняется между тестами — каждый тест
 * начинает с чистого состояния.
 */
@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}

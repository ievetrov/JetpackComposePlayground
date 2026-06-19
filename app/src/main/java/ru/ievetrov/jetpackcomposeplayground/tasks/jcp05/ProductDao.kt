package ru.ievetrov.jetpackcomposeplayground.tasks.jcp05

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * JCP-05: Практика написания тестов — Room DAO продуктов
 *
 * Используется в интеграционном тесте:
 * - insertAll() вызывается репозиторием после успешной загрузки из сети
 * - getAllProducts() — проверяем в тесте, что данные сохранились
 * - clearAll() вызывается из repository.clearCache()
 *
 * В тесте ProductsIntegrationTest:
 * - database = Room.inMemoryDatabaseBuilder(context, ProductsDatabase::class.java).build()
 * - dao = database.productDao()
 */
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("DELETE FROM products")
    suspend fun clearAll()
}

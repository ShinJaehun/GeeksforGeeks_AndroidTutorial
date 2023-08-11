package com.shinjaehun.groceryapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItem)

    @Delete
    suspend fun delete(item: GroceryItem)

    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItems(): LiveData<List<GroceryItem>>
}
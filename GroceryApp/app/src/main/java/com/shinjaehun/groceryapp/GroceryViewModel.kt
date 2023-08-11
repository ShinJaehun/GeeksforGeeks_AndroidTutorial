package com.shinjaehun.groceryapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    fun insert(item: GroceryItem) = GlobalScope.launch {
        repository.insert(item)
    }

    fun delete(item: GroceryItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllGroceryItems() = repository.getAllItems()
}
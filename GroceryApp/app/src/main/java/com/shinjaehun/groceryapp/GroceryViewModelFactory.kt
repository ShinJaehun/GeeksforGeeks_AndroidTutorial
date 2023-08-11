package com.shinjaehun.groceryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroceryViewModelFactory(private val repository: GroceryRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return super.create(modelClass)
        return GroceryViewModel(repository) as T
    }
}
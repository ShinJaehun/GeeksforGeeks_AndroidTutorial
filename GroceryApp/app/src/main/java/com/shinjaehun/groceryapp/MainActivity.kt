package com.shinjaehun.groceryapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shinjaehun.groceryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GroceryRVAdapter.GroceryItemClickInterface {

    private lateinit var binding: ActivityMainBinding

    private lateinit var list: List<GroceryItem>
    private lateinit var groceryRVAdapter: GroceryRVAdapter
    private lateinit var groceryViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<GroceryItem>()
        groceryRVAdapter = GroceryRVAdapter(list, this)

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = groceryRVAdapter

        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)
        groceryViewModel.getAllGroceryItems().observe(this, Observer {
            groceryRVAdapter.list = it
            groceryRVAdapter.notifyDataSetChanged()
        })

        binding.fabAdd.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)
        val cancelButton = dialog.findViewById<Button>(R.id.btnCancel)
        val addButton = dialog.findViewById<Button>(R.id.btnAdd)
        val itemNameEdt = dialog.findViewById<EditText>(R.id.etItemName)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.etItemPrice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.etItemQuantity)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        addButton.setOnClickListener {
            val itemName: String = itemNameEdt.text.toString()
            val itemPrice: String = itemPriceEdt.text.toString()
            val itemQuantity: String = itemQuantityEdt.text.toString()
            val qty : Int = itemQuantity.toInt()
            val pr : Int = itemPrice.toInt()
            if (itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()) {
                val item = GroceryItem(itemName, qty, pr)
                groceryViewModel.insert(item)
                Toast.makeText(applicationContext, "Item inserted", Toast.LENGTH_SHORT).show()
                groceryRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(applicationContext, "the item should not be null", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }


    override fun onItemClick(groceryItem: GroceryItem) {
        groceryViewModel.delete(groceryItem)
        groceryRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted", Toast.LENGTH_SHORT).show()
    }
}
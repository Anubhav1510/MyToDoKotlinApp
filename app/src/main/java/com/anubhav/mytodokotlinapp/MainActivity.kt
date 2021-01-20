package com.anubhav.mytodokotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = arrayListOf<String>()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemList)

        // Adding the items to the list
        btnAdd.setOnClickListener {
            itemList.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        // Clearing all the items from the list
        btnClear.setOnClickListener {
            itemList.clear()
            adapter.notifyDataSetChanged()
        }

        // Toast message on item click
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "You Selected the item --> " + itemList[i],
                    android.widget.Toast.LENGTH_SHORT).show()
        }

        // Selecting and Deleting the items from the list
        btnDelete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemList.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}
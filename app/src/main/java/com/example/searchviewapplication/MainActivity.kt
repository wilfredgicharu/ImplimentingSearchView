package com.example.searchviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: itemAdapter
    private val mItemList : ArrayList<ItemData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initItem()

        mItemList.shuffle()

        val list = findViewById<RecyclerView>(R.id.list)

        mAdapter = itemAdapter(mItemList)
        list.adapter = mAdapter

    }

    private fun initItem() {
        //assigning values
        mItemList.add(ItemData("Mercedes Benz", "Germany"))
        mItemList.add(ItemData("Volkswagen", "Germany"))
        mItemList.add(ItemData("Maybach", "Germany"))
        mItemList.add(ItemData("BMW", "Germany"))
        mItemList.add(ItemData("Audi", "Germany"))
        mItemList.add(ItemData("Smart", "Germany"))
        mItemList.add(ItemData("Porsche", "Germany"))
        mItemList.add(ItemData("Opel", "Germany"))
        mItemList.add(ItemData("Cadillac", "United States"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        
        val menuItem = menu!!.findItem(R.id.search)
        val searchView : SearchView = menuItem.actionView as SearchView
        perfomSearch(searchView)
        
        return super.onCreateOptionsMenu(menu)
    }

    private fun perfomSearch(searchView: SearchView) {
         searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(p0: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(p0: String?): Boolean {
                 mAdapter.filter.filter(p0)

                 return true
             }

         })
    }
}
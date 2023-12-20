package com.degin90.test62

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.degin90.test62.adapter.searchItemListAdapter
import com.degin90.test62.api.ApiUtils
import com.degin90.test62.databinding.ActivityMainBinding
import com.degin90.test62.model.Business
import com.degin90.test62.model.modelSearchRespond
import retrofit2.Call


val apiKey          = "Ubf1-f0uqsJUnssqPMGo-tiFeZTT85oFmKfznlPmjDtX8s83jYMoAb-ApuD63wgq6LDZNsUXG6gurZIVYaj2jzxJmmLdCdXbDqIHU_b6KiCEVi8v-YB0OSsW6MWaY3Yx"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var listResult      = mutableListOf<Business>()
    var queryToSearch   = ""
    private val adapterList     = searchItemListAdapter(listResult, object :
        searchItemListAdapter.OnItemClickListener {
        override fun onItemClick(action : String, item: Business?) {
            val postPage = Intent(this@MainActivity, DetailPage::class.java)
            postPage.putExtra("alias",item?.alias)
            postPage.putExtra("name",item?.name)
            startActivity(postPage)
        }
    })

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rcList = findViewById<RecyclerView>(R.id.resultList)
        rcList.layoutManager = LinearLayoutManager(this)
        rcList.adapter = adapterList

        binding.searchBtn.setOnClickListener {
            queryToSearch = binding.searchValue.text.toString()

            val inputMethodManager: InputMethodManager = this.getSystemService(
                INPUT_METHOD_SERVICE
            ) as InputMethodManager
            if (inputMethodManager.isAcceptingText) {
                inputMethodManager.hideSoftInputFromWindow(
                    this.currentFocus?.windowToken,
                    0
                )
            }

            listResult.clear()
            adapterList.notifyDataSetChanged()
            fetchData()
        }

        fetchData()
    }

    private fun fetchData() {
        val location = "New York"
        val authorization = "Bearer $apiKey"
        val apiProses = ApiUtils.ApiServices?.businessesSearch(
            authorization,
            location,
            queryToSearch
        )
        apiProses?.enqueue(object : retrofit2.Callback<modelSearchRespond> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<modelSearchRespond>,
                response: retrofit2.Response<modelSearchRespond>
            ) {
                val res = response.body()
                res?.businesses?.forEach {
                    listResult.add(it)
                }
                adapterList.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<modelSearchRespond>, t: Throwable) {
                Log.w("xxx","Error $t")
            }
        })
    }

}


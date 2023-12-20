package com.degin90.test62

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.degin90.test62.adapter.reviewItemListAdapter
import com.degin90.test62.api.ApiUtils
import com.degin90.test62.databinding.ActivityDetailBinding
import com.degin90.test62.model.Business
import com.degin90.test62.model.Review
import com.degin90.test62.model.reviewResponds
import retrofit2.Call


class DetailPage : AppCompatActivity () {
    private lateinit var binding: ActivityDetailBinding
    private var listReview      = mutableListOf<Review>()
    private var aliasPost = ""
    private val authorization = "Bearer $apiKey"
    private val adapterReview    = reviewItemListAdapter(listReview, object :
        reviewItemListAdapter.OnItemClickListener {
        override fun onItemClick(action : String, item: Review?) {
            //Todo
        }
    })

    private var gmapLat : String = ""
    private var gmapLng : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.title = intent.getStringExtra("name")
        aliasPost = intent.getStringExtra("alias").toString()


        val rcReview = findViewById<RecyclerView>(R.id.reviewRc)
        rcReview.layoutManager = LinearLayoutManager(this)
        rcReview.adapter = adapterReview




        val reviewProses = ApiUtils.ApiServices?.getBusines(authorization,aliasPost)
        reviewProses?.enqueue(object : retrofit2.Callback<Business> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<Business>,
                response: retrofit2.Response<Business>
            ) {
                val it = response.body()

                Glide.with(this@DetailPage)
                    .load(it!!.imageUrl)
                    .centerCrop()
                    .into(binding.postImage)

                aliasPost = it.alias.toString()
                binding.postName.text = it.name
                binding.postPhone.text = it.displayPhone
                binding.postAddr.text = it.location?.displayAddress?.joinToString(",")

                if(it.isClosed!!){
                    binding.buttonStatus.setBackgroundResource(R.color.reds)
                    binding.postStatus.text = "Closed"
                }else{
                    binding.buttonStatus.setBackgroundResource(R.color.green)
                    binding.postStatus.text = "Open"
                }

                if (it.rating?.toInt() == 1){
                    binding.ratingStar1.visibility = View.VISIBLE
                }else if (it.rating?.toInt() == 2){
                    binding.ratingStar1.visibility = View.VISIBLE
                    binding.ratingStar2.visibility = View.VISIBLE
                }else if (it.rating?.toInt() == 3){
                    binding.ratingStar1.visibility = View.VISIBLE
                    binding.ratingStar2.visibility = View.VISIBLE
                    binding.ratingStar3.visibility = View.VISIBLE
                }else if (it.rating?.toInt() == 4){
                    binding.ratingStar1.visibility = View.VISIBLE
                    binding.ratingStar2.visibility = View.VISIBLE
                    binding.ratingStar3.visibility = View.VISIBLE
                    binding.ratingStar4.visibility = View.VISIBLE
                }else if (it.rating?.toInt() == 5){
                    binding.ratingStar1.visibility = View.VISIBLE
                    binding.ratingStar2.visibility = View.VISIBLE
                    binding.ratingStar3.visibility = View.VISIBLE
                    binding.ratingStar4.visibility = View.VISIBLE
                    binding.ratingStar5.visibility = View.VISIBLE
                }

                gmapLat = it.coordinates?.latitude?.toString()!!
                gmapLng = it.coordinates?.longitude?.toString()!!
                fetchReview()
            }
            override fun onFailure(call: Call<Business>, t: Throwable) {
                Log.w("xxx","Error $t")
            }
        })

        binding.mapsButton.setOnClickListener {
            if(gmapLat.isNotEmpty()) {
                val strUri =
                    "http://maps.google.com/maps?q=loc:$gmapLat,$gmapLng"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
                intent.setClassName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"
                )
                startActivity(intent)
            }
        }
    }



    private fun fetchReview() {
        val reviewProses = ApiUtils.ApiServices?.getReview(
            authorization,
            aliasPost,
            20,
            "yelp_sort"
        )
        reviewProses?.enqueue(object : retrofit2.Callback<reviewResponds> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<reviewResponds>,
                response: retrofit2.Response<reviewResponds>
            ) {
                val res = response.body()
                res?.reviews?.forEach {
                    listReview.add(it)
                }
                adapterReview.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<reviewResponds>, t: Throwable) {
                Log.w("xxx","Error $t")
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


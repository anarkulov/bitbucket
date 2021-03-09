package com.erzhan.api.main.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.erzhan.api.R
import com.erzhan.api.adapter.ApiAdapter
import com.erzhan.api.data.DataModel
import com.erzhan.api.main.MyInterface
import com.erzhan.api.main.MyPresenter
import kotlin.collections.ArrayList

class JokeActivity : AppCompatActivity(),  MyInterface.DataView {
    private lateinit var networkRequestQueue: RequestQueue
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshButton: Button

    private lateinit var presenter: MyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        networkRequestQueue = Volley.newRequestQueue(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        presenter = MyPresenter(this, networkRequestQueue)

        recyclerView = findViewById(R.id.recyclerViewId)
        refreshButton = findViewById(R.id.refreshButtonId)
        refreshButton.setOnClickListener{
            initView()
        }
        initView()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        presenter.getData()
    }

    override fun getDataFromPresenter(value: ArrayList<DataModel>) {
        val myAdapter = ApiAdapter(value)
        recyclerView.adapter = myAdapter
    }
}
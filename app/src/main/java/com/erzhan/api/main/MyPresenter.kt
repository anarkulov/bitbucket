package com.erzhan.api.main

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.erzhan.api.data.DataModel
import com.android.volley.RequestQueue
import org.json.JSONException
import org.json.JSONObject

class MyPresenter(view: MyInterface.DataView, var networkRequestQueue: RequestQueue) : MyInterface.Presenter {
    private var dataView: MyInterface.DataView = view
    private lateinit var model: DataModel
    private val list = ArrayList<DataModel>()

    init {
        loadData()
    }

    private fun loadData() {
        val url = "https://official-joke-api.appspot.com/random_ten"
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                var items: JSONObject
                for (i in 0 until response.length()) {

                    try {
                        items = response.getJSONObject(i)

                        val id = items.getInt("id")
                        val type = items.getString("type")
                        val setup = items.getString("setup")
                        val punchline = items.getString("punchline")

                        model = DataModel(id, type, setup, punchline)
                        model.addValue()

                        list.add(model)

                    } catch (e: JSONException) {
                        reportError("Failed to load data. $e")
                    }
                }
            },
            {
                reportError("Failed to load data.")
            }
        )
        networkRequestQueue.add(jsonObjectRequest)
    }

    override fun getData() {
        loadData()
        dataView.getDataFromPresenter(list)
    }

    private fun reportError(message: String) {
        println(message)
    }
}
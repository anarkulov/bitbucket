package com.erzhan.api

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class JokesActivity : AppCompatActivity() {
    private lateinit var networkRequestQueue: RequestQueue
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        networkRequestQueue = Volley.newRequestQueue(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        recyclerView = findViewById(R.id.recyclerViewId)

        loadData()
    }

    private fun loadData() {
        val url = "https://official-joke-api.appspot.com/jokes/ten"
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                val list = ArrayList<Data>()
                var items : JSONObject
                for (i in 0 until response.length()){

                    try {
                        items = response.getJSONObject(i)

                        val id = items.getInt("id")
                        val type = items.getString("type")
                        val setup = items.getString("setup")
                        val punchline = items.getString("punchline")

                        val item = Data(id, type, setup, punchline)
                        list.add(item)

                    } catch (e: JSONException) {
                        reportError(e.toString())
                    }
                }
                recyclerView.adapter = ApiAdapter(list)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.setHasFixedSize(true)
            },
            {
                reportError("Failed to load data.")
            }
        )
        networkRequestQueue.add(jsonObjectRequest)
    }

    private fun reportError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
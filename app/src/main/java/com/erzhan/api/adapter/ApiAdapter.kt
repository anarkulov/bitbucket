package com.erzhan.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erzhan.api.data.Data
import com.erzhan.api.R

class ApiAdapter(private val itemList: List<Data>) : RecyclerView.Adapter<ApiAdapter.ApiViewHolder>(){

    class ApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView1 : TextView = itemView.findViewById(R.id.textViewItem1Id)
        val textView2 : TextView = itemView.findViewById(R.id.textViewItem2Id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item, parent, false)

        return ApiViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val item = itemList[position]

        holder.textView1.text = item.setup
        holder.textView2.text = item.punchline
    }

    override fun getItemCount(): Int{
        return itemList.size
    }
}
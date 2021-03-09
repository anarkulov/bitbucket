package com.erzhan.api.data

import com.erzhan.api.main.MyInterface

class DataModel(
    var id: Int,
    var type: String,
    var setup: String,
    var punchline: String
    ) : MyInterface.ModelInterface
{
    private var list = ArrayList<DataModel>()

    override fun addValue() {
        list.add(DataModel(id, type, setup, punchline))
    }

    override fun getValue(): ArrayList<DataModel> {
        return list
    }
}

//id": 377,
//    "type": "programming",
//    "setup": "Knock-knock.",
//    "punchline": "A race condition. Who is there?"
package com.erzhan.api.main

import com.erzhan.api.data.DataModel

interface MyInterface {
    interface DataView {
        fun getData(value: ArrayList<DataModel>)
    }

    interface Presenter {
        fun loadData()
    }

    interface ModelInterface {
        fun addValue()
        fun getValue(): ArrayList<DataModel>
    }
}
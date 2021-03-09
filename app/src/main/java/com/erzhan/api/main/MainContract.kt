package com.erzhan.api.main

import com.erzhan.api.data.DataModel

interface MainContract {
    interface DataView {
        fun getDataFromPresenter(value: ArrayList<DataModel>)
    }

    interface Presenter {
        fun getData()
    }

    interface ModelInterface {
        fun addValue()
        fun getValue(): ArrayList<DataModel>
    }
}
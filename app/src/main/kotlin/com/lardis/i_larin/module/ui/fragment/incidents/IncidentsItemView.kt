package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.repository.TestModel
import kotlinx.android.synthetic.main.incidents_fragment_item.view.*

class IncidentsItemView(value: TestModel) : ItemView<TestModel>(value, {}) {
    override fun getLayoutId() = R.layout.incidents_fragment_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {
            incidents_fragment_item_edit_text.text = value.name
        }
    }
}
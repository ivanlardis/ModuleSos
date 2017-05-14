package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import kotlinx.android.synthetic.main.incidents_activity_add_coments_item.view.*

class IncidentAddComentsItemView(val onClickListen: (String) -> Unit?) : ItemView<Int?>(null, {}) {
    override fun getLayoutId() = R.layout.incidents_activity_add_coments_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {
            incidents_activity_add_coments_item_button.callback = { onClickListen.invoke(it) }
        }
    }
}
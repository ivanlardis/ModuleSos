package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.model.FBModel
import kotlinx.android.synthetic.main.incidents_activity_button_item.view.*

class IncidentButtonItemView(value: FBModel,val textButton:String, val onClickListen: (FBModel) -> Unit?) : ItemView<FBModel>(value, {}) {
    override fun getLayoutId() = R.layout.incidents_activity_button_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {
            incidents_activity_completed_item_button.text=textButton
            incidents_activity_completed_item_button.setOnClickListener {
                onClickListen.invoke(value)
            }
        }
    }
}
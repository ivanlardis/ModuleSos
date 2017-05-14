package com.example.black_sony.testrecyclerview.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.GroupTitleItemView
import com.lardis.i_larin.module.R
import kotlinx.android.synthetic.main.incidents_activity_button_item.view.*


class IncidentTitleItemView(value: String) : GroupTitleItemView<String>(value, {}) {
    override fun hideOnEmptyGroup(): Boolean = false

    override fun getLayoutId(): Int {

        return R.layout.incidents_activity_title_item; }


    override fun bind(view: View) {
        super.bind(view)

        with(view)
        {
            incidents_activity_completed_item_button.text = value
            incidents_activity_completed_item_button.setOnClickListener {
                expand = !expand
                observer?.onChanged()

            }
        }


    }
}
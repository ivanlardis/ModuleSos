package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.model.FBModel
import kotlinx.android.synthetic.main.incidents_activity_complete_item.view.*

class IncidentCompleteItemView(value: FBModel, onClickListener: (ItemView<FBModel>) -> Unit?) : ItemView<FBModel>(value, onClickListener) {
    override fun getLayoutId() = R.layout.incidents_activity_complete_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {

            if (value.compeleteTime > 0) {
                incidents_activity_complete_item_image.setImageResource(R.drawable.ic_sentiment_satisfied_black_48dp)
                incidents_activity_complete_item_text_view_intident.visibility = View.VISIBLE

            } else {
                incidents_activity_complete_item_image.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_48dp)
                incidents_activity_complete_item_text_view_intident.visibility = View.GONE

            }

            incidents_activity_complete_item_text_view_time.text = DATE_FORMAT.format(value.createTime)
            incidents_activity_complete_item_text_view_description.text = value.intident
        }
    }
}
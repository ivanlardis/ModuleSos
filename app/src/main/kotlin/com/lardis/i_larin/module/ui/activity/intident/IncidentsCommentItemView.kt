package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.ui.activity.intident.ModelComment
import com.lardis.i_larin.module.ui.widget.ui.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.incidents_factivity_comment_item.view.*

class IncidentsCommentItemView(value: ModelComment) : ItemView<ModelComment>(value, {}) {
    override fun getLayoutId() = R.layout.incidents_factivity_comment_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {
            incidents_factivity_comment_item_text_view_text.text = value.text
            incidents_factivity_comment_item_text_view_name.text = value.name
            incidents_factivity_comment_item_text_view_time.text = DATE_FORMAT.format(value.time)


            value?.let {
                Picasso.with(context)
                        .load(value?.photoUrl)
                        .placeholder(R.drawable.ic_check)
                        .transform(CircleTransform())
                        .error(R.drawable.ic_check)
                        .into(incidents_factivity_comment_item_image);
            }
        }
    }
}
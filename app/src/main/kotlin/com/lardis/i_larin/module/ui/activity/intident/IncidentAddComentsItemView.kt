package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.prefs.Prefs
import com.lardis.i_larin.module.ui.widget.ui.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.incidents_activity_add_coments_item.view.*
import kotlinx.android.synthetic.main.message_send_view.view.*

class IncidentAddComentsItemView( val onClickListen: (String) -> Unit?) : ItemView<Int?>(null, {}) {
    override fun getLayoutId() = R.layout.incidents_activity_add_coments_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {

            message_send_view_image_user.visibility=View.VISIBLE
            if( Prefs.PHOTO_URL.string.length>3)
            Picasso.with(context)
                    .load(Prefs.PHOTO_URL.string)
                    .placeholder(R.drawable.ic_check)
                    .transform(CircleTransform())
                    .error(R.drawable.ic_check)
                    .into(message_send_view_image_user);
            incidents_activity_add_coments_item_button.callback = { onClickListen.invoke(it) }
        }
    }
}
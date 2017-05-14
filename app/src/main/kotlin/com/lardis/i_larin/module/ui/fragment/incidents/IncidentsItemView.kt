package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.ui.widget.ui.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.incidents_fragment_item.view.*
import java.text.SimpleDateFormat

class IncidentsItemView(value: FBModel,  onClickListener: (ItemView<FBModel>) -> Unit?) : ItemView<FBModel>(value, onClickListener) {
    override fun getLayoutId() = R.layout.incidents_fragment_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {
            value?.intident.let {    incidents_fragment_item_edit_text_incident.text =it}
            value?.name.let {    incidents_fragment_item_edit_text_name.text =it}
            value?.createTime.let {    incidents_fragment_item_edit_text_time.text = DATE_FORMAT.format(it)}
            Picasso.with(context)
                    .load(""+value?.photoUrl)
                    .placeholder(R.drawable.ic_check)
                    .transform(CircleTransform())
                    .error(R.drawable.ic_check)
                    .into(incidents_fragment_item_image);

        }
    }
}
var DATE_FORMAT =   SimpleDateFormat("dd-MM-yy:HH:mm:SS");
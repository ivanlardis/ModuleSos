package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.storage.entities.DialogModel
import kotlinx.android.synthetic.main.dialods_fragment_item.view.*

class DialogsItemView(value: DialogModel,   onClickListener: (ItemView<DialogModel>) -> Unit? = {}) : ItemView<DialogModel>(value, onClickListener) {
    override fun getLayoutId() = R.layout.dialods_fragment_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {
            dialods_fragment_item_text_view_title.text=value.description
            dialods_fragment_item_text_view_last_message.text=value.lastMessage
            dialods_fragment_item_text_view_last_description.text=value.description
            dialods_fragment_item_text_view_time_create.text=value.timeCreate.toString()
        }
    }
}
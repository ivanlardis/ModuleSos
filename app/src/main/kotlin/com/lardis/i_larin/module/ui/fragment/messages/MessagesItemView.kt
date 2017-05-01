package com.example.i_larin.pixabayreader.ui.adapter.view

import android.view.View
import com.example.black_sony.testrecyclerview.core.ItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.storage.entities.MessageModel
import kotlinx.android.synthetic.main.messages_fragment_item.view.*

class MessagesItemView(value: MessageModel) : ItemView<MessageModel>(value, {}) {
    override fun getLayoutId() = R.layout.messages_fragment_item

    override fun bind(view: View) {
        super.bind(view)
        with(view)
        {
            messages_fragment_item_text_view_time_create.text =  ""+value.timeCreate
            messages_fragment_item_text_view_message.text = value.message
            messages_fragment_item_text_view_author.text = ""+value.idAuthor
        }
    }
}
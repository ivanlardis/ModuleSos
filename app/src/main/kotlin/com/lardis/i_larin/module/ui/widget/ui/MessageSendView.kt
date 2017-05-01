package com.lardis.i_larin.module.ui.widget.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import com.lardis.i_larin.module.R
import kotlinx.android.synthetic.main.message_send_view.view.*
import timber.log.Timber

/**
 * Created by black-sony on 01.05.17.
 */
class MessageSendView : FrameLayout {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    var buttonSend: ImageView? = null
    var etSend: EditText? = null

    fun init() {
        var inflate = LayoutInflater.from(getContext()).inflate(R.layout.message_send_view, this);
        with(inflate)
        {
            buttonSend = message_send_view_image

            etSend = message_send_view_edit_text
        }
        hideButton()

        etSend?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Timber.e("" + s?.length)
                s?.let {
                    if (s.length > 2) {
                        showButton()
                    } else {
                        hideButton()

                    }
                }


            }
        })

        buttonSend?.setOnClickListener {

            callback.invoke(etSend?.text.toString())

            etSend?.setText("")
        }
    }

    var callback: (String) -> Unit? = {}
    private fun showButton() {
        Timber.e("showButton")
        buttonSend?.visibility = View.VISIBLE

    }

    private fun hideButton() {
        Timber.e("hideButton")
        buttonSend?.visibility = View.GONE

    }

}

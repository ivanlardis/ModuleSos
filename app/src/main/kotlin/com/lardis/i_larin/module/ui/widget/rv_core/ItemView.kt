package com.example.black_sony.testrecyclerview.core

import android.view.View


abstract class ItemView<T>(
        val value: T,
        var onClickListener: (ItemView<T>) -> Unit? = {}) {

    abstract fun getLayoutId(): Int

    open fun getItemId(): Any? = Unit

    open fun bind(view: View) {
        view.setOnClickListener {
            onClickListener.invoke(this)
        }
    }
}
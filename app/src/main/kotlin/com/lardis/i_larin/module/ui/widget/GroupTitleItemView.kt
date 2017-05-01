package com.example.black_sony.testrecyclerview.core

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class GroupTitleItemView<T>(value: T, onClickListener: (ItemView<T>) -> Unit?) : ItemView<T>(value, onClickListener) {

    abstract fun hideOnEmptyGroup(): Boolean
    var expand = false

    override fun bind(view: View) {
        super.bind(view)
        view.setOnClickListener {
            expand = !expand
            observer?.onChanged()
        }
    }

    fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        this.observer = observer
    }

    var observer: RecyclerView.AdapterDataObserver? = null
}
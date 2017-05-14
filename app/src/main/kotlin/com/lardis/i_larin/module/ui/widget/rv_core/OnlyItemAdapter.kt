package com.example.black_sony.testrecyclerview.core

import android.support.v7.widget.RecyclerView
import timber.log.Timber
import java.util.*


class OnlyItemAdapter : ItemAdapter {
    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        this.observer = observer
    }

    var observer: RecyclerView.AdapterDataObserver? = null
    var headerItems: MutableList<ItemView<*>> = ArrayList()
    var data: MutableList<ItemView<*>> = ArrayList()
    var footerItems: MutableList<ItemView<*>> = ArrayList()


    override fun getItem(position: Int): ItemView<*> {
        Timber.e("position"+position)
        Timber.e("headerItems.size"+headerItems.size)
        Timber.e("data.size"+data.size)
        Timber.e("footerItems"+footerItems.size)

        var itemPosition = position
        if (itemPosition < headerItems.size) {
            return headerItems[itemPosition]
        } else {
            itemPosition -= headerItems.size
        }
        Timber.e(" data position"+itemPosition)
        if (itemPosition < data.size) {
            return data[itemPosition]
        } else {
            itemPosition -= data.size
        }
        Timber.e("footerItems position"+itemPosition)
        Timber.e("footerItems size"+footerItems.size)

        return footerItems[itemPosition]
    }


    fun addItemViews(dataList: List<ItemView<*>>) {
        this.data.addAll(dataList.subList(this.data.size, dataList.size - 1))
        observer?.onChanged()
    }



    fun updateItemViews(dataList: List<ItemView<*>>) {
        data.clear()
        data.addAll(dataList)
        observer?.onChanged()
    }

    override fun getItemCount() = headerItems.size + footerItems.size + data.size


    override fun addHeader(headerItem: ItemView<*>) {
        headerItems.add(headerItem)
    }


    override fun removeHeader(headerItem: ItemView<*>) {
        headerItems.remove(headerItem)
    }

    override fun addFooter(footerItem: ItemView<*>) {
        footerItems.add(footerItem)
    }

    override fun removeFooter(footerItem: ItemView<*>) {
        footerItems.remove(footerItem)
    }
}
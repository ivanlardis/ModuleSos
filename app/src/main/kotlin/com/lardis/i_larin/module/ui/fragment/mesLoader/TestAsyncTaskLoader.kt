package com.lardis.i_larin.module.ui.fragment.mesLoader

import android.content.Context
import android.support.v4.content.AsyncTaskLoader

/**
 * Created by black-sony on 08.05.17.
 */
class TestAsyncTaskLoader(context: Context?) : AsyncTaskLoader<String>(context) {
    override fun loadInBackground(): String {
        Thread.sleep(10000)
        return "Сообшение из лоодера" + Math.random()
    }
}
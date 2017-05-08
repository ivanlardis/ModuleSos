package com.lardis.i_larin.module.ui.fragment.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.ui.fragment.mesLoader.TestAsyncTaskLoader
import kotlinx.android.synthetic.main.mes_loader_fragment.*
import timber.log.Timber


class MesLoaderFragment : Fragment(), LoaderManager.LoaderCallbacks<String> {


    val LOADER_ID = 1
    override fun onLoadFinished(loader: android.support.v4.content.Loader<String>?, data: String?) {
        Timber.e("onLoadFinished")
        mes_loader_fragment_progress_bar.visibility = GONE
        mes_loader_fragment_text_view.setText("Ответ $data")
    }

    override fun onLoaderReset(loader: android.support.v4.content.Loader<String>?) {
        Timber.e("onLoaderReset")
    }

    override fun onCreateLoader(id: Int, args: Bundle?): android.support.v4.content.Loader<String>? =
            when (id) {
                LOADER_ID -> TestAsyncTaskLoader(context)
                else -> null
            }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.mes_loader_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startLoaderLoad()
    }

    private fun startLoaderLoad() {


        mes_loader_fragment_progress_bar.visibility = View.VISIBLE


        //TODO Не   знаком  с лоадерами и из-за этого смотрю был ли создан лоадер
        // TODO  если да то иничю тем самым возращая последний результат, если нет то инит плю форсе
        // TODO  Думаю это костыль, и был бы очень признателен если подсказали как правильно это делать

        var loader: Loader<String>? = loaderManager.getLoader(LOADER_ID)
        var restartLoader = loaderManager.initLoader(LOADER_ID, null, this)
        if (loader == null) restartLoader.forceLoad()
    }

    companion object {

        val TAG = "MesLoaderFragment"


        fun newInstance(): MesLoaderFragment {
            val fragment = MesLoaderFragment()

            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}

package com.lardis.i_larin.module.ui.fragment.dialogs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.black_sony.testrecyclerview.core.GroopAdapter
import com.example.i_larin.pixabayreader.ui.adapter.view.MessagesItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.presenter.setting.DialogPresenter
import com.lardis.i_larin.module.presentation.view.navigation.TooggleView
import com.lardis.i_larin.module.presentation.view.setting.DialogView
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel
import kotlinx.android.synthetic.main.dialogs_fragment.*
import timber.log.Timber


class MessagesFragment : MvpAppCompatFragment(), DialogView {
    override fun showMessages(dialogModels: List<MessageModel>) {

        with(groopAdapter)
        {
            clearData()
            headerItems.addAll(dialogModels.map { MessagesItemView(it) })
        }
    }

    override fun showDialogs(dialogModels: List<DialogModel>) {

    }

    fun setTitleActionBar(title: String) {
        ((activity as AppCompatActivity).supportActionBar)?.let {

            it.title = title
        }
    }

    lateinit var groopAdapter: GroopAdapter

    private fun configureRecyclerView() = with(dialogs_fragment_recycler)
    {
        groopAdapter = GroopAdapter()
        setLayoutManager(LinearLayoutManager(activity.applicationContext))
        addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        setAdapter(groopAdapter)
    }

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mDialogPresenter: DialogPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.dialogs_fragment, container, false)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()

        setTitleActionBar("Сообщения")
        mDialogPresenter.loadData()
    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume")
        (activity as TooggleView).setToggleAsBack(true)

        ((activity as AppCompatActivity).supportActionBar)?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDefaultDisplayHomeAsUpEnabled(true)
            (activity as TooggleView).setToggleAsBack(true)

        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        ((activity as AppCompatActivity).supportActionBar)?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setDefaultDisplayHomeAsUpEnabled(false)
            (activity as TooggleView).setToggleAsBack(false)

        }
    }

    companion object {

        val TAG = "SettingFragment"


        fun newInstance(): MessagesFragment {
            val fragment = MessagesFragment()

            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}

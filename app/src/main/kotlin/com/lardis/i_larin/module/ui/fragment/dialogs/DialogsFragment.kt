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
import com.example.black_sony.testrecyclerview.core.ItemView
import com.example.i_larin.pixabayreader.ui.adapter.view.DialogsItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.presenter.setting.DialogPresenter
import com.lardis.i_larin.module.presentation.view.setting.DialogView
import com.lardis.i_larin.module.storage.entities.DialogModel
import com.lardis.i_larin.module.storage.entities.MessageModel
import kotlinx.android.synthetic.main.dialogs_fragment.*


class DialogsFragment : MvpAppCompatFragment(), DialogView {
    override fun showMessages(dialogModels: List<MessageModel>) {
    }

    override fun showDialogs(dialogModels: List<DialogModel>) {
        with(groopAdapter)
        {
            clearHeader()
            headerItems.addAll(dialogModels.map { DialogsItemView(it, onClickListener) })
            notifyDataSetChanged()
        }
    }

    fun setTitleActionBar(title: String) {
        ((activity as AppCompatActivity).supportActionBar)?.let {

            it.title = title
        }
    }

    val onClickListener: (ItemView<DialogModel>) -> Unit? = { mDialogPresenter.selectedDialog(it.value.id) }

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
        setTitleActionBar("Диалоги")


        dialogs_fragment_fab.setOnClickListener { showLangDialog() }
    }


    companion object {

        val TAG = "SettingFragment"


        fun newInstance(): DialogsFragment {
            val fragment = DialogsFragment()

            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }

    var dialogFragment: AddDiaDialog? = null

    private fun showLangDialog() {

        if (activity.supportFragmentManager.findFragmentByTag("AddDiaDialog") != null) {
            dialogFragment = fragmentManager
                    .findFragmentByTag("AddDiaDialog") as AddDiaDialog
        } else {
            dialogFragment = AddDiaDialog.newInstance()

        }
        dialogFragment?.callback = { mDialogPresenter.saveDialog(it) }
        dialogFragment?.show(activity.supportFragmentManager, "AddDiaDialog")
    }

}

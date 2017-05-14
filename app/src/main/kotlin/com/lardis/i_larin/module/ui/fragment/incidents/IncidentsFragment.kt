package com.lardis.i_larin.module.ui.fragment.dialogs

import android.content.Intent
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
import com.example.i_larin.pixabayreader.ui.adapter.view.IncidentsItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.presentation.presenter.setting.IncidentsPresenter
import com.lardis.i_larin.module.presentation.view.setting.IncidentsView
import com.lardis.i_larin.module.ui.activity.intident.IntidentActivity
import com.lardis.i_larin.module.ui.fragment.incidents.IncidentAddDialog
import kotlinx.android.synthetic.main.incidents_fragment.*


class IncidentsFragment : MvpAppCompatFragment(), IncidentsView {
    override fun showSelected(data: FBModel) {}

    override fun show(data: List<FBModel>) {
        with(groopAdapter)
        {
            clearHeader()
            headerItems.addAll(data.map { IncidentsItemView(it, onClickListener) })
            notifyDataSetChanged()
        }
    }

    var onClickListener: (ItemView<FBModel>) -> Unit? = {
        mIncidentsPresenter.selected(it.value)

        val intent = Intent(context, IntidentActivity::class.java)
        startActivity(intent)

    }


    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mIncidentsPresenter: IncidentsPresenter

    lateinit var groopAdapter: GroopAdapter


    private fun configureRecyclerView() {
        var linearLayoutManager = LinearLayoutManager(activity.applicationContext)
        with(incidents_fragment_recycler)
        {
            groopAdapter = GroopAdapter()

            linearLayoutManager.setReverseLayout(true)
            setLayoutManager(linearLayoutManager)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL))
            setAdapter(groopAdapter)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.incidents_fragment, container, false)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()

        incidents_fragment_fabutton.setOnClickListener {

            showIncidentAddDialog()
        }
//        incidents_fragment_button_rm.setOnClickListener {
//            if (VKSdk.isLoggedIn())
//                mIncidentsPresenter.rm()
//            else Toast.makeText(context, "Извините Нужно залогинеться через ВК чтоб работало",
//                    Toast.LENGTH_SHORT).show()
//        }
        setTitleActionBar("Происшествия")
        mIncidentsPresenter.loadData()
    }


    companion object {


        fun newInstance(): IncidentsFragment {
            val fragment = IncidentsFragment()

            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }

    fun setTitleActionBar(title: String) {
        ((activity as AppCompatActivity).supportActionBar)?.let {

            it.title = title
        }
    }


    private var dialogFragment: IncidentAddDialog? = null
    private fun showIncidentAddDialog() {

        if (activity.supportFragmentManager.findFragmentByTag("IncidentAddDialog") != null) {
            dialogFragment = fragmentManager
                    .findFragmentByTag("IncidentAddDialog") as IncidentAddDialog
        } else {
            dialogFragment = IncidentAddDialog.newInstance()

        }
        dialogFragment?.callback = {
            mIncidentsPresenter.add(it)

        }
        dialogFragment?.show(activity.supportFragmentManager, "IncidentAddDialog")
    }

}

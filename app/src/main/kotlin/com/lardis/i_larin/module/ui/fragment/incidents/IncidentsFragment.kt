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
import com.example.i_larin.pixabayreader.ui.adapter.view.IncidentsItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.presenter.setting.IncidentsPresenter
import com.lardis.i_larin.module.presentation.view.setting.IncidentsView
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.model.IncidentsModel
import com.lardis.i_larin.module.model.IncidentsViewModel
import kotlinx.android.synthetic.main.incidents_fragment.*


class IncidentsFragment : MvpAppCompatFragment(), IncidentsView {
    override fun show(data: IncidentsModel) {
        with(groopAdapter)
        {
            clearHeader()
            headerItems.addAll(data.incidents.map { IncidentsItemView(IncidentsViewModel(it,data.user.get(it.idUser))) })
            notifyDataSetChanged()
        } }



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
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setAdapter(groopAdapter)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.incidents_fragment, container, false)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()

        incidents_fragment_button_add.setOnClickListener { mIncidentsPresenter.add() }
        incidents_fragment_button_rm.setOnClickListener { mIncidentsPresenter.rm() }
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

}

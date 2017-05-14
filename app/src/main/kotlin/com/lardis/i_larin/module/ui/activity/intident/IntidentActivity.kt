package com.lardis.i_larin.module.ui.activity.intident

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.black_sony.testrecyclerview.core.GroopAdapter
import com.example.i_larin.pixabayreader.ui.adapter.view.IncidentCompleteItemView
import com.example.i_larin.pixabayreader.ui.adapter.view.IncidentCompletedItemView
import com.example.i_larin.pixabayreader.ui.adapter.view.IncidentCreateItemView
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.prefs.Prefs
import com.lardis.i_larin.module.presentation.presenter.setting.IncidentsPresenter
import com.lardis.i_larin.module.presentation.view.setting.IncidentsView
import kotlinx.android.synthetic.main.intident_activity.*
import timber.log.Timber

class IntidentActivity : MvpAppCompatActivity(), View.OnClickListener, IncidentsView {
    override fun showSelected(data: FBModel) {

        createView(data)

        with(groopAdapter)
        {
            clearHeader()
            headerItems.add(IncidentCreateItemView(data, {}))

            if (data.compeleteTime > 0) {
                headerItems.add(IncidentCompleteItemView(data, {}))
            } else if (data.idUser.equals("" + Prefs.USER_ID.integer)) {

                headerItems.add(IncidentCompletedItemView(data, {
                    Timber.e("IncidentCompletedItemView")
                    mIncidentsPresenter.completed(it)

                }))

            }



            notifyDataSetChanged()
        }


    }

    override fun show(data: List<FBModel>) {
    }


    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mIncidentsPresenter: IncidentsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intident_activity)
        configureRecyclerView()
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = intident_activity_toolbar.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true);
            it.setDisplayShowHomeEnabled(true);
            it.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(this)

    }

    private fun createView(fBModel: FBModel) {
        intident_activity_toolbar.setTextTitle(fBModel.name)
                .setTextSubtitle(fBModel.family)
                .setTextValue(fBModel.phoneNumber)
                .setTextUnit(fBModel.car)
                .setTextExtraDescription(fBModel.mapInfo)
                .setImageUrl(fBModel.photoUrl)
                .applyChanges()
    }

    lateinit var groopAdapter: GroopAdapter

    private fun configureRecyclerView() {
        var linearLayoutManager = LinearLayoutManager(applicationContext)
        with(intident_activity_recycler)
        {
            groopAdapter = GroopAdapter()

            setLayoutManager(linearLayoutManager)
            addItemDecoration(DividerItemDecoration(this@IntidentActivity, DividerItemDecoration.HORIZONTAL))
            setAdapter(groopAdapter)
        }
    }

    override fun onClick(v: View) {
        onBackPressed()
        Timber.e("onBackPressed")
    }

}
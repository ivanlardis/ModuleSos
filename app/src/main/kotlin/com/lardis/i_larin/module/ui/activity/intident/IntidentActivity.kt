package com.lardis.i_larin.module.ui.activity.intident

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.black_sony.testrecyclerview.core.GroopAdapter
import com.example.black_sony.testrecyclerview.core.ItemView
import com.example.black_sony.testrecyclerview.core.OnlyItemAdapter
import com.example.black_sony.testrecyclerview.view.IncidentTitleItemView
import com.example.i_larin.pixabayreader.ui.adapter.view.*
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.prefs.Prefs
import com.lardis.i_larin.module.presentation.presenter.setting.IncidentsPresenter
import com.lardis.i_larin.module.presentation.view.setting.IncidentsView
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.intident_activity.*
import timber.log.Timber
import java.util.*


class IntidentActivity : MvpAppCompatActivity(), View.OnClickListener, IncidentsView {

    var incidentTitleItemView = IncidentTitleItemView("Коменнтарии")
    override fun showSelected(data: FBModel) {

        createView(data)

        with(groopAdapter)
        {

            clearAll()
            headerItems.add(IncidentCreateItemView(data, {}))



            if (data.phoneNumber.length > 3) {
                headerItems.add(IncidentButtonItemView(data, "Позвонить", {

                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${data.phoneNumber}"))
                    startActivity(intent)

                }))
                headerItems.add(IncidentButtonItemView(data, "Написать смс", {

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${data.phoneNumber}"))
                    startActivity(intent)

                }))

            }
            if (data.longitude != null && data.latitude != null) {
                headerItems.add(IncidentButtonItemView(data, "Открыть на карте", {
                    Timber.e("${data.longitude},${data.latitude}")

                    val geoUriString = "geo:${data.latitude},${data.longitude}?z=2"
                    val geoUri = Uri.parse(geoUriString)
                    val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
                    startActivity(mapIntent)

                }))

            }


            if (data.compeleteTime > 0) {
                headerItems.add(IncidentCompleteItemView(data, {}))
            } else if (data.idUser.equals("" + Prefs.USER_ID.integer)) {

                headerItems.add(IncidentButtonItemView(data, "Проблемма решена", {
                    Timber.e("IncidentButtonItemView")
                    mIncidentsPresenter.completed(it)

                }))

            }


            var list: MutableList<ItemView<*>> = ArrayList()
            data.comments?.forEach { list.add(IncidentsCommentItemView(it.value)) }


            var pixabayImagesAdapter = OnlyItemAdapter()
            pixabayImagesAdapter.updateItemViews(list)

            pixabayImagesAdapter.footerItems.add(IncidentAddComentsItemView({

                if (VKSdk.isLoggedIn()) {
                    mIncidentsPresenter.addComments(it)
                } else Toast.makeText(this@IntidentActivity, "Извините Нужно залогинеться через ВК чтоб работало",
                        Toast.LENGTH_SHORT).show()
            }))

            groopAdapter.addGroopData(incidentTitleItemView, pixabayImagesAdapter)



            notifyDataSetChanged()
        }


    }

    var extandComment: Boolean = false
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
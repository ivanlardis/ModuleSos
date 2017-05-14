package com.lardis.i_larin.module.ui.activity.intident

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.model.FBModel
import com.lardis.i_larin.module.presentation.presenter.setting.IncidentsPresenter
import com.lardis.i_larin.module.presentation.view.setting.IncidentsView
import kotlinx.android.synthetic.main.intident_activity.*
import timber.log.Timber

class IntidentActivity : MvpAppCompatActivity(), View.OnClickListener , IncidentsView {
    override fun showSelected(data: FBModel) {

        createView(data)
      }

    override fun show(data: List<FBModel>) {
      }


    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mIncidentsPresenter: IncidentsPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intident_activity)

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
                .setTextExtraDescription(fBModel.mapInfo )
                .setImageUrl(fBModel.photoUrl)
                .applyChanges()
    }



    override fun onClick(v: View) {
        onBackPressed()
        Timber.e("onBackPressed")
    }

}
package com.lardis.i_larin.module.ui.activity.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.view.navigation.NavigationView
import com.lardis.i_larin.module.presentation.presenter.navigation.NavigationPresenter

import                  com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpAppCompatActivity


class NavigationActivity : MvpAppCompatActivity(), NavigationView {
    companion object {
        const val TAG = "NavigationActivity"
        fun getIntent(context: Context): Intent = Intent(context, NavigationActivity::class.java)
    }

    @InjectPresenter
    lateinit var mNavigationPresenter: NavigationPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }
}

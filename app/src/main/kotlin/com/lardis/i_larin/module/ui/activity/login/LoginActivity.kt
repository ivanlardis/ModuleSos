package com.lardis.i_larin.module.ui.activity.login

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.view.login.LoginView
import com.lardis.i_larin.module.presentation.presenter.login.LoginPresenter

import                  com.arellomobile.mvp.MvpAppCompatActivity;


class LoginActivity : MvpAppCompatActivity(), LoginView {
    companion object {
        const val TAG = "LoginActivity"
        fun getIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    @InjectPresenter
    lateinit var mLoginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}

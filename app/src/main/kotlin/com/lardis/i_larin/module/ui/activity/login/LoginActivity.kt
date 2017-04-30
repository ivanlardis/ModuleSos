package com.lardis.i_larin.module.ui.activity.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.presenter.login.LoginPresenter
import com.lardis.i_larin.module.presentation.view.login.LoginView
import com.lardis.i_larin.module.ui.activity.navigation.NavigationActivity
import kotlinx.android.synthetic.main.login_activity.*



class LoginActivity : MvpAppCompatActivity(), LoginView {
    val EMAIL = "EMAIL"
    val PASSWORD = "PASSWORD"


    companion object {
        const val TAG = "LoginActivity"
        fun getIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    @InjectPresenter
    lateinit var mLoginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        login_activity_button.setOnClickListener { startNavigationActivity() }

    }

    private fun startNavigationActivity() {
        val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
        intent.putExtra(EMAIL, login_activity_edit_text_email.getText().toString())
        intent.putExtra(PASSWORD, login_activity_edit_text_password.getText().toString())
        startActivity(intent)
    }

}

package com.lardis.i_larin.module.ui.activity.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.presenter.login.LoginPresenter
import com.lardis.i_larin.module.presentation.view.login.LoginView
import com.lardis.i_larin.module.ui.activity.navigation.NavigationActivity
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : MvpAppCompatActivity(), LoginView {
    override fun showProgress(boolean: Boolean) {
        if (boolean) {
            login_activity_button.showProgress()
        } else login_activity_button.hideProgress()

    }

    override fun showWarning() {
        Toast.makeText(this, "Неправильный пароль", Toast.LENGTH_SHORT).show()
    }

    override fun showOK() {

        startNavigationActivity()
    }

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
        login_activity_button.setOnClickListener {
            mLoginPresenter.checkUser(login_activity_edit_text_email.text.toString(),
                    login_activity_edit_text_password.text.toString())
        }

        login_activity_button_vk.setOnClickListener {

            VKSdk.login(this, "scope=groups,friends,wall,video,audio,pages,messages,offline")

        }

    }

    private fun startNavigationActivity() {
        val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
        intent.putExtra(EMAIL, login_activity_edit_text_email.getText().toString())
        intent.putExtra(PASSWORD, login_activity_edit_text_password.getText().toString())

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                intent.putExtra(EMAIL, VKAccessToken.currentToken().userId)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            override fun onError(error: VKError) {}
        })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
